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

* 1. Click on 'Start Date'
* 2. select date from the calendar picker that displays more than 31 days
* 3. gray ones are past or future dates but current month selected in calendar is not  grayed
* 4. I need to select past date as '12/14/2020', the calendar is like it contains MonthName and year. Once you click year it displays 3 years current selected with left right navigations to select years. Once year is selected Month name appears to select month then dates appears. I have to check first if my past-year is present on screent else click left button to navigate to past untill desired year is fount
* 5. Click on 'End Date'
* 6. select date from the calendar picker that displays more than 31 days
* 7. gray ones are past or future dates but current month selected in calendar is not  grayed
* 8. I need to select past date as '12/14/2029' the calendar is like it contains MonthName and year.binding Once you click year it displays 3 years with left right navigations to select years. Once year is selected Month name appears to select month then dates appears. I have to check first if my future-year is present on screent else click right button to navigate to future untill desired year is fount

// Click on 'Start Date' field to open the calendar picker
WebUI.click(findTestObject('Object Repository/StartDate'))
// Click on year display to open year selection view
WebUI.click(findTestObject('Object Repository/Calendar/YearDisplay'))
// Loop to navigate to the desired past year 2020
while (!(WebUI.verifyElementPresent(findTestObject('Object Repository/Calendar/Year_2020'), 1, FailureHandling.OPTIONAL))) {
    // Click left navigation button to go to previous set of years
    WebUI.click(findTestObject('Object Repository/Calendar/Year_LeftNav'))
}
// Select the year 2020
WebUI.click(findTestObject('Object Repository/Calendar/Year_2020'))
// Select the month December
WebUI.click(findTestObject('Object Repository/Calendar/Month_December'))
// Select the date 14
WebUI.click(findTestObject('Object Repository/Calendar/Date_14'))
// Click on 'End Date' field to open the calendar picker
WebUI.click(findTestObject('Object Repository/EndDate'))
// Click on year display to open year selection view
WebUI.click(findTestObject('Object Repository/Calendar/YearDisplay'))
// Loop to navigate to the desired future year 2029
while (!(WebUI.verifyElementPresent(findTestObject('Object Repository/Calendar/Year_2029'), 1, FailureHandling.OPTIONAL))) {
    // Click right navigation button to go to next set of years
    WebUI.click(findTestObject('Object Repository/Calendar/Year_RightNav'))
}
// Select the year 2029
WebUI.click(findTestObject('Object Repository/Calendar/Year_2029'))
// Select the month December
WebUI.click(findTestObject('Object Repository/Calendar/Month_December'))
// Select the date 14
WebUI.click(findTestObject('Object Repository/Calendar/Date_14'))
