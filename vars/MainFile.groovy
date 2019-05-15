#!/usr/bin/env groovy

def call()
{
	pipeline {
    agent any
	
	options {
    skipDefaultCheckout(true)
	}
    stages {
	    
	    
	    
	    
	    stage('Read YAML file') {
        steps {
            script{ datas = readYaml (file: 'env.yml') }
                  }
    }
	    
	    
	    
	    stage('Checkout') {
			steps {
				scmFile(datas.branch,datas.gitUrl)
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
