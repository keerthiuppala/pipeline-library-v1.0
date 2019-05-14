#!/usr/bin/env groovy

def call(def buildTool) {
		def mvnHome = tool "${buildTool}";
        sh "${mvnHome}/bin/mvn "+"${mavenGoals}"+" -Dbuild.number=${BUILD_NUMBER}"
}


