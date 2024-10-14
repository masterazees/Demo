package testNg;

import java.util.List;

import org.openqa.selenium.WebElement;

public class childclassoperation extends BaseClass {

	public static void childopera() {
		BaseClass c = new BaseClass();
		c.sleep(1000);
		List<WebElement> allrows = c.xpathelemnts("//span[text()='Account List']/following::table//tbody/tr[1]/td[1]");
		int rowsize = allrows.size();
		System.out.println(rowsize);
		List<WebElement> roe = c.xpathelemnts("//div[@id='ext-gen13']//table/tbody/tr[1]/td[1]//a[1]");

		// For Each PRINT All Row 17 Rowcolumn
		/*
		 * for (WebElement alr : allrows) { String text = alr.getText();
		 * System.out.println(text); }
		 */
		// For Statement PRINT all ROW
		/*
		 * for (int i = 0; i < rowsize; i++) { WebElement alr = allrows.get(i); String
		 * text = alr.getText(); System.out.println(text); }
		 */
		/*
		 * int roesize =roe.size();
		 * 
		 * System.out.println(roesize); for (int i = 0; i < roesize; i++) { WebElement
		 * alr2 = roe.get(i); String text = alr2.getText();
		 * System.out.println("data of return" +text); }
		 */
		roe.get(2).click();
	}
}