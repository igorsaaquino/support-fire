# 🚀 Instruções para Configurar o GitHub

## 📋 Pré-requisitos
1. **Instalar Git** (se não estiver instalado)
   - Baixe em: https://git-scm.com/download/win
   - Instale com as configurações padrão

2. **Criar conta no GitHub** (se não tiver)
   - Acesse: https://github.com
   - Crie uma conta gratuita

## 🔧 Configuração do Git

### 1. Configurar Git (primeira vez)
```bash
git config --global user.name "Seu Nome"
git config --global user.email "seu.email@exemplo.com"
```

### 2. Inicializar o repositório
```bash
git init
```

### 3. Adicionar todos os arquivos
```bash
git add .
```

### 4. Fazer o primeiro commit
```bash
git commit -m "Initial commit: Support Fire app completo"
```

## 📤 Criar Repositório no GitHub

### 1. Acesse o GitHub
- Vá para https://github.com
- Clique em "New repository"

### 2. Configure o repositório
- **Nome**: `support-fire`
- **Descrição**: `App mobile para cadastro do curso de Brigadista Mirim - Support Fire`
- **Visibilidade**: Public (gratuito)
- **NÃO** marque "Add a README file" (já temos um)
- **NÃO** marque "Add .gitignore" (já temos um)

### 3. Clique em "Create repository"

## 🔗 Conectar com o GitHub

### 1. Adicionar o repositório remoto
```bash
git remote add origin https://github.com/SEU_USUARIO/support-fire.git
```
*Substitua SEU_USUARIO pelo seu nome de usuário do GitHub*

### 2. Fazer push para o GitHub
```bash
git branch -M main
git push -u origin main
```

## ✅ Verificação
Após seguir os passos, você deve ver todos os arquivos do projeto no seu repositório GitHub!

## 📱 Próximos Passos
1. **Instalar dependências**: `npm install`
2. **Testar o app**: `npx react-native run-android` ou `npx react-native run-ios`
3. **Configurar para publicação** nas lojas

## 🆘 Problemas Comuns

### Erro de autenticação
Se der erro de autenticação, use um Personal Access Token:
1. GitHub → Settings → Developer settings → Personal access tokens
2. Generate new token
3. Use o token como senha quando pedir

### Git não reconhecido
- Reinicie o terminal após instalar o Git
- Ou adicione o Git ao PATH do sistema

## 📞 Suporte
Se tiver dúvidas, consulte a documentação do Git: https://git-scm.com/doc

