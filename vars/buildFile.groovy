#!/usr/bin/env groovy

def call(def buildTool) {
		def mvnHome = tool "${buildTool}";
	echo "keerthi"
	echo "${buildTool}"
        bat "${mvnHome}\\bin\\mvn "+"${mavenGoals}"+" -Dbuild.number=${BUILD_NUMBER}"
}

