package calendar

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.concurrent.locks.Condition

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class selectFromCalendar {

	@Keyword
	def selectDateFromCalendarPicker(int day, String month, int year) {
		// Click to open the date picker
		WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/Visit Date (Required)'))

		// Get the current year from the calendar
		String currentMonthYearText = WebUI.getText(findTestObject('Object Repository/Page_CURA Healthcare Service/MonthSelector'))
		String[] parts = currentMonthYearText.split(" ")
		int yearText = Integer.parseInt(parts[1].trim()) // Extract and convert year to int

		// Navigate to the correct year if needed
		if(year==yearText) {
			WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/MonthSelector'))
		}
		else if(year < yearText) {
			WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/MonthSelector'))

			while (true){
				// Get the currently displayed year in the calendar
				String yearInCalendar = WebUI.getText(findTestObject('Object Repository/Page_CURA Healthcare Service/yearNav')).trim();
				int displayedYear = Integer.parseInt(yearInCalendar); // Convert to int

				// If we reached the target year, exit loop
				if (displayedYear == year) {
					break;
				}

				// Click previous year button
				WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/YearSelectorPrev'));
				WebUI.waitForElementPresent(findTestObject('Object Repository/Page_CURA Healthcare Service/yearNav'), 2);  // Ensure UI updates
			}
		}
		else if(year > yearText) {
			// Navigate forward in time
			WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/MonthSelector'))

			while (true){
				// Get the currently displayed year in the calendar
				String yearInCalendar = WebUI.getText(findTestObject('Object Repository/Page_CURA Healthcare Service/yearNav')).trim();
				int displayedYear = Integer.parseInt(yearInCalendar); // Convert to int

				// If we reached the target year, exit loop
				if (displayedYear == year) {
					break;
				}

				// Click next year button
				WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/YearSelectorNext'));
				WebUI.waitForElementPresent(findTestObject('Object Repository/Page_CURA Healthcare Service/yearNav'), 2);
			}
		}

		//Month Selection
		String focusedMonth = WebUI.getText(findTestObject('Object Repository/Page_CURA Healthcare Service/FocusedMonth'))
		if(focusedMonth == month) {
			WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/MonthSelector'))
		}

		//create test object
		TestObject monthElement = new TestObject()
		monthElement.addProperty("xpath", ConditionType.EQUALS, "//span[contains(text(),'" + month + "')]")
		WebUI.click(monthElement)
		WebUI.waitForElementPresent(monthElement, 2)  // Ensure the selection is applied

		//Day Selection
		TestObject dayElement = new TestObject()
		dayElement.addProperty("xpath", ConditionType.EQUALS, "//td[contains(text(),'" + day + "')]")
		WebUI.click(dayElement)
		WebUI.waitForElementPresent(dayElement, 2)  // Ensure the selection is applied
	}
}
