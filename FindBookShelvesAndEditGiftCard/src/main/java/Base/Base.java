package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;

public class Base {

	public static WebDriver d;
	public static Properties prop;
	public static String baseUrl = "https://www.urbanladder.com/";
	public static ExtentHtmlReporter exthtml;
	public static ExtentTest exttest;
	public static ExtentReports report;
	
    //Method to setup driver and extent report
	@BeforeSuite
	public void driversetup() throws FileNotFoundException, IOException {

		prop = new Properties();
		prop.load(new FileInputStream("src/main/java/config/config.properties"));
		if (prop.getProperty("browserName").matches("chrome")) {
			d = new ChromeDriver();
		}
		if (prop.getProperty("browserName").matches("firefox")) {
			d = new FirefoxDriver();
		}
		d.manage().window().maximize();
		d.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		d.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		exthtml = new ExtentHtmlReporter(prop.getProperty("path"));
		report = new ExtentReports();
		report.attachReporter(exthtml);
		report.setSystemInfo("Host Name", "User_Arnab"); 
		report.setSystemInfo("Environment", "Eclipse");
		report.setSystemInfo("User Name", "Arnab");

		exthtml.config().setDocumentTitle("UrbanLadder");
		exthtml.config().setReportName("UrbanLadder Functional Testing");
		exthtml.config().setTestViewChartLocation(ChartLocation.TOP);
		exthtml.config().setTheme(Theme.STANDARD);

	}
    
	//Method to capture  screenshot
	public String takescreenshot(String imagename) {
		File f = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		String de = prop.getProperty("screenshots") + imagename;
		try {
			File des = new File(de);
			FileUtils.copyFile(f, des);
			exttest.addScreenCaptureFromPath(de);
		} catch (Exception e) {
		}
		return de;
	}
    //Method to close the browser and kill the driver
	@AfterSuite
	public void teadDown() {
		d.close();
		report.flush();
		try {
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
		} catch (Exception e) {
		}
	}

}