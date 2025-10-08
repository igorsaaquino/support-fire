# 🚀 Comandos Git - Support Fire

## 📋 Comandos Rápidos (Copie e Cole)

### 1. 🔧 Configurar Git (primeira vez)
```bash
git config --global user.name "Seu Nome"
git config --global user.email "seu.email@exemplo.com"
```

### 2. 📁 Inicializar e Configurar
```bash
git init
git add .
git commit -m "Initial commit: Support Fire app completo"
```

### 3. 🔗 Conectar com GitHub
```bash
git remote add origin https://github.com/SEU_USUARIO/support-fire.git
git branch -M main
git push -u origin main
```

## 🎯 Processo Completo

### Passo 1: Criar Repositório no GitHub
1. Acesse: https://github.com/new
2. Nome: `support-fire`
3. Descrição: `App mobile para cadastro do curso de Brigadista Mirim - Support Fire`
4. Público ✅
5. **NÃO** marque "Add a README file"
6. Clique em "Create repository"

### Passo 2: Executar Comandos
```bash
# Configurar Git (se necessário)
git config --global user.name "Seu Nome"
git config --global user.email "seu.email@exemplo.com"

# Inicializar repositório
git init
git add .
git commit -m "Initial commit: Support Fire app completo"

# Conectar com GitHub (SUBSTITUA SEU_USUARIO)
git remote add origin https://github.com/SEU_USUARIO/support-fire.git
git branch -M main
git push -u origin main
```

## 🔄 Comandos para Atualizações Futuras

### 📤 Enviar alterações
```bash
git add .
git commit -m "Descrição da alteração"
git push
```

### 📥 Baixar alterações
```bash
git pull
```

### 🔍 Ver status
```bash
git status
```

## 🆘 Solução de Problemas

### ❌ Erro: "git não é reconhecido"
**Solução**: Instale o Git em https://git-scm.com/download/win

### ❌ Erro de autenticação
**Solução**: Use Personal Access Token:
1. GitHub → Settings → Developer settings → Personal access tokens
2. Generate new token (classic)
3. Selecione "repo" (acesso completo)
4. Use o token como senha

### ❌ Erro: "Repository not found"
**Solução**: Verifique se o nome do usuário está correto

### ❌ Erro: "Permission denied"
**Solução**: Verifique se você tem permissão no repositório

## ✅ Verificar se Funcionou

Após executar os comandos, acesse:
https://github.com/SEU_USUARIO/support-fire

Você deve ver todos os arquivos do projeto!

## 📱 Próximos Passos

1. **Instalar dependências**: `npm install`
2. **Testar o app**: `npx react-native run-android`
3. **Configurar para publicação** nas lojas

---

**🔥 Support Fire - Agora no GitHub! 🔥**
