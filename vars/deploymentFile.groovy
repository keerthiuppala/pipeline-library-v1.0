#!/usr/bin/env groovy

def call() {
	bat "C:\\Users\\Dharani.M01\\Documents\\Dhar\\apache-tomcat-8.5.16\\bin\\shutdown.bat"

	bat "copy  "+"${deploymentTarget}"+" C:\\Users\\Dharani.M01\\Documents\\Dhar\\apache-tomcat-8.5.16\\webapps"
	
	bat "C:\\Users\\Dharani.M01\\Documents\\Dhar\\apache-tomcat-8.5.16\\bin\\startup.bat"
}

