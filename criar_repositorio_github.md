# 🚀 Criar Repositório no GitHub - Support Fire

## 📋 Passo a Passo Completo

### 1. 🌐 Acessar o GitHub
- Vá para: https://github.com
- Faça login na sua conta (ou crie uma se necessário)

### 2. ➕ Criar Novo Repositório
- Clique no botão **"New"** (verde) ou **"+"** no canto superior direito
- Ou acesse diretamente: https://github.com/new

### 3. ⚙️ Configurar o Repositório

#### 📝 Informações Básicas:
- **Repository name**: `support-fire`
- **Description**: `App mobile para cadastro do curso de Brigadista Mirim - Support Fire`
- **Visibility**: ✅ **Public** (gratuito)
- **Initialize this repository with**:
  - ❌ **NÃO** marque "Add a README file" (já temos um)
  - ❌ **NÃO** marque "Add .gitignore" (já temos um)
  - ❌ **NÃO** marque "Choose a license" (opcional)

#### 🎯 Configuração Final:
```
Repository name: support-fire
Description: App mobile para cadastro do curso de Brigadista Mirim - Support Fire
Public ✅
Add a README file ❌
Add .gitignore ❌
Choose a license ❌
```

### 4. 🔗 Conectar com o Projeto Local

#### No terminal, execute os comandos:

```bash
# 1. Inicializar Git (se ainda não foi feito)
git init

# 2. Adicionar todos os arquivos
git add .

# 3. Fazer o primeiro commit
git commit -m "Initial commit: Support Fire app completo"

# 4. Adicionar o repositório remoto (SUBSTITUA SEU_USUARIO)
git remote add origin https://github.com/SEU_USUARIO/support-fire.git

# 5. Renomear branch para main
git branch -M main

# 6. Fazer push para o GitHub
git push -u origin main
```

### 5. ✅ Verificar se Funcionou
- Acesse: https://github.com/SEU_USUARIO/support-fire
- Você deve ver todos os arquivos do projeto
- O README.md deve aparecer na página inicial

## 🔧 Comandos Rápidos (Copie e Cole)

### Se for a primeira vez usando Git:
```bash
git config --global user.name "Seu Nome"
git config --global user.email "seu.email@exemplo.com"
```

### Comandos para criar o repositório:
```bash
git init
git add .
git commit -m "Initial commit: Support Fire app completo"
git remote add origin https://github.com/SEU_USUARIO/support-fire.git
git branch -M main
git push -u origin main
```

## 🆘 Problemas Comuns

### ❌ Erro: "git não é reconhecido"
**Solução**: Instale o Git em https://git-scm.com/download/win

### ❌ Erro de autenticação
**Solução**: Use Personal Access Token:
1. GitHub → Settings → Developer settings → Personal access tokens
2. Generate new token (classic)
3. Selecione "repo" (acesso completo aos repositórios)
4. Use o token como senha quando pedir

### ❌ Erro: "Repository not found"
**Solução**: Verifique se o nome do usuário está correto na URL

### ❌ Erro: "Permission denied"
**Solução**: Verifique se você tem permissão no repositório

## 📱 Após Criar o Repositório

### 1. 📥 Clonar em outro computador:
```bash
git clone https://github.com/SEU_USUARIO/support-fire.git
```

### 2. 🔄 Atualizar o repositório:
```bash
git add .
git commit -m "Descrição da alteração"
git push
```

### 3. 📥 Baixar atualizações:
```bash
git pull
```

## 🎯 Estrutura Final no GitHub

Após o push, você deve ver:
```
support-fire/
├── 📱 App.js
├── 📱 index.js
├── 📱 app.json
├── 📦 package.json
├── ⚙️ metro.config.js
├── ⚙️ babel.config.js
├── ⚙️ tsconfig.json
├── 📄 README.md
├── 📄 SETUP_GITHUB.md
├── 📄 INSTRUCOES_INSTALACAO.md
├── 📄 RESUMO_PROJETO.md
├── 📁 src/
│   ├── 📁 screens/
│   │   ├── 🏠 HomeScreen.js
│   │   ├── 📝 RegisterScreen.js
│   │   ├── ✅ SuccessScreen.js
│   │   └── 🔧 AdminScreen.js
│   └── 📁 styles/
│       └── 🎨 theme.js
└── 📄 .gitignore
```

## 🎉 Sucesso!

Se tudo deu certo, você terá:
- ✅ Repositório criado no GitHub
- ✅ Todos os arquivos enviados
- ✅ README.md exibindo na página inicial
- ✅ Projeto pronto para compartilhar

## 📞 Precisa de Ajuda?

Se tiver algum problema:
1. Verifique se o Git está instalado
2. Verifique se está logado no GitHub
3. Verifique se o nome do usuário está correto
4. Consulte a documentação do Git: https://git-scm.com/doc

---

**🔥 Support Fire - Agora no GitHub! 🔥**
