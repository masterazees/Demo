package testNg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.sound.midi.SysexMessage;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

import utilityt.Datautils;
import utilityt.LoadPropties;

public class BaseClass {
	static WebDriver driver;
	Workbook w;
	FileInputStream fin;
	//@Parameters({"browser"})
	public static Select sd;
	public void browser() {
		// WebDriverManager.firefoxdriver().setup();

		switch (LoadPropties.Browser) 
		{ 
		case "chrome": 
			driver = new ChromeDriver(); 
			break; 
		case "firefox": 
			driver =new FirefoxDriver(); 
			break; 
		default:
			driver = new ChromeDriver();
			break; }

		//driver = new ChromeDriver();
	}
	public void quitb() {
		driver.quit();
	}
	public void max() {
		driver.manage().window().maximize();
	}
	public void MasterUrl() {
		//driver.get("http://leaftaps.com/opentaps/control/main");
		driver.get(LoadPropties.App_URl);
	}

	public WebElement idLocator(String idname) {
		WebElement idName = driver.findElement(By.id(idname));
		return idName;
	}

	public List<WebElement> xpathelemnts(String xnames) {
		List<WebElement> xNames = driver.findElements(By.xpath(xnames));
		return xNames;
	}

	public WebElement nameloc(String namel) {
		WebElement name = driver.findElement(By.name(namel));
		return name;
	}

	public WebElement xpathlocator(String xpathn) {
		WebElement xpathname = driver.findElement(By.xpath(xpathn));
		return xpathname;
	}

	public WebElement classnamelocator(String classname) {
		WebElement clsname = driver.findElement(By.className(classname));
		return clsname;
	}

	public void clickM(WebElement element) {
		element.click();
	}

	public void sendkeys(WebElement element, String name) {
		element.sendKeys(name);
	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void windowhandle(int num) {
		String Parentwindow = driver.getWindowHandle();
		Set<String> allwindow = driver.getWindowHandles();
		ArrayList<String> alltab = new ArrayList<String>(allwindow);
		driver.switchTo().window(alltab.get(num));
		{
			String title = driver.getCurrentUrl();
			System.out.println("Current URl Child is: " + title);
			childclassoperation.childopera();
		}
		driver.switchTo().window(Parentwindow);
		String title = driver.getCurrentUrl();
		System.out.println("Current URl is: " + title);
	}

	public String excelRead(String sheet, int row, int cell) {
		// String value=null;
		
		File f = new File("./test-data/CRMFA Data.xlsx");
		
		try {
			fin = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			w = new XSSFWorkbook(fin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet s = w.getSheet(sheet);
		Row r = s.getRow(row);
		Cell c = r.getCell(cell);
		DataFormatter d = new DataFormatter();
		String CellValue = d.formatCellValue(c);
		//System.out.println(CellValue);
		try {
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CellValue;

	}
	public  String screenshotA(String screenName) {
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destl = "./ScreenS/"+screenName+".png";
		File dest = new File(destl);
		try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//FileHandler.copy(source, dest);
		return destl;
	}
	public void dropdown(WebElement element, String vistext ) {
		Select sd = new Select(element);
		sd.selectByVisibleText(vistext);
		//return sd; 
	}

	public  Select dropd(WebElement element ) {
		sd = new Select(element);
		return sd;
	}
	
	public void droptext(Select s) {
		List<WebElement> options = s.getOptions();
		for (WebElement A : options) {
			String text = A.getText();
			System.out.println(text);
		
		}
		
		
	}

}
