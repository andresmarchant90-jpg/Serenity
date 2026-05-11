package com.test.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features", // Ruta de tus archivos .feature
        glue = "com.test.stepdefinitions", // Donde estarán tus StepDefinitions
        snippets = CucumberOptions.SnippetType.CAMELCASE // Para que los métodos sugeridos usen camelCase
)
public class RunnerIT {
}