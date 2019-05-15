#!/usr/bin/env groovy
@Library('pipeline-library-v1.0')_

def call() 
{
	environment {
        branch = 'master'
		gitUrl = 'https://github.com/keerthiuppala/maven-project.git'	
		buildTool = 'maven_home'
		gitCredentials = ' '
		mavenGoals = 'clean package'
		artifactoryTool = 'artifactoryserver'
		uploadArtifacts = '*maven*.jar'
		uploadRepository = 'samplerepo/'
		downloadArtifacts = 'samplerepo/*.jar'
		downloadTarget = 'samplerepo/'
		publishJunit = 'target/surefire-reports/*.xml'
              }
   
		scmFile(branch,gitUrl)
			   
		buildFile(buildTool)
			
		uploadArtifactory(artifactoryTool)
			
		downloadArtifactory(artifactoryTool)
			
		junitFile()
			
}
