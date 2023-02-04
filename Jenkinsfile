pipeline {
    agent any

    tools {
        maven "maven"
    }

    stages {
            stage('Run API Smoke Test') {
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
    }
    post {
        always {
           publishHTML (target : [
                                   allowMissing: false,
                                   alwaysLinkToLastBuild: true,
                                   keepAll: true,
                                   reportDir: 'target/site/serenity/',
                                   reportFiles: 'index.html',
                                   reportName: 'Serenity Report',
                                   reportTitles: 'API Test for Auction App',
                                   useWrapperFileDirectly: true
                                   ])
        }
    }
}