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
                            publishHTML (target : [
                                                    allowMissing: false,
                                                    alwaysLinkToLastBuild: false,
                                                    keepAll: true,
                                                    reportDir: 'target/site/serenity/',
                                                    reportFiles: 'index.html',
                                                    reportName: 'Serenity Report 3',
                                                    reportTitles: 'Smoke API Test for Auction App',
                                                    useWrapperFileDirectly: true
                                                    ])
                    }
                }
                stage('Run API Regression Tests') {
                    steps {
                         sh 'mvn verify -Dcucumber.filter.tags="@Regression"'
                             publishHTML (target : [
                                                     allowMissing: false,
                                                     alwaysLinkToLastBuild: false,
                                                     keepAll: true,
                                                     reportDir: 'target/site/serenity/',
                                                     reportFiles: 'index.html',
                                                     reportName: 'Serenity Report 4',
                                                     reportTitles: 'Smoke API Test for Auction App',
                                                     useWrapperFileDirectly: true
                                                     ])
                    }
                }
            }
        }

    }
}