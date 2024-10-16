package testNg;
import org.testng.annotations.Test;  


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utilityt.Datautils;
import utilityt.LoadPropties;


@Listeners(Customlist.class)
public class Login extends BaseClass {
	//BaseClass b = new BaseClass();
	ExtentSparkReporter  spark;
	ExtentReports reports;
	ExtentTest Testcase;

	@BeforeSuite
	public void launchreport() { spark = new
	ExtentSparkReporter("Testreport.html"); 
	reports = new ExtentReports();
	reports.attachReporter(spark); 
	}

	@BeforeTest
	public void LaunchBrowser() throws IOException {
		LoadPropties Lp = new LoadPropties();
		Lp.loadprop();
		browser();
		MasterUrl();
	}
	// private static final String dataProvider = null;
	// @Parameters({"ComName","browser"})
	@Test (priority =1)
	//(dataProvider = "getdata", dataProviderClass = Datautils.class)
	public void testmain() throws IOException {
		// String company ,String brow --> use if @parameter used
		// String data[] ---> only for @Dataprovider used in Method paramatreization
		// System.out.println("username is:" + data[0]); ----> output for DataProvider
		// from Excel
		// System.out.println(" passwordis:" + data[1]);

		Testcase = reports.createTest(" Login Page ");
		WebElement user = idLocator("username");
		sendkeys(user, LoadPropties.Username); //b.excelRead("TData", 1, 0)

		WebElement passw = idLocator("password");
		sendkeys(passw, LoadPropties.Password);
		WebElement fin = classnamelocator("decorativeSubmit");
		clickM(fin);
		sleep(1000);
		screenshotA("Leelavathy");
		WebElement but = idLocator("button");
		clickM(but);
		screenshotA("Abirami");
		WebElement Clead = xpathlocator("//a[text()='Create Lead']");
		clickM(Clead);

	}

	@Test (priority =2) 
	public void createLead() {
		try {
			WebElement Formfill = idLocator("createLeadForm_companyName");
			sendkeys(Formfill,excelRead("TData", 1, 0) ); 
			WebElement wind = xpathlocator("//input[@id='createLeadForm_parentPartyId']/following::a[1]");
			clickM(wind);
			windowhandle(1);
			sleep(1000);
			WebElement FirstName = driver.findElement(By.xpath("//input[@id='createLeadForm_firstName']"));
			sendkeys(FirstName,excelRead("TData", 1, 2));

			WebElement LastName = driver.findElement(By.xpath("//input[@id='createLeadForm_lastName']"));
			sendkeys(LastName,excelRead("TData", 1, 3));

			WebElement drop = nameloc("dataSourceId");
			dropd(drop);
			droptext(sd);
			sd.selectByVisibleText(excelRead("TData", 1, 4));
			WebElement dropmarketing = nameloc("marketingCampaignId");
			dropd(dropmarketing);
			sd.selectByVisibleText(excelRead("TData", 1, 5));
			droptext(sd);
			WebElement Firstlocal = nameloc("firstNameLocal");
			sendkeys(Firstlocal,excelRead("TData", 1,6));
			WebElement LastLocal = nameloc("lastNameLocal");
			sendkeys(LastLocal,excelRead("TData", 1,7));
			WebElement Salute = nameloc("personalTitle");
			sendkeys(Salute,excelRead("TData", 1,8));
			WebElement Title = nameloc("generalProfTitle");
			sendkeys(Title,excelRead("TData", 1,9));
			WebElement DepartName = nameloc("departmentName");
			sendkeys(DepartName,excelRead("TData", 1,10));
			WebElement revenue = nameloc("annualRevenue");
			sendkeys(revenue,excelRead("TData", 1, 11));
			WebElement indusType = nameloc("industryEnumId");
			dropd(indusType);
			sd.selectByVisibleText(excelRead("TData", 1, 12));
			WebElement NoEmp = nameloc("numberEmployees");
			sendkeys(NoEmp,excelRead("TData", 1, 13));
			WebElement owenership = nameloc("ownershipEnumId");
			dropd(owenership);
			sd.selectByVisibleText(excelRead("TData", 1, 14));
			WebElement siccode = nameloc("sicCode");
			sendkeys(siccode,excelRead("TData", 1, 15));
			WebElement ticsymbol = nameloc("tickerSymbol");
			sendkeys(ticsymbol,excelRead("TData", 1, 16));
			
		}
		finally{
			quitb();
		}
		finally{
			quitb();
		}

		//Testcase.log(Status.PASS, markup)
	}


	@AfterMethod
	public void stat(ITestResult result) {

		if(result.getStatus()==ITestResult.FAILURE) {
			Testcase.log(Status.FAIL, "Testcase failed res" + " " + result.getName());
			String screen =screenshotA(result.getName());
			Testcase.addScreenCaptureFromPath(screen);

		}
		else if (result.getStatus()==ITestResult.SUCCESS){
			Testcase.log(Status.PASS, "Testcase passed res" + result.getName());
		}
		else if (result.getStatus()==ITestResult.SKIP){
			Testcase.log(Status.SKIP, "Testcase skipped res" + result.getName());
		}

	}

	/*
	 * @AfterMethod public void failedscre(ITestResult result) {
	 * 
	 * if (ITestResult.FAILURE == result.getStatus()) {
	 * b.screenshotA(result.getName()); }
	 * 
	 * }
	 */
	@AfterSuite
	public void savereport() {
		reports.flush();
	}
}
