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

//* 1. Click 'Add Shift' button
//* 2. click 'Shift Role' input box, then search 'supervisor' & click on 'supervisor' from the dynamic dropdown
//* 3. click 'Division' input box, then search 'div1' & click on 'div1' from the dynamic dropdown
//* 4. click 'Start Time' enter '10' into 'hour' and '30' into minutes field
//* 5. click 'End Time' enter '18' into 'hour' and '40' into minutes field
//* 6. enter 'this is shift' into 'note' input box
//* 7. click on 'submit' button
//* 8. verify success message as 'shift created successfully!!'
//* 9. Finally Go to calendar and verify the delails like shift role, division, start time, end time and notes

// Click 'Add Shift' button
WebUI.click(findTestObject('Object Repository/AddShift/button_AddShift'))

// Click 'Shift Role' input box
WebUI.click(findTestObject('Object Repository/AddShift/input_ShiftRole'))

// Enter 'supervisor' in 'Shift Role' search box
WebUI.setText(findTestObject('Object Repository/AddShift/input_ShiftRole'), 'supervisor')

// Click on 'supervisor' from the dynamic dropdown
WebUI.click(findTestObject('Object Repository/AddShift/dropdown_ShiftRole_supervisor'))

// Click 'Division' input box
WebUI.click(findTestObject('Object Repository/AddShift/input_Division'))

// Enter 'div1' in 'Division' search box
WebUI.setText(findTestObject('Object Repository/AddShift/input_Division'), 'div1')

// Click on 'div1' from the dynamic dropdown
WebUI.click(findTestObject('Object Repository/AddShift/dropdown_Division_div1'))

// Click 'Start Time' hour input box and enter '10'
WebUI.setText(findTestObject('Object Repository/AddShift/input_StartTime_Hour'), '10')

// Enter '30' into 'Start Time' minutes field
WebUI.setText(findTestObject('Object Repository/AddShift/input_StartTime_Minutes'), '30')

// Click 'End Time' hour input box and enter '18'
WebUI.setText(findTestObject('Object Repository/AddShift/input_EndTime_Hour'), '18')

// Enter '40' into 'End Time' minutes field
WebUI.setText(findTestObject('Object Repository/AddShift/input_EndTime_Minutes'), '40')

// Enter 'this is shift' into 'note' input box
WebUI.setText(findTestObject('Object Repository/AddShift/input_Note'), 'this is shift')

// Click on 'submit' button
WebUI.click(findTestObject('Object Repository/AddShift/button_Submit'))

// Verify success message as 'shift created successfully!!'
WebUI.verifyElementText(findTestObject('Object Repository/AddShift/message_Success'), 'shift created successfully!!')

// Go to calendar page
WebUI.click(findTestObject('Object Repository/Calendar/button_Calendar'))

// Verify shift role in calendar
WebUI.verifyElementText(findTestObject('Object Repository/Calendar/label_ShiftRole'), 'supervisor')

// Verify division in calendar
WebUI.verifyElementText(findTestObject('Object Repository/Calendar/label_Division'), 'div1')

// Verify start time in calendar
WebUI.verifyElementText(findTestObject('Object Repository/Calendar/label_StartTime'), '10:30')

// Verify end time in calendar
WebUI.verifyElementText(findTestObject('Object Repository/Calendar/label_EndTime'), '18:40')

// Verify notes in calendar
WebUI.verifyElementText(findTestObject('Object Repository/Calendar/label_Note'), 'this is shift')