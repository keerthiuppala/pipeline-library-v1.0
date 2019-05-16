#!/usr/bin/env groovy

def call(def datas) {
		def server = Artifactory.server("${datas.artifactoryTool}");
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

