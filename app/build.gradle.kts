import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization) // Aplica o plugin de serialização
}

// --- Automatic Version Code Increment ---
val versionPropsFile = rootProject.file("app/version.properties")
if (versionPropsFile.isFile) {
    val versionProps = Properties()
    versionProps.load(versionPropsFile.inputStream())

    // Increment versionCode for any "assemble" or "bundle" task.
    val isPackagingTask = gradle.startParameter.taskNames.any { taskName ->
        taskName.contains("assemble", ignoreCase = true) || taskName.contains(
            "bundle",
            ignoreCase = true
        )
    }

    if (isPackagingTask) {
        val currentVersionCode = versionProps["versionCode"].toString().toInt()
        val newVersionCode = currentVersionCode + 1
        versionProps["versionCode"] = newVersionCode.toString()
        versionProps.store(versionPropsFile.writer(), "Auto-incremented versionCode for build.")
        println(">>> Version code automatically incremented to: $newVersionCode")
    }
}
// --- End of Automatic Versioning ---

// Load version properties again to ensure the build uses the updated values
val versionProps = Properties()
versionProps.load(versionPropsFile.inputStream())

android {
    namespace = "br.com.supportfire.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "br.com.supportfire.app"
        minSdk = 24
        targetSdk = 35
        // Read the updated version properties
        versionCode = versionProps["versionCode"].toString().toInt()
        versionName = versionProps["versionName"].toString()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            // Habilita a ofuscação (minification) e o R8 shrinking
            isMinifyEnabled = true
            // Habilita a remoção de recursos não utilizados
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // Inclui os símbolos de depuração para código nativo
            ndk {
                debugSymbolLevel = "FULL"
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(platform(libs.androidx.compose.bom))

    // AndroidX Core & Lifecycle
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.splashscreen)

    // Compose UI - As versões são gerenciadas pelo BOM
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.navigation.compose)

    // Ktor para Networking
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)

    // Testes
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // Ferramentas de Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
