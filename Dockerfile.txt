# syntax=docker/dockerfile:1
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
# Copia pom e resolve dependências em cache
COPY pom.xml .
RUN ./_mvnw_exist_ || true
# Copia wrapper e scripts (caso existam)
COPY mvnw mvnw
COPY .mvn .mvn
RUN chmod +x mvnw
# Baixa dependências sem código para cache
RUN ./mvnw -q -DskipTests dependency:go-offline
# Copia código
COPY src src
# Empacota
RUN ./mvnw -q -DskipTests package

FROM eclipse-temurin:21-jdk
WORKDIR /
# Copia o jar gerado do estágio de build (ajuste o nome se necessário)
COPY --from=build /app/target/*-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
