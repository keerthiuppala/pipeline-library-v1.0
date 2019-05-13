#!/usr/bin/env groovy

 def call() {
          sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
  }
