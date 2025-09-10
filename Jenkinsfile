pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'JDK24'
    }

    environment {
        IMAGE_NAME = "weather/app:${env.BUILD_NUMBER}"
        LATEST_IMAGE = "weather/app:latest"
        CONTAINER_NAME = 'weather-container'
    }

    stages {
        stage('1. Clone Repo') {
            steps {
                cleanWs()
                echo 'Klonlash boshlandi...'
                git url: 'https://github.com/AbdulbositAbdurahimovDeveloper/Weather.git', branch: 'main'
                echo 'Repo muvaffaqiyatli olindi.'
            }
        }

        stage('2. Build JAR') {
            steps {
                echo 'JAR fayl qurilmoqda...'
                sh 'mvn clean package -DskipTests'
                echo 'JAR fayl muvaffaqiyatli qurildi.'
            }
        }

        stage('3. Build Docker Image') {
            steps {
                echo "Docker image qurilmoqda: ${IMAGE_NAME}"
                sh 'cp target/*.jar .'
                sh "docker build -t ${IMAGE_NAME} -t ${LATEST_IMAGE} ."
                echo "Docker image muvaffaqiyatli qurildi: ${IMAGE_NAME} va ${LATEST_IMAGE}"
            }
        }

        stage('4. Deploy Application') {
            steps {
                echo "Container ishga tushirilmoqda: ${CONTAINER_NAME}"
                sh "docker rm -f ${CONTAINER_NAME} || true"
                sh "docker run -d --name ${CONTAINER_NAME} -p 8808:8080 --network app-network ${LATEST_IMAGE}"
                echo "Ilova http://localhost:8808 manzilida ishga tushdi."
            }
        }
    }

    post {
        always {
            echo 'Pipeline tugadi. Ish joyini tozalash...'
            cleanWs()
        }
    }
}
