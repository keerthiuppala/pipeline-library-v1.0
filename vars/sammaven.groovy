#!/usr/bin/env groovy

 def call(Map config) {
      try {
          sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      }
  }
