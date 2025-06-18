# Etapa de construcción
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de empaquetado (runtime)
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
# Copia el JAR de la etapa 'build' a la etapa actual
COPY --from=build /app/target/fleetguard360.jar fleetguard360.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","fleetguard360.jar"]