#!/usr/bin/env groovy

def call(def artifactoryTool) {
		def server = Artifactory.server("${artifactoryTool}");
        def uploadSpec = """{
        "files": [
        {
          "pattern": "${uploadArtifacts}",
          "target": "${uploadRepository}"
        }
         ]
        }"""
        server.upload(uploadSpec)
}

