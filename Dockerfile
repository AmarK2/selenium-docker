FROM bellsoft/liberica-openjdk-alpine

#add curl and jq
RUN apk add curl jq

#workspace
WORKDIR /home/selenium-docker

#add required files
ADD target/docker-resources ./
ADD runner.sh runner.sh

#run the tests
ENTRYPOINT sh runner.sh
