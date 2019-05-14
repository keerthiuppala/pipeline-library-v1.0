#!/usr/bin/env groovy

def call(def artifactoryTool) {
		def server = Artifactory.server("${artifactoryTool}");
        def downloadSpec = """{
		"files": [
		{
		  "pattern": "Sample/*.war",
		  "target": "Sample/"
		}
		]
		}"""
        server.download(downloadSpec)
}

