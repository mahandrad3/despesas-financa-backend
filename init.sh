#!/bin/bash

# Verifica se o arquivo app.jar existe no diretório /app
if [ -f /app/app.jar ]; then
    echo "Arquivo app.jar encontrado. Iniciando aplicação..."
    
    # Carrega variáveis de ambiente do arquivo .env (se estiver usando dotenv)
    if [ -f /app/.env ]; then
        source /app/.env
    else
        echo "Erro ao ler .env"
        exit 1
    fi
    
    # Exemplo de utilização das variáveis no script
    java -jar -Dspring.mail.host="$MAIL_HOST" -Dspring.mail.port="$MAIL_PORT" /app/app.jar
else
    echo "ERRO: Arquivo app.jar não encontrado no diretório /app."
    exit 1  # Sai do script com código de erro 1
fi

