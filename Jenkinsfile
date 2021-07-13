pipeline {
    agent any
    
    tools {
        maven 'mavenjenkins'
    }
    triggers {
        githubPush()
    }
    stages {
        stage('Build') {
            steps {
                echo "Iniciando la construcción....."
				sh 'mvn clean install sonar:sonar'
            }
        }        
    }
}
