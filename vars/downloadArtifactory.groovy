#!/usr/bin/env groovy

def call(def datas) {
		def server = Artifactory.server("${datas.artifactoryTool}");
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

