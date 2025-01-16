FROM bellsoft/liberica-openjdk-alpine
RUN apk --no-cache --repository=http://dl-cdn.alpinelinux.org/alpine/v3.20/main/ --allow-untrusted add curl jq
WORKDIR /home/selenium-docker
ADD target/docker-resources ./
ADD runner.sh runner.sh
ENTRYPOINT sh runner.sh