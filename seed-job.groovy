job('MyfirstJob') {
    
    triggers {
        scm('H/15 * * * *')
    }
    steps {
        shell('echo First Job START')
    }
}

job('My-Second-Job') {
    
    triggers {
        scm('H/15 * * * *')
    }
    steps {
        shell('echo second Job START')
    }
}
