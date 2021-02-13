pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                bat 'mvn --version'
                bat 'echo "Hello World"'
                bat '''
                    echo "Multiline shell steps works too"
                    ls -lah
                '''
                bat 'mvn clean install'
            }
        }
    }
}
