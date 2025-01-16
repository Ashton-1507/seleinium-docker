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
                sh "docker build -t ashton152/selenium ."
            }
        }
        stage('push image'){
            steps{
                sh "docker push ashton152/selenium"
            }
        }
    }

}