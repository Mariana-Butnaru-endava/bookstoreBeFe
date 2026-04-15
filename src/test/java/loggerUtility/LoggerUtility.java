package loggerUtility;


import io.cucumber.java.Scenario;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {
    @Getter
    private static Scenario scenario;
    private static final Logger logger = LogManager.getLogger(LoggerUtility.class);

    public static void startTest(String testName, Scenario scenario) {
        LoggerUtility.scenario = scenario;
        String message = String.format("======= Test: %s start ======", testName);
        logger.info(message);
        scenario.log(message);
    }

    public static void endTest(String testName) {
        String message = String.format("======= Test: %s end ======", testName);
        logger.info(message);
        scenario.log(message);
    }

    public static void logInfo(Object message) {
        logger.info(message);
        scenario.log(message.toString());
    }

    public static void logError(String message) {
        String msg = String.format("ERROR: %s", message);
        logger.error(msg);
        scenario.log(msg);
    }
}
