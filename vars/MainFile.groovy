#!/usr/bin/env groovy

def call()
{
	
	node {
		echo "11111111111111"
  def datas = readYaml file: 'env.yml'
		echo datas.branch.toString()
}
pipeline {
    agent any
	
	options {
    skipDefaultCheckout(true)
	}
    stages {
	    
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
