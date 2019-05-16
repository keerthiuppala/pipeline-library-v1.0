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
				item = Jenkins.instance.getItemByFullName("JOB_NAME")
				println item.getScm().getUserRemoteConfigs()[0].getUrl()
				gitrepo= item.getScm().getUserRemoteConfigs()[0].getUrl()
				scmFile("master", gitrepo)
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
