#!/usr/bin/env groovy


def call()
{
	
	def branchName = getCurrentBranch()
echo 'My branch is' + branchName

def getCurrentBranch () {
    return sh (
        script: 'git rev-parse --abbrev-ref HEAD',
        returnStdout: true
    ).trim()
}
	
	
pipeline {
    agent any
	options {
    skipDefaultCheckout(true)
		}
    stages {
    	    stage('Checkout') {    
			steps {
				echo "\u2600 ${GIT_URL}"
					
				scmFile(branch, gitrepo)
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
