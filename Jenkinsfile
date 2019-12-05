#!/usr/bin/env groovy
pipeline {
	agent any

	tools {
		maven 'Maven'
	}

	options {
		buildDiscarder(logRotator(daysToKeepStr: '5', numToKeepStr: '5'))
	}

	environment {
		BUILD_NUMBER = "${env.BUILD_NUMBER}"
		TIMESTAMP = new java.text.SimpleDateFormat('yyyyMMddHHmmss').format(new Date())
	}

	// ###############
	// PIPELINE STAGES
	// ###############

	stages {

		// #############
		// CONFIGURATION
		// #############

		stage('Configuration') {
			steps {
				echo "Setting up environment vars..."
				script {
					GIT_COMMIT_SHORT = sh(
					 script: "printf \$(git rev-parse --short ${GIT_COMMIT})",
					 returnStdout: true
					)

					env.ENV = "prod"

					// Configurations from config.json file
					env.BUILD_CONFIG = "./config/jenkins/config.json"
					echo "Build Config file : ${BUILD_CONFIG}"
					def config = readJSON file: "${BUILD_CONFIG}"
					env.BUILD_NAME = config.project_name
					env.SERVICE_PORT = config.service_port		


					// Global Environment Variables
					env.REPO_URL = config.service_port


					// Job Specific Configurations
					env.BUILD_IMAGE_VERSION = "${BUILD_NUMBER}-${GIT_COMMIT_SHORT}"
					env.ARTIFACT_NAME = "${BUILD_NAME}"
					env.IMAGE_NAME =  "${BUILD_NAME}:${BUILD_IMAGE_VERSION}"
					env.BUILD_DIR = "target"
					env.BUILD = "${BUILD_NAME}:${BUILD_IMAGE_VERSION}"

				}
			}
		}

		stage('Maven Build') {
			steps {
				script {
					sh "mkdir -p ${BUILD_DIR}"
					sh "mvn clean install -DskipTests -U -B -Dproject.build.directory=${BUILD_DIR}"
					sh "cp -f ${BUILD_DIR}/*.jar ./"
				}
			}
		}

		// ##########
		// UNIT TESTS
		// ##########

		stage('Unit Tests') {
			steps {
				sh 'mvn test'
			}
			post {
				always {
					junit "${BUILD_DIR}/surefire-reports/*.xml"
				}
			}
		}

		// #########
		// SONARQUBE
		// #########
/*
		stage('SonarQube Analysis') {
			steps {
			   script {
					withSonarQubeEnv('sonarqube') {
						sh 'mvn sonar:sonar'
					}
				}
			}
		}
		

		// ######################
		// SONARQUBE QUALITY GATE
		// ######################

		stage("SonarQube Quality Gate") {
			steps {
				script {
					timeout(time: 5, unit: 'MINUTES') {
						def qg = waitForQualityGate()
						if (qg.status != 'OK') {
							error "Pipeline aborted due to quality gate failure: ${qg.status}"
						}
					}
				}
			}
		}
		

		// ######
		// DOCKER
		// ######

		stage('Docker') {
			// only deploy master branch
			when {
				anyOf {
					branch 'dev*'
					branch 'master'
				}
			}

			agent none

			steps {
				// Build Docker Image
				script {
					echo "Building Docker Image..."
					sh "docker build -t ${REPO_URL}/${BUILD_NAME}:${BUILD_IMAGE_VERSION} --build-arg APP_ENV=${ENV} --build-arg APP_NAME=${BUILD_NAME} --build-arg DEPLOY_ARTIFACT_DIR=${BUILD_DIR} --build-arg SERVICE_PORT=${SERVICE_PORT} --build-arg ARTIFACT_NAME=${BUILD_NAME}.jar --no-cache . --rm=true"
				}


				// Publish Docker Image
				script {
					docker login --username=anirudhchhabra --email=anirudh.chhabra@gmail.com
					sh "docker tag ${REPO_URL}/${BUILD_NAME}:${BUILD_IMAGE_VERSION} ${REPO_URL}/${BUILD_NAME}:latest"
					sh "docker push ${REPO_URL}/${BUILD_NAME}:${BUILD_IMAGE_VERSION}"
					sh "docker push ${REPO_URL}/${BUILD_NAME}:latest"
					echo "Docker image pushed successfully. Deleting the local image."
					sh "docker rmi ${REPO_URL}/${BUILD_NAME}"
					echo "Local image is deleted successfully."
				}
			}
		}
*/
		// ##########
		// DEPLOYMENT
		// ##########

		stage('Deploy to AWS') {
			// only deploy master and develop branches
			when {
				anyOf {
					branch 'dev*'
					branch 'master'
				}
			}

			steps {
				script {
					echo "deploying to AWS EC2"
					scp target/ remote_username@10.10.0.2:/remote/directory
				}
			}
		}

	}

	post {
		always {
			echo 'Build finished.  Cleaning up workspace....'
			deleteDir()
		}
		success {
			echo 'Success!'
		}
		unstable {
			echo 'I am unstable.  :/'
		}
		failure {
			echo 'OH NO!  I am a failure.'
		}
		changed {
			echo 'Something changed...'
		}
	}
}
