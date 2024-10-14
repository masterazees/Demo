package testNg;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTable {

	@Test
	public static void DynamicWebTable() {
		WebDriverManager.chromedriver().setup();
		//WebDriver driver =new ChromeDriver();
		WebDriver driver =new FirefoxDriver();
		driver.get("https://letcode.in/table");
		WebElement table = driver.findElement(By.id("simpletable"));
		List<WebElement> headers = table.findElements(By.tagName("th"));
		for (WebElement header : headers) {
			String text =header.getText();
			System.out.println(text);}
		List<WebElement> allrows = table.findElements(By.xpath("//table[@id='simpletable']//tbody/tr"));
		int size = allrows.size();
		System.out.println(size);
		for (int i = 0; i < size; i++) {
			WebElement alr = allrows.get(i);
			String text = alr.getText();
			System.out.println("Text before: " +text); }
		
		 for (WebElement alr : allrows) {
			 String text = alr.getText();
			 System.out.println("Text Value: " +text);
			 //List<WebElement> column =alr.findElements(By.tagName("td")); 
			 //WebElement Firstcolumn = column.get(1);
		  //System.out.println(Firstcolumn.getText());
			 }
		 
		for (int i = 0; i < size; i++) {
			List<WebElement> allcoumn = allrows.get(i).findElements(By.tagName("td"));
			//WebElement column = allcoumn.get(1);
			//String text = column.getText();
			//System.out.println(text);
			int Csize = allcoumn.size(); //System.out.println(Csize);
			/*if(text.equals("Raj")){
				WebElement input = allcoumn.get(3).findElement(By.tagName("input"));
				input.click();
				break;*/
			for (int j = 0; j < Csize; j++) {
				String cell = allcoumn.get(j).getText();
				
				System.out.println(cell);
			
			}
			
			
			
			
		}
		
		
		
		driver.quit();
			}
	}
