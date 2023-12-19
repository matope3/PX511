package com.px511.secdev.test

import cucumber.api.CucumberOptions

@CucumberOptions(features = ["features"],
        glue = ["com.px511.secdev.cucumber.steps"],
        tags = ["@e2e", "@smoke"])
@Suppress("unused")
class CucumberTestCase
