import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.model.FailureHandling.STOP_ON_FAILURE
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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

import static org.junit.Assert.assertEquals

import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement as Keys

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://demoqa.com/alerts')

WebUI.waitForPageLoad(10)

//click on alert
WebUI.click(findTestObject('Object Repository/Alerts/ConfirmDenyAlert'), STOP_ON_FAILURE)

WebUI.waitForAlert(2)

//verify alert text

String alertText = WebUI.getAlertText()
String expAlertText = "Do you confirm action?"
assertEquals(expAlertText, alertText)

//alert Accepted
WebUI.acceptAlert()

WebUI.delay(5)
//get alert text after accepting the alert
String textAfterAlertClicked = WebUI.getText(findTestObject('Object Repository/Alerts/textAfterAlert'))
String expTextAfterAlertClicked = "You selected Ok"
assertEquals(expTextAfterAlertClicked, textAfterAlertClicked)

//click on alert
WebUI.click(findTestObject('Object Repository/Alerts/ConfirmDenyAlert'), STOP_ON_FAILURE)

WebUI.waitForAlert(2)

//alert Denied
WebUI.dismissAlert()

WebUI.delay(5)

//verify alert text
String alertText2 = WebUI.getText(findTestObject('Object Repository/Alerts/textAfterAlert'))
String expAlertText2 = "You selected Cancel"
assertEquals(expAlertText2, alertText2)

WebUI.closeBrowser()

