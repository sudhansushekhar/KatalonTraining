import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.testng.Assert as Assert
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://rahulshettyacademy.com/AutomationPractice/')

WebUI.waitForPageLoad(10)

WebDriver driver = DriverFactory.getWebDriver()


// Find the table
WebElement Table = driver.findElement(By.xpath("//div[@class='tableFixHead']//table[@id='product']"));

// Find all rows in the table
List<WebElement> rows = Table.findElements(By.tagName("tr"));

int amount = 0

for (int i = 1; i < rows.size(); i++) {
	// find all columns
	List<WebElement> cols = rows.get(i).findElements(By.tagName("td"))

    // Extract text, remove non-numeric characters if needed
    String amountText = cols.get(3).getText().trim().replaceAll("[^0-9]", "");

    // Convert to integer and add to totalSum
    amount =amount + Integer.parseInt(amountText);  
}

println('Total Sum of Amounts :' + amount)

String AmountCollectedText = driver.findElement(By.xpath("//div[@class='totalAmount']")).getText()
// split based on delimiter
String[] parts = AmountCollectedText.split(":")

 int actualAmount = 0;  // Default value
    if (parts.length > 1) {
        actualAmount = Integer.parseInt(parts[1].trim());
    } else {
        System.out.println("Warning: Expected ':' but not found in text.");
    }

    System.out.println("Extracted Amount: " + actualAmount);

Assert.assertEquals(actualAmount, amount)

WebUI.closeBrowser()