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
				script{ url = sh(returnStdout: true, script: 'git config remote.origin.url').trim() }
				echo "2222222222222222222"
 				echo "${url}"
 				echo "333333333333333333333333"
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
