# 1-qadam: Asosiy image
FROM openjdk:24

# 2-qadam: JAR faylni "app.jar" deb qayta nomlab, nusxalash
COPY target/*.jar app.jar

# 3-qadam: "app.jar" ni ishga tushirish
ENTRYPOINT ["java", "-jar", "app.jar"]