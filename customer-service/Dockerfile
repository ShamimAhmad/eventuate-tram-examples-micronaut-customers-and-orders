FROM java:openjdk-8u111-alpine
RUN apk --no-cache add curl
HEALTHCHECK --start-period=30s --interval=5s CMD curl -f http://localhost:8080/health || exit 1
CMD java ${JAVA_OPTS} -jar customer-service-*.jar
COPY build/libs/customer-service-*.jar .
