pipeline {
    agent any
    parameters {
        string(
            name: "after", 
            defaultValue: "", 
            description: "Log created after, ex: 2024-09-07, default is 10 days ago from today",
        )
        string(
            name: "before", 
            defaultValue: "", 
            description: "Log created before, ex: 2024-09-07, default is today",
        )
    }
    
    stages {
        stage('Build') {
            steps {
                script {
                    echo "Building ${env.BRANCH_NAME} version of the app..."
                }
                sh 'mvn clean package'
            }
        }
    
        stage('Docker Build') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'main') {
                        echo 'Building Docker image for Production...'
                        sh 'docker build -t simple-java-app:prod --build-arg ENVIRONMENT=production .'
                    } else {
                        echo 'Building Docker image for Development...'
                        sh 'docker build -t simple-java-app:dev --build-arg ENVIRONMENT=development .'
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'main') {
                        echo 'Deploying to Production...'
                        sh 'docker rm -f java-app-prod || true'
                        sh 'docker run -d -p 8082:8080 --name java-app-prod -e ENVIRONMENT=production simple-java-app:prod'
                    } else {
                        echo 'Deploying to Development...'
                        sh 'docker rm -f java-app-dev || true'
                        sh 'docker run -d -p 8081:8080 --name java-app-dev -e ENVIRONMENT=development simple-java-app:dev'
                    }
                }
            }
        }
    }
}
