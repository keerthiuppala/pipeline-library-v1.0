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
				echo "11111111111111111 ${url}"
				script{ gitrepo = ${GIT_URL}}
				echo "22222222222222222 ${url}"
				echo "33333333333333333 ${url}"
				scmFile("master", url)
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
