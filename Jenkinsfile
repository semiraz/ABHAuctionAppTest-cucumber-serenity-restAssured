pipeline {
    agent any

    tools {
        maven "maven"
    }

    stages {
        stage('API Testing') {
            parallel {
                stage('Run API Smoke Test') {
                    steps {
                         // Run Maven on a Unix agent.
                        sh 'mvn verify -Dcucumber.filter.tags="@Smoke"'
                    }
                    post {
                        always {
                           publishHTML (target : [
                                                   allowMissing: false,
                                                   alwaysLinkToLastBuild: false,
                                                   keepAll: true,
                                                   reportDir: 'target/site/serenity/',
                                                   reportFiles: 'capabilities.html',
                                                   reportName: 'Serenity Report Smoke',
                                                   reportTitles: 'Smoke API Test for Auction App',
                                                   useWrapperFileDirectly: true
                                                   ])
                        }
                    }
                }
                stage('Run API Regression Tests') {
                    steps {
                         sh 'mvn verify -Dcucumber.filter.tags="@Regression"'
                    }
                    post {
                          always {
                                  publishHTML (target : [
                                                          allowMissing: false,
                                                          alwaysLinkToLastBuild: false,
                                                          keepAll: true,
                                                          reportDir: 'target/site/serenity/',
                                                          reportFiles: 'capabilities.html',
                                                          reportName: 'Serenity Report Regression',
                                                          reportTitles: 'Regression API Test for Auction App',
                                                          useWrapperFileDirectly: true
                                                          ])
                          }
                    }
                }
            }
        }

    }
}