# 🚀 Script para Criar Repositório GitHub - Support Fire
# Execute este script no PowerShell como Administrador

Write-Host "🔥 Support Fire - Criando Repositório no GitHub" -ForegroundColor Red
Write-Host "=================================================" -ForegroundColor Red

# Verificar se o Git está instalado
try {
    $gitVersion = git --version
    Write-Host "✅ Git encontrado: $gitVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Git não encontrado!" -ForegroundColor Red
    Write-Host "📥 Instale o Git em: https://git-scm.com/download/win" -ForegroundColor Yellow
    Write-Host "🔄 Reinicie o terminal após a instalação" -ForegroundColor Yellow
    exit 1
}

# Verificar se estamos em um repositório Git
if (Test-Path ".git") {
    Write-Host "✅ Repositório Git já inicializado" -ForegroundColor Green
} else {
    Write-Host "🔄 Inicializando repositório Git..." -ForegroundColor Yellow
    git init
}

# Verificar configuração do Git
Write-Host "`n🔧 Verificando configuração do Git..." -ForegroundColor Yellow
$userName = git config --global user.name
$userEmail = git config --global user.email

if (-not $userName -or -not $userEmail) {
    Write-Host "⚠️  Configuração do Git não encontrada!" -ForegroundColor Yellow
    Write-Host "🔧 Configure o Git primeiro:" -ForegroundColor Yellow
    Write-Host "   git config --global user.name 'Seu Nome'" -ForegroundColor Cyan
    Write-Host "   git config --global user.email 'seu.email@exemplo.com'" -ForegroundColor Cyan
    Write-Host "`n🔄 Execute os comandos acima e rode este script novamente" -ForegroundColor Yellow
    exit 1
} else {
    Write-Host "✅ Git configurado: $userName <$userEmail>" -ForegroundColor Green
}

# Adicionar todos os arquivos
Write-Host "`n📁 Adicionando arquivos ao Git..." -ForegroundColor Yellow
git add .

# Verificar se há arquivos para commit
$status = git status --porcelain
if (-not $status) {
    Write-Host "✅ Todos os arquivos já estão commitados" -ForegroundColor Green
} else {
    Write-Host "📝 Fazendo commit dos arquivos..." -ForegroundColor Yellow
    git commit -m "Initial commit: Support Fire app completo"
}

# Solicitar nome de usuário do GitHub
Write-Host "`n🌐 Configuração do GitHub" -ForegroundColor Yellow
Write-Host "=================================" -ForegroundColor Yellow
$githubUser = Read-Host "Digite seu nome de usuário do GitHub"

if (-not $githubUser) {
    Write-Host "❌ Nome de usuário é obrigatório!" -ForegroundColor Red
    exit 1
}

# URL do repositório
$repoUrl = "https://github.com/$githubUser/support-fire.git"

Write-Host "`n🔗 Configurando repositório remoto..." -ForegroundColor Yellow
Write-Host "URL: $repoUrl" -ForegroundColor Cyan

# Remover remote existente se houver
git remote remove origin 2>$null

# Adicionar remote
git remote add origin $repoUrl

# Renomear branch para main
git branch -M main

Write-Host "`n📤 Enviando para o GitHub..." -ForegroundColor Yellow
Write-Host "⚠️  IMPORTANTE: Você precisa criar o repositório no GitHub primeiro!" -ForegroundColor Red
Write-Host "`n📋 Passos para criar o repositório:" -ForegroundColor Yellow
Write-Host "1. Acesse: https://github.com/new" -ForegroundColor Cyan
Write-Host "2. Nome: support-fire" -ForegroundColor Cyan
Write-Host "3. Descrição: App mobile para cadastro do curso de Brigadista Mirim - Support Fire" -ForegroundColor Cyan
Write-Host "4. Público ✅" -ForegroundColor Cyan
Write-Host "5. NÃO marque 'Add a README file'" -ForegroundColor Cyan
Write-Host "6. Clique em 'Create repository'" -ForegroundColor Cyan

$continuar = Read-Host "`nPressione ENTER quando tiver criado o repositório no GitHub"

# Tentar fazer push
Write-Host "`n🚀 Enviando arquivos para o GitHub..." -ForegroundColor Yellow
try {
    git push -u origin main
    Write-Host "`n🎉 SUCESSO! Repositório criado no GitHub!" -ForegroundColor Green
    Write-Host "🔗 Acesse: https://github.com/$githubUser/support-fire" -ForegroundColor Cyan
    Write-Host "`n✅ Projeto Support Fire está no GitHub!" -ForegroundColor Green
} catch {
    Write-Host "`n❌ Erro ao enviar para o GitHub!" -ForegroundColor Red
    Write-Host "🔧 Possíveis soluções:" -ForegroundColor Yellow
    Write-Host "1. Verifique se o repositório foi criado no GitHub" -ForegroundColor Cyan
    Write-Host "2. Verifique se você tem permissão no repositório" -ForegroundColor Cyan
    Write-Host "3. Use Personal Access Token se necessário" -ForegroundColor Cyan
    Write-Host "4. Execute manualmente: git push -u origin main" -ForegroundColor Cyan
}

Write-Host "`n📚 Próximos passos:" -ForegroundColor Yellow
Write-Host "1. Instalar dependências: npm install" -ForegroundColor Cyan
Write-Host "2. Testar o app: npx react-native run-android" -ForegroundColor Cyan
Write-Host "3. Configurar para publicação nas lojas" -ForegroundColor Cyan

Write-Host "`n🔥 Support Fire - Projeto no GitHub! 🔥" -ForegroundColor Red
