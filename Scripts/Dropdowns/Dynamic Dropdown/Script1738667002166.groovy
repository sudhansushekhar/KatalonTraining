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
String countryName = "Dominican Republic"

WebUI.setText(findTestObject('Object Repository/Dropdowns/inputForDynamicDD'), 'In')

WebUI.delay(2)

//WebDriver driver = DriverFactory.getWebDriver()
//List <WebElement> countries = driver.findElements(By.xpath("//ul[@id='ui-id-1']//li//div"))
//println(countries.size())

List countries = WebUI.findWebElements(findTestObject('Object Repository/Dropdowns/elementinDynamicDD'), 30)

for(WebElement country:countries) {
	if (country.getText() == countryName) {
		country.click()
	}
}

WebUI.delay(2)

WebUI.closeBrowser()

