import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

 * 1. Open a browser to a 'https://katalon-demo-cura.herokuapp.com/'
 * 2. Log in to the website
 * 3. Input username and password
 * 4. Click the login button
 * 5. Click go to table
 * 6. Click on button 'Edit' within the context of table at column 'Actions' at aparticular row by checking start and end time
 * 5. Close the browser
// Open a browser to the specified URL
WebUI.openBrowser('https://katalon-demo-cura.herokuapp.com/')

// Click the login link to go to login page
WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment'))

// Input username
WebUI.setText(findTestObject('Object Repository/Page_CURA Healthcare Service/input_Username_username'), 'John Doe')

// Input password
WebUI.setEncryptedText(findTestObject('Object Repository/Page_CURA Healthcare Service/input_Password_password'), 'g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM')

// Click the login button
WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/button_Login'))

// Click go to table (assuming a link or button to navigate to the table page)
WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/a_Go To Table'))

// Find the row in the table by start and end time and click the 'Edit' button in the 'Actions' column
def startTime = '08:00'
def endTime = '09:00'

// Get all rows of the table
List rows = WebUI.findWebElements(findTestObject('Object Repository/Page_Table/table_rows'), 10)

for (int i = 0; i < rows.size(); i++) {
    def row = rows[i]
    def startCell = row.findElement(By.xpath('./td[1]'))
    def endCell = row.findElement(By.xpath('./td[2]'))
    if (startCell.getText() == startTime && endCell.getText() == endTime) {
        def editButton = row.findElement(By.xpath('.//button[contains(text(),"Edit")]'))
        editButton.click()
        break
    }
}

// Close the browser
WebUI.closeBrowser()