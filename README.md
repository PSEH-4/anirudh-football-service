Sample url:
Valid Response
http://localhost:8080/football/standings?countryName=England&leagueName=Championship&teamName=West%20Brom

Team Not found
http://localhost:8080/football/standings?countryName=England&leagueName=Championship&teamName=West%20Bro

Invalid Request:
http://localhost:8080/football/standings?countryName=England&leagueName=Championship

Docker Build:
docker build -t ${REPO_URL}/${BUILD_NAME}:${BUILD_IMAGE_VERSION} --build-arg APP_ENV=${ENV} --build-arg APP_NAME=${BUILD_NAME} --build-arg DEPLOY_ARTIFACT_DIR=${BUILD_DIR} --build-arg SERVICE_PORT=${SERVICE_PORT} --build-arg ARTIFACT_NAME=${BUILD_NAME}.jar --no-cache . --rm=true

Replace with actual value:
docker build -t football-microservice:1.0.0 --build-arg APP_ENV=prod --build-arg APP_NAME=football-ms --build-arg DEPLOY_ARTIFACT_DIR=target --build-arg SERVICE_PORT=8080 --build-arg ARTIFACT_NAME=football-standing-ms-0.0.1-SNAPSHOT --no-cache . --rm=true