package com.questionnaire.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/questionnaire/step_defs",
        dryRun = false,
        plugin = {
                "html:target/report.html"
        }
)

public class CukesRunner {

}
