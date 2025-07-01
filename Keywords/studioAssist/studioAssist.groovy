package studioAssist

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


public class studioAssist {


	/*
	 * Clicks on a web element identified by the given test object ID string.
	 *
	 * @param testObjectId The ID string of the test object to be clicked.
	 */
	@Keyword
	def clickElement(String testObjectId) {
		try {
			// Find the test object using the provided ID string
			TestObject to = findTestObject(testObjectId)
			// Click the found test object
			WebUI.click(to)
			// Mark the keyword as succeeded
			KeywordUtil.markPassed("Successfully clicked the element: " + testObjectId)
		} catch (WebElementNotFoundException e) {
			// Mark failure if the element is not found
			KeywordUtil.markFailed("Cannot find the element: " + testObjectId)
		} catch (Exception e) {
			// Mark failure for any other exceptions during click
			KeywordUtil.markFailed("Failed to click the element: " + testObjectId)
		}
	}

	/*
	 * Selects items from the first table, opens a date selection window, selects the date based on the parameter, clicks OK,
	 * moves the row to the second table, and removes it from the first table. Repeats until the first table is empty.
	 *
	 * @param dateSelection String indicating 'same day' or 'next day' selection for date picker.
	 */
	@Keyword
	def selectItemsAndMoveRows(String dateSelection) {

		// Loop until the first table is empty
		while (true) {
			// Get the count of rows in the first table
			int rowCount = WebUI.getNumberOfTotalOption(findTestObject('Object Repository/FirstTable/rows'))
			if (rowCount == 0) {
				break
			}
			// Click the first row in the first table to open date selection window
			WebUI.click(findTestObject('Object Repository/FirstTable/row', [('index') : 1]))
			// Select date based on parameter
			if (dateSelection == 'same day') {
				// Click on same day date picker element
				WebUI.click(findTestObject('Object Repository/DatePicker/SameDay'))
			} else if (dateSelection == 'next day') {
				// Click on next day date picker element
				WebUI.click(findTestObject('Object Repository/DatePicker/NextDay'))
			}
			// Click OK button to confirm date selection
			WebUI.click(findTestObject('Object Repository/DatePicker/OKButton'))
			// Wait for row to move to second table and be removed from first table
			WebUI.waitForElementNotPresent(findTestObject('Object Repository/FirstTable/row', [('index') : 1]), 10)
		}
	}

	/*
	 * Capture the error message displayed on the page and validate it against the expected message.
	 *
	 * @param expectedMessage The expected error message to validate.
	 */
	@Keyword
	def validateTransientErrorMessage(String expectedMessage) {

		// Define maximum wait time in milliseconds and polling interval
		int maxWaitTime = 3000
		int pollingInterval = 200
		int elapsedTime = 0
		String actualMessage = null

		// Loop to repeatedly check for the error message until timeout or message found
		while (elapsedTime < maxWaitTime) {
			try {
				// Attempt to get the error message text from the error message test object
				actualMessage = WebUI.getText(findTestObject('Object Repository/ErrorMessage'))
				if (actualMessage != null && actualMessage.trim() == expectedMessage) {
					// If message matches expected, mark as passed and exit loop
					KeywordUtil.logInfo("Error message matched: " + actualMessage)
					return
				}
			} catch (Exception e) {
				// Ignore exceptions if element not found or not visible
			}
			// Wait for polling interval before next check
			WebUI.delay(pollingInterval / 1000)
			elapsedTime += pollingInterval
		}
		// If message not found or did not match within timeout, mark as failed
		KeywordUtil.markFailed("Expected error message '" + expectedMessage + "' was not displayed.")
	}

	/*
	 * Set text into a test object after verifying it is visible
	 *
	 * @param testObject The TestObject to set text into
	 * @param text The text to set
	 */
	@Keyword
	def setTextIfVisible(def testObject, String text) {
		// Verify the test object is visible before setting text
		if (WebUI.waitForElementVisible(testObject, 10)) {
			// Set the text into the test object
			WebUI.setText(testObject, text)
		} else {
			// Throw an exception if element is not visible
			throw new StepFailedException("Element not visible: " + testObject.toString())
		}
	}

	/*
	 * Click on a test object after verifying it is visible
	 *
	 * @param testObject The TestObject to click
	 */
	@Keyword
	def clickIfVisible(def testObject) {
		// Verify the test object is visible before clicking
		if (WebUI.waitForElementVisible(testObject, 10)) {
			// Click the test object
			WebUI.click(testObject)
		} else {
			// Throw an exception if element is not visible
			throw new StepFailedException("Element not visible: " + testObject.toString())
		}
	}


	/*
	 * Capture the text from an input box by retrieving its 'value' attribute
	 *
	 * @param testObject The TestObject representing the input box
	 * @return The text contained in the input box
	 */

	@Keyword
	def captureInputBoxText(TestObject testObject) {
		// Retrieve the 'value' attribute from the input box to get the displayed text
		return WebUI.getAttribute(testObject, 'value')
	}


	/*
	 * Capture the visible text from an input box when getText or getAttribute do not work
	 *
	 * @param testObject The TestObject representing the input box
	 * @return The captured text from the input box
	 */
	@Keyword
	def captureInputBoxText1(def testObject) {
		// Use JavaScript to get the value property of the input element
		return WebUI.executeJavaScript('return arguments[0].value;', Arrays.asList(WebUI.findWebElement(testObject)))
	}


	/*
	 * Capture the visible text from an input box that does not return value via getText or getAttribute
	 *
	 * @param testObject The TestObject representing the input box
	 * @return The visible text inside the input box
	 */
	@Keyword
	def captureInputBoxText(def testObject) {
		// Scroll to the element to ensure visibility
		WebUI.scrollToElement(testObject, 10)
		// Use JavaScript to get the visible text by accessing the element's innerText or textContent
		String script = "return arguments[0].innerText || arguments[0].textContent || ''"
		String text = WebUI.executeJavaScript(script, Arrays.asList(WebUI.findWebElement(testObject)))
		return text.trim()
	}
	
	
/*
 * Switches to a browser tab by index and ensures the button element is correctly identified and clicked.
 *
 * @param tabIndex The index of the tab to switch to (0-based).
 */
@Keyword
def switchTab(int tabIndex) {
    // Get all window handles
    def windowHandles = DriverFactory.getWebDriver().getWindowHandles() as List
    // Switch to the specified tab
    DriverFactory.getWebDriver().switchTo().window(windowHandles[tabIndex])
    // Wait for the button to be present and clickable
    WebUI.waitForElementClickable(findTestObject('Object Repository/path/to/button'), 10)
    // Click the button ensuring only one element is targeted
    WebUI.click(findTestObject('Object Repository/path/to/button'))
}
}
