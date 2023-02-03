pipeline {
    agent any

    tools {
        maven "maven"
    }

    stages {
            stage('Run API Smoke Test') {
                when {
                    expression {
                        BRANCH_NAME == "master"
                    }
                }
                steps {
                     // Run Maven on a Unix agent.
                    sh "mvn verify -Dcucumber.filter.tags='@Smoke'"
                }
            }
            stage('Run API Regression Tests') {
                steps {
                     sh "mvn verify -Dcucumber.filter.tags='@Regression'"
                }
            }
            post {
                always {
                   publishHTML (target : [
                                           allowMissing: false,
                                           alwaysLinkToLastBuild: false,
                                           keepAll: true,
                                           reportDir: 'target/site/serenity/',
                                           reportFiles: 'capabilities.html',
                                           reportName: 'Serenity Report',
                                           reportTitles: 'API Test for Auction App',
                                           useWrapperFileDirectly: true
                                           ])
                }
            }
    }
}