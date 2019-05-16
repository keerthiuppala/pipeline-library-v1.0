#!/usr/bin/env groovy

def call(def datas) {
	echo datas.buildTool.toString()
		def mvnHome = tool "${datas.buildTool}";
	sh "${mvnHome}/bin/mvn "+"${datas.mavenGoals}"+" -Dbuild.number=${BUILD_NUMBER}"
}


