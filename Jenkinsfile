pipeline {
    agent any
    
    tools {
        maven 'mavenjenkins'
    }
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        
		stage('Sonar'){
			steps{
				sh 'mvn clean install sonar:sonar'
			}
		}
		
    }
}
