#!/usr/bin/env groovy

def call(def artifactoryTool) {
		def server = Artifactory.server("${artifactoryTool}");
        def uploadSpec = """{
        "files": [
        {
          "pattern": "${datas.uploadArtifacts}",
          "target": "${datas.uploadRepository}"
        }
         ]
        }"""
        server.upload(uploadSpec)
}

