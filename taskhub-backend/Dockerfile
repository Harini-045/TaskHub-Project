# ===== STAGE 1: Build the app =====
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies first (for caching)
COPY pom.xml .

# Copy the rest of the project
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# ===== STAGE 2: Run the app =====
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy jar file from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose backend port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

