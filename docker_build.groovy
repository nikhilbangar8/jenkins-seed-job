pipelineJob('Docker Build Job') {
  definition {
    cps {
      script('''
          pipeline {
          agent any
                stages {
                    stage('Checking Docker Version') {
                        steps {
                            sudo docker --version
                        }
                    }
                    stage('Starting Linux image in Docker') {
                        steps {
                            echo 'logic'
                            echo pwd
                        }
                    }
                    stage('Build Docker Image for Tomcat Server') {
                        steps {
                            echo 'logic'
                        }
                    }
                    stage('End Of the Stage') {
                        steps {
                            echo 'logic'
                        }
                    }
                }
            }
        ''')
      sandbox()
    }
  }
}
