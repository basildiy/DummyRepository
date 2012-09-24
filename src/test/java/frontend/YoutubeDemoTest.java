package frontend;

import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(
		features = "src/main/resources/features/frontend/", 
		glue = { "com.steps.frontend" }, 
		format = { "progress" })
public class YoutubeDemoTest {

}
