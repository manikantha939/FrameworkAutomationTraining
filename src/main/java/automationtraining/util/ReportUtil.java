package automationtraining.util;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportUtil {
	private static ExtentReports extent;

	public static void initializeReport() {
		String path=System.getProperty("user.dir")+"/reports/automationReport.html";
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(new File(path));
		extent.attachReporter(spark);
	}
	
	public static ExtentReports getExtentReports() {
		return extent;
	}
	
	public static void flushReport() {
		extent.flush();
	}

}
