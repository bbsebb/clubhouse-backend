# gestion-club-spring-app/Dockerfile
FROM openjdk:20
WORKDIR /app
COPY infrastructure/build/libs/infrastructure.jar /app
COPY book-domain/build/libs/book-domain.jar /app
COPY game-domain/build/libs/game-domain.jar /app
CMD ["java", "-cp","/app/*", "org.springframework.boot.loader.JarLauncher"]