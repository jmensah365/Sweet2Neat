pipeline {
    agent any

    stages{
        stage('Build Frontend'){
            steps{
                sh "echo Building frontend"
                sh "cd project-one-frontend && npm install && npm run build"
                
            }
        
            }
        stage('Deploy Frontend'){
            steps{
                script{
                    try {
                        withAWS(region: 'us-east-1', credentials: 'AWS_CREDENTIALS'){
                        sh "aws s3 sync project-one-frontend/dist s3://cim-frontend" 
                        }
                    }catch (Exception e) {
                            echo "${e}"
                            throw e
                    }   
                }
            }
        }
        stage('Build Backend'){
            steps{
                sh "cd project-one && mvn clean install && ls target/"
            }
        }
        stage('Test Backend'){
            steps{
                sh "cd Testing/projec2-testing && mvn clean test"
            }
        }
        stage('Deploy Backend'){
            steps{
                script{
                    withAWS(region: 'us-east-1', credentials: 'AWS_CREDENTIALS'){
                        sh 'pwd'
                        sh "aws s3 cp project-one/target/*.jar s3://cim-backend"
                        // sh "echo 'aws elasticbeanstalk create-application-version --application-name myName --version-label 0.0.1 --source-bundle S3Bucket=\"bjgomes-bucket-sdet-backend\",S3Key=\"demo-1.0-SNAPSHOT.jar\"'"
                        // sh "echo 'aws elasticbeanstalk update-environment --environment-name myName --version-label 0.0.1'"
                    }  
                }   
            }
        }
    }
}
