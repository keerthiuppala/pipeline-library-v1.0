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
				scmFile("master","https://github.com/keerthiuppala/maven-project.git")
			}
		}
	    stage('Read YAML file') {
        steps {
		echo "222222222222222222222222222222222222"
            script{ datas = readYaml (file: 'env.yml') }
		echo datas.branch.toString()
		echo "333333333333333333333333333333333333"
                  }
    }
	    stage('Build') {
			steps {
				buildFile(datas.buildTool)
			}
		}
	    stage('Upload Artifacts') {
			steps {
				uploadArtifactory(datas.artifactoryTool)
			}
		}
	    stage('Download Artifacts') {
			steps {
				downloadArtifactory(datas.artifactoryTool)
			}
		}
	    stage('Publish Junit Reports') {
			steps {
				junitFile()
			}
		}

	}

}
}
