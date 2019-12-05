REPO_URL=football-standing
ENV=prod
APP_NAME=football-standing-ms
DEPLOY_ARTIFACT_DIR=target
SERVICE_PORT=8080
BUILD_NAME=football-standing-ms-0.0.1-SNAPSHOT
BUILD_IMAGE_VERSION=1.0.0


docker build -t ${REPO_URL}/${BUILD_NAME}:${BUILD_IMAGE_VERSION} --build-arg APP_ENV=${ENV} --build-arg APP_NAME=${BUILD_NAME} --build-arg DEPLOY_ARTIFACT_DIR=${BUILD_DIR} --build-arg SERVICE_PORT=${SERVICE_PORT} --build-arg ARTIFACT_NAME=${BUILD_NAME}.jar --no-cache . --rm=true