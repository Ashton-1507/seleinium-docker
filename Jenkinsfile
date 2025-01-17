pipeline{
    agent any
    stages{
        stage('Build Jar'){
            steps{
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Docker'){
            steps{
                sh "docker build -t ashton152/selenium:latest ."
            }
        }
        stage('push image'){
            environment{
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh 'docker push ashton152/selenium:latest'
                sh "docker tag ashton152/selenium:latest ashton152/selenium:${env.BUILDNUMBER}"
                sh "docker push ashton152/selenium:${env.BUILDNUMBER}"
            }
        }
    }
}