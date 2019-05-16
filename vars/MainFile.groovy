#!/usr/bin/env groovy

import java.util.logging.Logger
import jenkins.*
import jenkins.model.*
import hudson.model.*
import hudson.tasks.Shell
import hudson.slaves.EnvironmentVariablesNodeProperty
import hudson.slaves.EnvironmentVariablesNodeProperty.Entry
import hudson.model.Node.Mode
import hudson.markup.RawHtmlMarkupFormatter
import hudson.markup.EscapedMarkupFormatter

def call()
{
pipeline {
    agent any
	options {
    skipDefaultCheckout(true)
		}
    stages {
    	    stage('Checkout') {    
			steps {
				Jenkins jenkins = Jenkins.getInstance()
				item = jenkins.instance.getItemByFullName("JOB_NAME")
				println item.getScm().getUserRemoteConfigs()[0].getUrl()
				gitrepo= item.getScm().getUserRemoteConfigs()[0].getUrl()
				scmFile("master", gitrepo)
			}
		}
	    stage('Read YAML file') {
        		steps {
            			script{ datas = readYaml (file: 'env.yml') }
                  	}
    		}
	    stage('Build') {
			steps {
				buildFile(datas)
			}
		}
	    stage('Upload Artifacts') {
			steps {
				uploadArtifactory(datas)
			}
		}
	    stage('Download Artifacts') {
			steps {
				downloadArtifactory(datas)
			}
		}
	    stage('Publish Junit Reports') {
			steps {
				junitFile(datas)
			}
		}

	}

}
}
