FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
# due to a bug in Jersey making it unable
# to scan nested JAR-files. so I have to unzip the jar and copy
# files seprately to the docker image.
# refer to: https://www.ivankrizsan.se/2016/12/06/jersey-and-spring-boot-standalone-jar-files/
ARG DEPENDENCY=build/libs/my_jar
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
#java -jar build/libs/booking-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-cp","app:app/lib/*","com.hotel.booking.BookingApplication"]