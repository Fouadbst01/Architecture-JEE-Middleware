# image de base contient java 8
FROM openjdk:8

LABEL maintainer="fouadelbssita@gmail.com"

#ajouter un volume point a /tmp
#VOLUME /tmp

#exposer le port 8080 au exterieur
#EXPOSE 8008


#path du jar file qui sera executer dans notre container
ARG JAVA_FILE=target/Student_Management-0.0.1-SNAPSHOT.jar

#ajouter le fichier dans notre volume
ADD ${JAVA_FILE} Student_Management.jar

#execute jar file
ENTRYPOINT ["java","-jar","/Student_Management.jar"]