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
                
                dir('project-one') {
                    withSonarQubeEnv('SonarCloud') {
                        sh '''
                            mvn sonar:sonar \
                            -Dsonar.projectKey=Candy-Inventory-Group_Sweet2Neat \
                            -Dsonar.projectName=Sweet2Neat-project-one \
                            -Dsonar.java.binaries=target/classes \
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                        '''
                    }
                }
                
                sh "cd project-one && mvn clean install && ls target/"
            }
        }
        stage('Test Backend'){
            steps{
                sh "cd project-one && mvn test"
            }
        }
        
        stage('Selenium/Cucumber Tests'){
            steps{
                sh "cd Testing/project2-testing && mvn verify"
            }
        }

        stage('Run JMeter Tests') {
            steps {
                //Run Jmeter tests without a GUI
                sh '/home/ec2-user/jmeter/apache-jmeter-5.6.3/bin/jmeter -n -t /home/ec2-user/Sweet2Neat/JmeterTestPlans/reqres-test.jmx -l /home/ec2-user/Sweet2Neat/JmeterTestResults/results.jtl'
                sh 'cat /home/ec2-user/Sweet2Neat/JmeterTestResults/results.jtl'
            }
        }

        stage('Publish test results') {
            steps{
                //publishing test results
                //should be in xml format
                perfReport sourceDataFiles: '/home/ec2-user/Sweet2Neat/JmeterTestResults/results.jtl'
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
