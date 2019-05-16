#!/usr/bin/env groovy

def call(def branch,def gitUrl) {
  checkout changelog: true, poll: true, scm: [
            $class           : 'GitSCM',
            branches         : [[name: '*/' + "${branch}"]],
            userRemoteConfigs: [[url: "${gitUrl}" ]]
    ]
}

