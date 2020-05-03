job('MyfirstJob') {
    
    steps {
        shell('echo First Job START')
    }
}

job('My-Second-Job') {

    steps {
        shell('echo second Job START')
    }
}

pipelineJob('Pipeline-Job') {
  definition {
    cps {
      script(
          pipeline {
                stages {
                    stage('Stage 1') {
                        steps {
                            echo 'logic'
                        }
                    }
                    stage('Stage 2') {
                        steps {
                            echo 'logic'
                        }
                    }
                }
            }
        })
      sandbox()
    }
  }
}
