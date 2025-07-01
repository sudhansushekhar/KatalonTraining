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

//* 1. Capture start date and time from start column in a table that has format '10/21/2025 12:45'
//* 2. Capture end date and time from end column in a table that has format '10/21/2025 23:45'
//* 3. split start and end date and place them in a variable
//* 4. split start and end time and place them in a variable

// Get the start date and time text from the start column in the table
String startDateTime = WebUI.getText(findTestObject('Object Repository/table/startColumn'))
// Get the end date and time text from the end column in the table
String endDateTime = WebUI.getText(findTestObject('Object Repository/table/endColumn'))
// Split the start date and time into separate variables
String[] startParts = startDateTime.split(' ')
// Assign start date and start time to variables
String startDate = startParts[0]
String startTime = startParts[1]
// Split the end date and time into separate variables
String[] endParts = endDateTime.split(' ')
// Assign end date and end time to variables
String endDate = endParts[0]
String endTime = endParts[1]