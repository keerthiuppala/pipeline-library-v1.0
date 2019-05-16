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
				echo "\u2600 GIT_URL=${env.BUILD_URL}"
					
				scmFile("master", GIT_URL)
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
