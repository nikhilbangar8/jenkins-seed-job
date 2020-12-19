def dockerfileRepo="https://github.com/nikhilbangar8/tomcat_docker.git"

pipelineJob('RnDX Django App Build') {
  parameters{
    stringParam('dockerfilename','Dockerfile','')
    stringParam('dockerfileRepo','Dockerfile','')
    stringParam('dockerimagename','rndx_django','')
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
                    stage('Retrieving dockerfile for Django Service') {
                        steps {
                            echo ''
                            sh "pwd"
                            sh "ls"
                        }
                    }
                    stage('Build Docker Image for Django Server') {
                        steps {
                            echo 'Building Docker conatiner image with Name ${dockerimagename}'
                            sh "sudo -s docker build -t ${dockerimagename} ."
                            echo "image built successfully"
                        }
                    }
                    stage('Final stage') {
                        steps {
                            echo 'Pipeline Completed'
                            sh "sudo docker images"
                        }
                    }
                }
            }
        ''')
      sandbox()
    }
  }
}
