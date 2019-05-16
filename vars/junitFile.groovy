#!/usr/bin/env groovy

def call(def datas) {
	junit "${datas.publishJunit}"
}

