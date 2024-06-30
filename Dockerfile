# Etapa de build
FROM maven:3.8.6-openjdk-18 AS build

COPY . /app
WORKDIR /app

# Executa o comando Maven
RUN mvn clean install -DskipTests

# Imprime o conteúdo do diretório /app/target
RUN ls -l /app/target

# Etapa final
FROM openjdk:18-jdk

EXPOSE 8080

ENV USERNAME_SMTP=${USERNAME_SMTP}
ENV PASSWORD_SMTP=${PASSWORD_SMTP}
ENV DATABASE_URL=${DATABASE_URL}
ENV DATABASE_USERNAME=${DATABASE_USERNAME}
ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}
ENV JWT_SECRET=${JWT_SECRET}

# Copia o app.jar da etapa de build
COPY --from=build /app/target/despesas-financas-backend-0.0.1-SNAPSHOT.jar /app/app.jar


COPY src/main/resources/application.yml /app/application.yml
COPY .env /app/.env
COPY init.sh /app/init.sh

RUN chmod +x /app/init.sh

WORKDIR /app

ENTRYPOINT ["./init.sh"]

