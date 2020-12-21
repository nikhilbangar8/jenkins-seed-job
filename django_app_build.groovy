def dockerfileRepo="https://github.com/nikhilbangar8/RndxDjangoApp.git"

pipelineJob('RnDX Django App Build') {
  parameters{
    stringParam('dockerfilename','Dockerfile','')
    stringParam('dockerfileRepo',dockerfileRepo,'')
    stringParam('dockerimagename','rndx_django','')
    stringParam('app_version','1','Application Version')
    stringParam('ECR_Repo','211448159615.dkr.ecr.us-east-1.amazonaws.com/rndx','ECR Repository Link')
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
                            echo "Docker File Retrieve Completed"
                        }
                    }
                    stage('Build Docker Image for Django Server') {
                        steps {
                            sh "ls"
                            echo "Building Docker conatiner image with Name ${dockerimagename} with version ${app_version}"
                            dir('RndxDjangoApp'){
                              sh "sudo -s docker build -t ${dockerimagename}:${app_version} ."
                            }
                            
                            echo "image built successfully"
                        }
                    }
                    stage('Uploading to ECR Repo') {
                        steps {
                            sh "sudo docker images"
                            //sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 211448159615.dkr.ecr.us-east-1.amazonaws.com"
                            sh "sudo docker tag ${dockerimagename}:${app_version} ${ECR_Repo}/${dockerimagename}:${app_version}"
                            sh "sudo docker push -a ${ECR_Repo}:${app_version}"
                            echo "Image Uploaded to ECR Repo"
                        }
                    }
                }
            }
        ''')
      sandbox()
    }
  }
}
