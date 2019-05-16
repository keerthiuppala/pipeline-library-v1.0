#!/usr/bin/env groovy

def call()
{
	
pipeline {
    agent any
	options {
    skipDefaultCheckout(true)
		}
    stages {
    	    stage('Checkout') {    
			steps {
				
				echo "111111111111111111111 My branch is: ${env.BRANCH_NAME}"
				scmFile("master", "https://github.com/keerthiuppala/maven-project.git")
			}
		}
	    stage('Read YAML file') {
        		steps {
            			script{ datas = readYaml (file: 'env.yml') }
                  	}
    		}
	    stage('Build') {
			steps {
				buildFile(datas)
			}
		}
	    stage('Upload Artifacts') {
			steps {
				uploadArtifactory(datas)
			}
		}
	    stage('Download Artifacts') {
			steps {
				downloadArtifactory(datas)
			}
		}
	    stage('Publish Junit Reports') {
			steps {
				junitFile(datas)
			}
		}

	}

}
}
