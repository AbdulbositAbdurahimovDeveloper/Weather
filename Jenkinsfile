pipeline {
    // Pipeline asosiy Jenkins agent'ida ishlaydi
    agent any

    // 'Global Tool Configuration'dagi sozlamalarni chaqirish
    tools {
        // Bu nom 'Global Tool Configuration'dagi 'Name' maydoniga to'liq mos kelishi kerak
        maven 'maven'
        jdk 'JDK24'
        dockerTool 'docker-cli'
    }

    // Pipeline uchun o'zgaruvchilar
    environment {
        IMAGE_NAME = "weather/app:${env.BUILD_NUMBER}" // Har bir build uchun unikal teg
        LATEST_IMAGE = "weather/app:latest"         // Har doim eng so'nggi versiya uchun teg
        CONTAINER_NAME = 'weather-container'       // Ishga tushiriladigan container nomi
    }

    stages {
        stage('1. Clone Repo') {
            steps {
                // Tozalik uchun avvalgi build qoldiqlarini o'chiramiz
                cleanWs()
                echo 'Klonlash boshlandi...'
                git url: 'https://github.com/AbdulbositAbdurahimovDeveloper/Weather.git', branch: 'main'
                echo 'Repo muvaffaqiyatli olindi.'
            }
        }

        stage('2. Build JAR') {
            steps {
                echo 'JAR fayl qurilmoqda...'
                // 'mvn' buyrug'i endi `tools` bloki tufayli topiladi
                sh 'mvn clean package -DskipTests'
                echo 'JAR fayl muvaffaqiyatli qurildi.'
            }
        }

        stage('3. Build Docker Image') {
            steps {
                echo "Docker image qurilmoqda: ${IMAGE_NAME}"
                // JAR faylni joriy direktoriyaga nusxalash
                sh 'cp target/*.jar .'

                // Image'ni qurish va ikkita teg bilan belgilash
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
    } // stages bloki shu yerda yopiladi

    // Pipeline qanday tugashidan qat'iy nazar ishlaydigan blok
    post {
        always {
            echo 'Pipeline tugadi. Ish joyini tozalash...'
            // Build'dan qolgan fayllarni o'chirish
            cleanWs()
        }
    }
}