#!/usr/bin/env groovy

def call(def artifactoryTool) {
		def server = Artifactory.server("${artifactoryTool}");
        def downloadSpec = """{
		"files": [
		{
		   "pattern": "${downloadArtifacts}",
          		"target": "${downloadTarget}"
		}
		]
		}"""
        server.download(downloadSpec)
}

