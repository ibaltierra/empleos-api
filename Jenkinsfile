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
