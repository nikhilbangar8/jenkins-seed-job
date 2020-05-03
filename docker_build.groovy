pipelineJob('Docker Build Job') {
  definition {
    cps {
      script('''
          pipeline {
          agent any
                stages {
                    stage('Checking Docker Version') {
                        steps {
                            def ret = sh(script: 'sudo docker --version', returnStdout: true)
                            println ret
                            shell('sudo docker --version')
                        }
                    }
                    stage('Starting Linux image in Docker') {
                        steps {
                            echo 'logic'
                            shell('echo pwd')
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
