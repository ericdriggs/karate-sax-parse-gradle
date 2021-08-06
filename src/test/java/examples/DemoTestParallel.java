package examples;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author pthomas3
 */
// important: do not use @RunWith(Karate.class) !
public class DemoTestParallel {

    @Test
    public void testParallel() {
        Results results = Runner.path("classpath:")
                .outputCucumberJson(true)
                .outputHtmlReport(true)
                .outputJunitXml(true)
                .parallel(5);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}