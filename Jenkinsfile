
pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('initialize') {
      steps {
        sh 'mvn -v'
        sh 'java -version'
        sh 'git --version'
        sh 'docker -v'
      }
        }

        stage('pull') {
      steps {
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/splitscale/api.git']]])
      }
        }

        stage('test') {
      steps {
        script {
          sh 'mvn clean test'
        }
      }
        }

        stage('install') {
      steps {
        script {
          sh 'mvn clean install'
        }
      }
        }

        stage('build docker image') {
      steps {
        sh 'docker build -t splitscale/fordastore-api:latest .'
      }
        }

        stage('deploy') {
          steps {
            script {
              try {
            runServer()
                        } catch (Exception e) {
            sh 'docker stop fordastore-api'
            sh 'docker rm fordastore-api'
            runServer()
              }
            }
          }
        }
    }
}

def runServer() {
  sh 'docker run --name fordastore-api --network fordastore --network-alias fordastore-api -p 8081:8081 -d splitscale/fordastore-api:latest'
}
