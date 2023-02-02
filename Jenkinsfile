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
                            publishHTML target :
                            [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false,
                            reportDir: 'target/site/serenity/', reportFiles: 'index.html', reportName: 'Serenity Report 1',
                            reportTitles: 'Smoke API Test for Auction App', useWrapperFileDirectly: true]

                        }
                    }
                }
                stage('Run API Regression Tests') {
                    steps {
                         sh 'mvn verify -Dcucumber.filter.tags="@Regression"'
                    }
                    post {
                          // If Maven was able to run the tests, even if some of the test
                          // failed, record the test results and archive the jar file.
                          always {
                              publishHTML target :
                              ([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false,
                              reportDir: 'target/site/serenity/', reportFiles: 'index.html', reportName: 'Serenity Report 2',
                              reportTitles: 'Regression API Tests for Auction App', useWrapperFileDirectly: true])
                          }
                    }
                }
            }
        }

    }
}