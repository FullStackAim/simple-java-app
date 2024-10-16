def available_environments = ["dev", "rec", "preprod", "production"]
def now = new Date()
def pipelineParameters = [
            string(name: "after", defaultValue: "", description: "log created after, ex: 2024-09-07, default today minus 10 days", trim: true ),
            string(name: "before", defaultValue: "", description: "log created beforte, ex: 2024-09-07, default is today", trim: true ),
        ]

pipeline {
    agent any
    properties([
        parameters(pipelineParameters)
])
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
