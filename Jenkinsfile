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
	    post {
		    success{
			    slackSend message: 'El proceso termino con éxito'
		    } 
		    failure{
		    	alckSend message: 'Fallo al ejecutar el proceso de Build'
		    }
                always {
                    slackSend color: '#BADA55', message: 'Termino el proceso!'
                }
            }
        }        
    }
}
