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
