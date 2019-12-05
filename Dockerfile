FROM openjdk:8-jdk-alpine
MAINTAINER anirudh.chhabra@publicissapient.com

ARG APP_ENV
ARG APP_NAME
ARG DEPLOY_ARTIFACT_DIR
ARG ARTIFACT_NAME
ARG SERVICE_PORT=8443

RUN apk update && apk add openssl

#
# create runtime user and group "${APP_NAME}"
#
RUN addgroup -S ${APP_NAME} -g 1005 &&\
 adduser -u 1005 -S ${APP_NAME} -g  ${APP_NAME} -h /home/${APP_NAME} -D  /home/${APP_NAME} -s /bin/bash &&\
 chmod 755 /home/${APP_NAME}

RUN mkdir -p /apps
RUN chown -R ${APP_NAME}:${APP_NAME} /apps
RUN     chmod -R 777 /apps

RUN mkdir -p /logs/${APP_NAME} && \
        chown -R ${APP_NAME}:${APP_NAME} /logs/${APP_NAME} && \
        chmod -R 755 /logs/${APP_NAME}

#
# Copy Deploy Artifacts
#USER ${APP_NAME}
WORKDIR /apps/${APP_NAME}
COPY ${DEPLOY_ARTIFACT_DIR}/${ARTIFACT_NAME} /apps/app.jar
RUN ls -ltr /apps/
RUN touch /home/${APP_NAME}/.bashrc \
        && echo export ENV=${APP_ENV} >> /home/${APP_NAME}/.bashrc \
        && echo export APP_ENV=${APP_ENV} >> /home/${APP_NAME}/.bashrc \
		&& echo export SPRING_PROFILES_ACTIVE=${APP_ENV} >> /home/${APP_NAME}/.bashrc

ENV APP_NAME=${APP_NAME}
ENV ENV=${APP_ENV}
ENV APP_ENV=${APP_ENV}
ENV PATH /usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
ENV SPRING_PROFILES_ACTIVE=${APP_ENV}
VOLUME /tmp

EXPOSE ${SERVICE_PORT}

RUN chmod  755 /apps/*.jar
USER ${APP_NAME}
ENTRYPOINT ["java","-jar","/apps/app.jar"]

