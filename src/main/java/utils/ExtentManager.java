package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {

            String reportPath = System.getProperty("user.dir") + "/reports/extent-report.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Selenium Basic Framework");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Tester", "Rahul Sharma");
            extent.setSystemInfo("Framework", "Selenium + TestNG");
        }
        return extent;
    }
}
