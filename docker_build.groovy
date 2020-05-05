def dockerfileRepo="https://github.com/nikhilbangar8/tomcat_docker.git"

pipelineJob('Docker Build Job') {
  parameters{
    stringParam('dockerfileRepo',dockerfileRepo,'')
    stringParam('dockerimagename','mytomcat','')
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
                    stage('Retrieving dockerfile for Tomcat Server') {
                        steps {
                            echo 'Cloning Dockerfile Repo for Tomcat Server'
                            sh "[ -d ./tomcat_docker ] && rm -r ./tomcat_docker || echo 'Directory Not Present'"
                            sh "git clone ${dockerfileRepo}"
                            sh "mv ./tomcat_docker/Dockerfile ./Dockerfile"
                            sh "rm -r ./tomcat_docker"
                            echo "Docker File Retrieve Completed"
                            sh "ls"
                        }
                    }
                    stage('Build Docker Image for Tomcat Server') {
                        steps {
                            echo 'Building Docker conatiner image with Name ${dockerimagename}'
                            sh "sudo docker build -t ${dockerimagename} ."
                            echo "image built successfully"
                        }
                    }
                    stage('Last stage') {
                        steps {
                            echo 'Pipeline Completed'
                            sh "docker images"
                        }
                    }
                }
            }
        ''')
      sandbox()
    }
  }
}
