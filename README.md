# Support Fire - App de Cadastro para Curso de Brigadista Mirim

## 🔥 Descrição
App mobile desenvolvido em React Native para cadastro de alunos no curso de Brigadista Mirim, autorizado pelo Corpo de Bombeiros do Estado do Rio de Janeiro.

## ✨ Funcionalidades
- **Landing Page** com informações sobre o curso
- **Formulário de pré-inscrição** completo com validação
- **Área administrativa** para gerenciar cadastros
- **Exportação automática para CSV** com todos os dados
- **Interface responsiva** e intuitiva
- **Armazenamento local** com lista auto-incremental

## 📱 Telas do Aplicativo

### 🏠 Tela Inicial (HomeScreen)
- Apresentação do projeto Support Fire
- Informações sobre o curso de Brigadista Mirim
- Objetivos e conteúdo programático
- Botões para inscrição e área administrativa

### 📝 Tela de Cadastro (RegisterScreen)
- Formulário completo com todos os campos obrigatórios
- Validação de dados em tempo real
- Seleção de data de nascimento
- Campos: Nome, E-mail, Telefone, Sexo, Tipo Sanguíneo, Endereço, Bairro, Cidade, Estado, Data de Nascimento, Pai, Mãe, Curso Desejado, Como soube do curso

### ✅ Tela de Sucesso (SuccessScreen)
- Confirmação da inscrição
- Exibição dos dados cadastrados
- Opções para nova inscrição ou voltar ao início

### 🔧 Área Administrativa (AdminScreen)
- Listagem de todos os cadastros
- Busca por nome, e-mail ou cidade
- Visualização detalhada de cada cadastro
- **Exportação para CSV** (funcionalidade principal)
- Contador de cadastros

## 🛠️ Tecnologias Utilizadas
- **React Native 0.72.6** - Framework principal
- **React Navigation 6** - Navegação entre telas
- **React Native Paper** - Componentes de UI
- **AsyncStorage** - Armazenamento local
- **React Native Date Picker** - Seleção de datas
- **React Native Picker Select** - Seleção de opções
- **React Native FS** - Manipulação de arquivos
- **React Native Share** - Compartilhamento de arquivos

## 📦 Instalação

### Pré-requisitos
- Node.js (versão 16 ou superior)
- React Native CLI
- Android Studio (para Android)
- Xcode (para iOS)

### Passos para instalação

1. **Clone o repositório**
```bash
git clone <url-do-repositorio>
cd support-fire
```

2. **Instale as dependências**
```bash
npm install
```

3. **Para Android:**
```bash
npx react-native run-android
```

4. **Para iOS:**
```bash
npx react-native run-ios
```

## 📁 Estrutura do Projeto
```
support-fire/
├── src/
│   ├── screens/
│   │   ├── HomeScreen.js          # Tela inicial
│   │   ├── RegisterScreen.js      # Tela de cadastro
│   │   ├── SuccessScreen.js       # Tela de sucesso
│   │   └── AdminScreen.js         # Tela administrativa
│   └── styles/
│       └── theme.js               # Tema do app
├── App.js                         # Componente principal
├── index.js                       # Ponto de entrada
├── package.json                   # Dependências
├── metro.config.js               # Configuração do Metro
├── babel.config.js               # Configuração do Babel
└── tsconfig.json                 # Configuração do TypeScript
```

## 🚀 Funcionalidades Implementadas

### ✅ Sistema de Cadastro
- **Validação completa** de todos os campos obrigatórios
- **Interface intuitiva** com React Native Paper
- **Seleção de data** com date picker nativo
- **Campos personalizados** para tipo sanguíneo, estado, etc.

### ✅ Armazenamento Local
- **AsyncStorage** para persistência de dados
- **Lista auto-incremental** conforme solicitado
- **Backup automático** dos dados

### ✅ Exportação CSV
- **Geração automática** de arquivo CSV
- **Todos os campos** incluídos na exportação
- **Compartilhamento** via apps nativos
- **Formatação adequada** para planilhas

### ✅ Área Administrativa
- **Busca avançada** por múltiplos campos
- **Visualização detalhada** de cada cadastro
- **Contador de inscrições** em tempo real
- **Interface responsiva** para diferentes tamanhos de tela

## 📱 Publicação nas Lojas

### Google Play Store
1. **Crie uma conta** no Google Play Console
2. **Prepare o APK/AAB** usando o comando de build
3. **Configure os metadados** do app
4. **Envie para revisão**

### App Store (iOS)
1. **Crie uma conta** no Apple Developer Program
2. **Configure o projeto** no Xcode
3. **Prepare os assets** e metadados
4. **Envie para revisão**

## 🔧 Manutenção
O app foi desenvolvido com foco em facilidade de manutenção:
- **Código bem estruturado** e documentado
- **Componentes reutilizáveis**
- **Fácil adição** de novas funcionalidades
- **Interface responsiva**

## 📊 Dados do CSV
O arquivo CSV exportado contém as seguintes colunas:
- ID (auto-incremental)
- Nome
- E-mail
- Telefone
- Sexo
- Tipo Sanguíneo
- Endereço
- Bairro
- Cidade
- Estado
- Data de Nascimento
- Pai
- Mãe
- Curso Desejado
- Como Soube
- Data do Cadastro

## 🎯 Vantagens da Solução

### 💰 Low Cost
- **Tecnologias gratuitas** (React Native, bibliotecas open-source)
- **Sem custos de licenciamento**
- **Fácil manutenção** por desenvolvedores

### 🔧 Fácil Manutenção
- **Código bem estruturado** e documentado
- **Componentes modulares**
- **Fácil adição** de novas funcionalidades
- **Interface responsiva**

### 📱 Cross-Platform
- **Funciona em Android e iOS**
- **Código único** para ambas as plataformas
- **Performance nativa**

### 🚀 Pronto para Publicação
- **Estrutura completa** para as lojas
- **Configurações otimizadas**
- **Instruções detalhadas** de publicação

## 📞 Suporte
Para dúvidas ou suporte técnico, entre em contato através do e-mail de suporte igoralencar1985@gmail.com.

## 📄 Licença
Este projeto é desenvolvido para o curso de Brigadista Mirim, autorizado pelo Corpo de Bombeiros do Estado do Rio de Janeiro.

---

**Support Fire** - Formando brigadistas mirins para um futuro mais seguro! 🔥👨‍🚒👩‍🚒

