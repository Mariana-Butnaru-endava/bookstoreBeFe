//package hooks;
//
//import loggerUtility.LoggerUtility;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeSuite;
//import propertyUtility.PropertyUtility;
//import reportUtility.ReportUtility;
//
//public class Hooks {
//
//    public String testName;
//    public PropertyUtility propertyUtility;
//
//    @BeforeSuite
//    public void beforeSuite() {
//        ReportUtility.initiateReport();
//    }
//
//    @BeforeMethod
//    public void beforeMethod() {
//        testName = this.getClass().getSimpleName();
//        LoggerUtility.startTest(testName);
//        ReportUtility.startTest(testName);
//    }
//
//    @AfterMethod
//    public void afterMethod() {
//        LoggerUtility.endTest(testName);
//        ReportUtility.endTest(testName);
//    }
//
//    @AfterSuite
//    public void afterSuite() {
//        ReportUtility.generateReport();
//    }
//}
