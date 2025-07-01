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

//* 1. Click on 'Print' button that will open print dialogue box
//* 2. Click on 'Save' and pdf will be opened in the next window
//* 3. Verify the list of books present in the invoice e.g 'abc', 'xyz', 'book3'

// Click on 'Print' button that will open print dialogue box
WebUI.click(findTestObject('Object Repository/PrintButton'))

// Click on 'Save' and pdf will be opened in the next window
WebUI.click(findTestObject('Object Repository/SaveButton'))

// Switch to the new window where PDF is opened
WebUI.switchToWindowIndex(1)

// Verify the list of books present in the invoice e.g 'abc', 'xyz', 'book3'
WebUI.verifyTextPresent('abc', false)
WebUI.verifyTextPresent('xyz', false)
WebUI.verifyTextPresent('book3', false)