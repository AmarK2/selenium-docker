pipeline {
    agent {
            label 'builder'
        }

    environment {
        COMMIT_HASH = ''
    }

    stages {
        stage('Get Commit Hash') {
            steps {
                script {
                    COMMIT_HASH = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                    env.COMMIT_HASH = COMMIT_HASH
                }
            }
        }

        stage('Build Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image') {
            steps {
                sh 'docker build -t=amark21/selenium:latest .'
            }
        }

        stage('Push Image') {
            environment {
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps {
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh 'docker push amark21/selenium:latest'
                sh "docker tag amark21/selenium:latest amark21/selenium:${COMMIT_HASH}"
                sh "docker push amark21/selenium:${COMMIT_HASH}"
            }
        }
    }

    post {
        always {
            sh "docker logout"
        }
    }
}
