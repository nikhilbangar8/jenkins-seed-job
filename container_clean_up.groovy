pipelineJob('Container Cleanup') {
  definition {
    cps {
      script('''
          pipeline {
          agent any
                stages {
                    stage('Stopping all Containers') {
                        steps {
                                echo "Stopping all Containers"
                                sh "sudo docker ps -a -q"
                                sh (script:'sudo docker stop $(docker ps -a -q)', returnStdout: true)
                                
                        }
                        
                    }
                    stage('Removing all Containers') {
                        steps {
                                echo "Removing all Containers"
                                sh "sudo docker ps -a -q"
                                sh (script:'sudo docker rm $(docker ps -a -q)', returnStdout: true)
                        }
                        
                    }  
                }
            }
        ''')
      sandbox()
    }
  }
}
