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

//Call Case- login to proceed after login
WebUI.selectOptionByValue(findTestObject('Object Repository/Page_CURA Healthcare Service/Tokyo CURA Healthcare Center'), 
    'Hongkong CURA Healthcare Center', true)

WebUI.check(findTestObject('Object Repository/Page_CURA Healthcare Service/Apply for hospital readmission'))

WebUI.verifyElementChecked(findTestObject('Object Repository/Page_CURA Healthcare Service/Apply for hospital readmission'), 
    5, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementNotChecked(findTestObject('Object Repository/Page_CURA Healthcare Service/radio_Medicaid'), 5, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/radio_Medicaid'))

WebUI.verifyElementChecked(findTestObject('Object Repository/Page_CURA Healthcare Service/radio_Medicaid'), 5, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/Visit Date (Required)'))

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/td_12'))

WebUI.setText(findTestObject('Object Repository/Page_CURA Healthcare Service/Comment'), 'test')

WebUI.verifyElementClickable(findTestObject('Object Repository/Page_CURA Healthcare Service/button_Book Appointment'))

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/button_Book Appointment'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_CURA Healthcare Service/Appointment Confirmation'), 2)

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/a_Go to Homepage'))

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/menu-toggle'))

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/a_Logout'))

