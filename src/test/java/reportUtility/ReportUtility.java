package reportUtility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ReportUtility {
    public static ExtentReports extent;
    public static ExtentTest testReport;

    private static final String pathToProject = System.getProperty("user.dir") + "/target/extentReports/";

    public static void initiateReport() {
        createDirectory();
        ExtentSparkReporter htmlReport = new ExtentSparkReporter(pathToProject + "ExtentReport.html");
        htmlReport.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReport);
    }

    public static void startTest(String testName) {
        testReport = extent.createTest(testName + " - report");
        attachReportLog(Status.INFO, "=== Start Test: " + testName);
    }

    public static void endTest(String testName) {
        attachReportLog(Status.INFO, "=== End Test: " + testName);
    }

    public static void generateReport() {
        extent.flush();
    }

    public static void attachReportLog(Status status, String message) {
        testReport.log(status, message);
    }

    private static void createDirectory() {
        File directory = new File(pathToProject);
        if (!directory.exists()) {
            directory.mkdirs();
        } else {
            System.out.println("Directorul exista");
        }
    }
}
