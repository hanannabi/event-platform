# -------- Stage 1: Build the application --------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml first (dependency caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the jar
RUN mvn clean package -DskipTests


# -------- Stage 2: Run the application --------
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy only the built jar from previous stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
