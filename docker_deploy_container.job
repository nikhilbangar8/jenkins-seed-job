pipelineJob('Docker Build Job') {
  parameters{
    stringParam('tomcatdockerImage','mytomcat','')
    stringParam('port','8081','')
    stringParam('containerName','mytomcatcontainer','')
  }
  definition {
    cps {
      script('''
          pipeline {
          agent any
                stages {
                    stage('Checking Docker Version') {
                        steps {
                                echo "Checking Docker Version"
                                sh "docker --version"
                        }
                        
                    }
                    stage('Deploying Container') {
                        steps {
                            echo 'Deploying Container with image name ${tomcatdockerImage}'
                            sh "docker run --name ${containerName} -p ${port}:8080 -itd ${tomcatdockerImage}"
                            echo "Tomcat Container Deployed Successfully on port ${port} with container-name ${containerName}"
                        }
                    }
                    stage('Final Step') {
                        steps {
                            sh "sudo -s docker ps"
                            echo "Pipeline Completed"
                        }
                    }
                    
                }
            }
        ''')
      sandbox()
    }
  }
}
