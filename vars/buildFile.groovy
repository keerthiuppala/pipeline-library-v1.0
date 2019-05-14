#!/usr/bin/env groovy

def call(def buildTool) {
	echo 'keerthi'
		def mvnHome = tool "${buildTool}";
	
	echo "${buildTool}"
        bat "${mvnHome}\\bin\\mvn "+"${mavenGoals}"+" -Dbuild.number=${BUILD_NUMBER}"
}

