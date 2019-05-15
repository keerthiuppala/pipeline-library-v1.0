#!/usr/bin/env groovy

def call()
{
	pipeline {
    agent any
	
	options {
    skipDefaultCheckout(true)
	}
    stages {
	    
	    stage('Reading Env Var') {
      steps {
        script {
	echo "111111111111111111111"
          def datas = readYaml file: 'env.yml'
          echo "2222222222222222222222"
        }
    
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
