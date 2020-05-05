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
                                def ids = sh (script:"$("docker pa -a -q", returnStdout: true).trim()
                                echo "${ids}"
                                sh "sudo docker stop ${ids}"
                        }
                        
                    }
                    stage('Removing all Containers') {
                        steps {
                                echo "Stopping all Containers"
                                sh "sudo docker ps -a -q"
                                def ids = sh (script:"$("docker pa -a -q", returnStdout: true).trim()
                                echo "${ids}"
                                sh "sudo docker rm ${ids}"
                        }
                        
                    }  
                }
            }
        ''')
      sandbox()
    }
  }
}
