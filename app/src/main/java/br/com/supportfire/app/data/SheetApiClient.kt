package br.com.supportfire.app.data

import android.util.Log
import br.com.supportfire.app.data.model.RegistrationData
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.json.JSONObject

class SheetApiClient {
    private val client = HttpClient(Android) {
        // Desativamos o seguimento automático de redirecionamento para lidar com o caso do Google Script manualmente.
        followRedirects = false

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    // URL do seu script
    private val scriptUrl =
        "https://script.google.com/macros/s/AKfycbyQSzR9INC1Tg3E2Y15jXCRlN41f2Xf3EwJ-_fMZq07q7jeHpDTMosQJdyU7D1chauzLw/exec"

    /**
     * Envia os dados de registro para o endpoint do Google Apps Script.
     * @return O código de registro (String) em caso de sucesso, ou null em caso de falha.
     */
    suspend fun submitRegistration(data: RegistrationData): String? {
        return try {
            // 1. Faz a requisição POST inicial
            val postResponse: HttpResponse = client.post(scriptUrl) {
                contentType(ContentType.Application.Json)
                setBody(data)
            }

            var finalResponse = postResponse

            // 2. Verifica se a resposta é um redirecionamento (302 Found)
            if (postResponse.status == HttpStatusCode.Found) {
                val location = postResponse.headers[HttpHeaders.Location]
                if (location != null) {
                    Log.d(
                        "SheetApiClient",
                        "Redirecionamento 302 recebido. Seguindo para: $location"
                    )
                    // 3. Faz uma nova requisição GET para a URL de redirecionamento
                    finalResponse = client.get(location)
                } else {
                    Log.e("SheetApiClient", "Redirecionamento 302 sem cabeçalho 'Location'.")
                    return null
                }
            }

            val responseBody = finalResponse.bodyAsText()
            Log.d(
                "SheetApiClient",
                "Resposta FINAL - Status: ${finalResponse.status}, Corpo: $responseBody"
            )

            if (finalResponse.status != HttpStatusCode.OK) {
                Log.e(
                    "SheetApiClient",
                    "Falha na requisição final: ${finalResponse.status}, Corpo: $responseBody"
                )
                return null
            }

            // Tentativa 1: Analisar como JSON (o formato esperado)
            try {
                val jsonResponse = JSONObject(responseBody)
                if (jsonResponse.optString("status") == "success") {
                    return jsonResponse.getString("registrationCode")
                } else {
                    val errorMessage =
                        jsonResponse.optString("message", "Erro desconhecido no script.")
                    Log.e("SheetApiClient", "Erro do Script (JSON): $errorMessage")
                    return null
                }
            } catch (e: Exception) {
                Log.w(
                    "SheetApiClient",
                    "Não foi possível analisar a resposta como JSON. Tentando como texto plano."
                )
            }

            // Tentativa 2: Tratar como texto plano (código de registro direto)
            val potentialCode = responseBody.trim()
            if (potentialCode.isNotEmpty() && !potentialCode.contains(" ") && !potentialCode.contains(
                    "<"
                )
            ) {
                Log.d(
                    "SheetApiClient",
                    "Assumindo que a resposta é o código de registro: '$potentialCode'"
                )
                return potentialCode
            }

            Log.e(
                "SheetApiClient",
                "A resposta não era JSON válido nem um código de texto plano reconhecido."
            )
            return null

        } catch (e: Exception) {
            Log.e("SheetApiClient", "Exceção ao enviar registro: ${e.message}", e)
            return null
        }
    }
}
