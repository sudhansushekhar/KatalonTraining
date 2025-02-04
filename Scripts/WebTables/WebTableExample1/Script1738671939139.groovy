import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.testng.Assert

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://rahulshettyacademy.com/AutomationPractice/')

WebUI.waitForPageLoad(10)

WebDriver driver = DriverFactory.getWebDriver()

String ExpectedValue = "WebSecurity Testing for Beginners-QA knowledge to next level"

WebElement Table = driver.findElement(By.xpath("//table[@name='courses']"))

//no of rows
List<WebElement> rows = Table.findElements(By.tagName('tr'))
int rowNo = rows.size()


Loop:
for(int i=1; i<rowNo;i++) {
	List<WebElement> cols = rows.get(i).findElements(By.tagName('td'))
	
	//column count 
	int colNo = cols.size()
	println('Number of cells In Row ' + i + ' are ' + colNo)
	
	for (int j = 0; j < colNo; j++) {
		String celltext = cols.get(j).getText()
		
		println('Cell Value Of row number ' + i + ' and column number ' + j + ' Is ' + celltext)
		
		if (celltext == ExpectedValue) {
			println('Text present in row number 4 is: ' + cols.get(1).getText())
			
			break Loop;
		}
	}
}


WebUI.closeBrowser()

