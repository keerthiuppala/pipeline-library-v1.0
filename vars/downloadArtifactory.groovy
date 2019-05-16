#!/usr/bin/env groovy

def call(def artifactoryTool) {
		def server = Artifactory.server("${artifactoryTool}");
        def downloadSpec = """{
		"files": [
		{
		   "pattern": "${datas.downloadArtifacts}",
          		"target": "${datas.downloadTarget}"
		}
		]
		}"""
        server.download(downloadSpec)
}

