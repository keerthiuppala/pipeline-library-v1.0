#!/usr/bin/env groovy

def call() {
  git url: 'https://github.com/Nagagopi/maven-simple.git'
  def mvnHome = tool 'maven_home'
  sh "${mvnHome}/bin/mvn -B verify"
}
