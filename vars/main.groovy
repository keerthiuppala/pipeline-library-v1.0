#!/usr/bin/env groovy
@Library('pipeline-library-v1.0')_
def call()
{
	
	echo "Keerthi"
	
pipeline {
    agent any
	
	options {
    skipDefaultCheckout(true)
	}
	environment {
        branch = 'master'
		gitUrl = 'https://github.com/keerthiuppala/maven-project.git'
		gitCredentials = 'Nagagopi:horntail23'
		buildTool = 'maven_home'
		mavenGoals = 'clean package'
		artifactoryTool = 'artifactoryserver'
		uploadArtifacts = '*maven*.jar'
		uploadRepository = 'samplerepo/'
		downloadArtifacts = 'samplerepo/*.jar'
		downloadTarget = 'samplerepo/'
		publishJunit = 'target/surefire-reports/*.xml'
    }
    stages {
	    
	    stage('Checkout') {
			steps {
				scmFile(branch,gitUrl)
			}
		}
	    stage('Build') {
			steps {
				buildFile(buildTool)
			}
		}
	    stage('Upload Artifacts') {
			steps {
				uploadArtifactory(artifactoryTool)
			}
		}
	    stage('Download Artifacts') {
			steps {
				downloadArtifactory(artifactoryTool)
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
