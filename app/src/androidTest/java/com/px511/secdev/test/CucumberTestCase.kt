package com.px511.secdev.test

import cucumber.api.CucumberOptions

@CucumberOptions(features = ["features"],
        glue = ["com.px511.secdev.cucumber.steps"])
@Suppress("unused")
class CucumberTestCase
