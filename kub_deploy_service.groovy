pipelineJob('MIcro Deploy Job') {
  parameters{
    stringParam('EcrRepo','211448159615.dkr.ecr.us-east-1.amazonaws.com/rndx','ECR Registry Url')
    stringParam('GitRepo','https://github.com/nikhilbangar8/rndxdjango_kubeconfig.git','Git repo for Kubernetes Config Files')
    stringParam('Serv_conf_file','rndx_micro-svc.yaml','File name for Service')
    stringParam('deploy_conf_file','rndx_micro.yaml','File Name for Deployment')
    stringParam('replica','2','Number of Replicas')

  }
  definition {
    cps {
      script('''
          pipeline {
          agent any
                stages {
                    stage('Retrieve config Files for Depoyment') {
                        steps {
                              echo "checking Version" 
                              sh "kubectl --version -client"
                              echo 'Cloning repo of config files'
                              sh "[ -d ./rndxdjango_kubeconfig ] && rm -r ./rndxdjango_kubeconfig || echo 'Directory Not Present'"
                              sh "git clone ${GitRepo}"
                              echo "Config Files Retrieve Completed"

                        }                        
                    }
                    stage('Checking Cluster Status') {
                        steps {
                            echo 'getting Node Info'
                            sh "kubectl get nodes"
                            echo 'Getting Services Info'
                            sh "kubectl get svc"
                        }
                    }
                    stage('Deploying Services') {
                        steps {
                            echo "Deployment Config"
                            sh "kubectl apply -f  ./rndxdjango_kubeconfig/rndx_micro.yaml"
                            echo "Service Config"
                            sh "kubectl apply -f  ./rndxdjango_kubeconfig/rndx_micro-svc.yaml"
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
