package backend;

import org.junit.runner.RunWith;
import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(
		features = "src/main/resources/features/backend/", 
		glue = { "com.steps.backend" }, 
		format = { "progress" })
public class YoutubePostAndGetTest {

}
