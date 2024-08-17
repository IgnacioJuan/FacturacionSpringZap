#inicia con la imagen base que contiene Java runtime
FROM openjdk:17-jdk-alpine

# se cambia el jar del microservicio a imagen
COPY target/factura.jar factura.jar

#se ejecuta el microservicio
ENTRYPOINT ["java","-jar","/factura.jar"]