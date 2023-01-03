def runServer() {
  sh 'docker run --name dev-api -p 28762:28762 -d splitscale/api:latest'
}

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

        stage('install') {
      steps {
        script {
          sh 'mvn clean install'
        }
      }
        }

        stage('build docker image') {
      steps {
        sh 'docker build -f dev.Dockerfile -t splitscale/api:latest .'
      }
        }

        stage('deploy') {
          steps {
            script {
              try {
                runServer()
                        } catch (Exception e) {
                sh 'docker stop dev-api'
                sh 'docker rm dev-api'
                runServer()
              }
            }
          }
        }
    }
}