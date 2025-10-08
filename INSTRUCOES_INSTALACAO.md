# 📱 Support Fire - Instruções de Instalação e Execução

## 🎯 Visão Geral
Este é o app mobile "Support Fire" desenvolvido para o curso de Brigadista Mirim, autorizado pelo Corpo de Bombeiros do Estado do Rio de Janeiro.

## 📋 Pré-requisitos

### 1. Node.js
- **Versão**: 16 ou superior
- **Download**: https://nodejs.org/
- **Verificar instalação**: `node --version`

### 2. React Native CLI
```bash
npm install -g react-native-cli
```

### 3. Para Android
- **Android Studio**: https://developer.android.com/studio
- **SDK Android**: Instalar via Android Studio
- **Variáveis de ambiente**: Configurar ANDROID_HOME

### 4. Para iOS (apenas no macOS)
- **Xcode**: Via App Store
- **CocoaPods**: `sudo gem install cocoapods`

## 🚀 Instalação do Projeto

### 1. Instalar dependências
```bash
npm install
```

### 2. Para iOS (apenas no macOS)
```bash
cd ios && pod install && cd ..
```

## ▶️ Executar o App

### Android
```bash
npx react-native run-android
```

### iOS (apenas no macOS)
```bash
npx react-native run-ios
```

### Metro Bundler (separadamente)
```bash
npx react-native start
```

## 📱 Funcionalidades do App

### 🏠 Tela Inicial
- Apresentação do projeto Support Fire
- Informações sobre o curso de Brigadista Mirim
- Botões para inscrição e área administrativa

### 📝 Tela de Cadastro
- Formulário completo com validação
- Todos os campos obrigatórios
- Seleção de data, tipo sanguíneo, estado
- Salvamento automático no dispositivo

### ✅ Tela de Sucesso
- Confirmação da inscrição
- Exibição dos dados cadastrados

### 🔧 Área Administrativa
- Listagem de todos os cadastros
- Busca por nome, e-mail ou cidade
- **Exportação para CSV** (funcionalidade principal)
- Visualização detalhada de cada cadastro

## 📊 Exportação CSV
O app gera automaticamente um arquivo CSV com:
- ID auto-incremental
- Todos os dados do cadastro
- Data de cadastro
- Formatação adequada para planilhas

## 🛠️ Estrutura do Projeto
```
support-fire/
├── src/
│   ├── screens/          # Todas as telas
│   └── styles/           # Tema e estilos
├── App.js               # Componente principal
├── package.json         # Dependências
└── README.md           # Documentação completa
```

## 🔧 Comandos Úteis

### Limpar cache
```bash
npx react-native start --reset-cache
```

### Debug Android
```bash
adb logcat
```

### Debug iOS
```bash
npx react-native log-ios
```

### Build para produção
```bash
# Android
cd android && ./gradlew assembleRelease

# iOS
cd ios && xcodebuild -workspace SupportFire.xcworkspace -scheme SupportFire -configuration Release
```

## 📱 Testando o App

### 1. Cadastro
- Preencha todos os campos obrigatórios
- Teste a validação de e-mail
- Verifique o salvamento dos dados

### 2. Área Administrativa
- Acesse a área administrativa
- Teste a busca por nome/e-mail
- **Teste a exportação CSV** (funcionalidade principal)
- Verifique a visualização detalhada

### 3. Navegação
- Teste a navegação entre todas as telas
- Verifique o botão "Voltar"
- Teste o botão "Nova Inscrição"

## 🚨 Problemas Comuns

### Erro de Metro
```bash
npx react-native start --reset-cache
```

### Erro de dependências
```bash
rm -rf node_modules
npm install
```

### Erro de Android
- Verificar se o emulador está rodando
- Verificar se o USB Debugging está ativado
- Verificar as variáveis de ambiente

### Erro de iOS
```bash
cd ios && pod install && cd ..
```

## 📞 Suporte
Para dúvidas ou problemas:
1. Consulte o README.md principal
2. Verifique os logs de erro
3. Entre em contato com o suporte técnico

## 🎯 Próximos Passos
1. **Testar todas as funcionalidades**
2. **Configurar para publicação** nas lojas
3. **Fazer backup** dos dados
4. **Documentar** qualquer customização

---

**Support Fire** - Formando brigadistas mirins para um futuro mais seguro! 🔥👨‍🚒👩‍🚒

