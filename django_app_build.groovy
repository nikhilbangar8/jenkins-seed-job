def dockerfileRepo="https://github.com/nikhilbangar8/RndxDjangoApp.git"

pipelineJob('RnDX Django App Build') {
  parameters{
    stringParam('dockerfilename','Dockerfile','')
    stringParam('dockerfileRepo',dockerfileRepo,'')
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
                            echo 'Cloning Dockerfile and App Repo for Django Service'
                            sh "[ -d ./RndxDjangoApp ] && rm -r ./RndxDjangoApp || echo 'Directory Not Present'"
                            sh "git clone ${dockerfileRepo}"
                            sh "pwd"
                            sh "cd ./RndxDjangoApp"
                            sh "ls"
                            sh "pwd"
                            //sh "mv ./tomcat_docker/Dockerfile ./Dockerfile"
                            //sh "rm -r ./tomcat_docker"
                            echo "Docker File Retrieve Completed"
                            sh "pwd"
                            sh "ls"
                        }
                    }
                    stage('Build Docker Image for Django Server') {
                        steps {
                            echo "Building Docker conatiner image with Name ${dockerimagename}"
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
