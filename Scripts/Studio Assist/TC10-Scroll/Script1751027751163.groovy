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

//* 1. Click 'add item' button
//* 2. Item table displayed, that contains only 10 item visibly. Once I scroll down it loads 10 more items and so on
//* 3. I have to click on 'item003' that might be way down in scrolling
//* 4. verify the selected item in the table

// Click 'add item' button
WebUI.click(findTestObject('Object Repository/add_item_button'))

// Scroll to 'item003' in the item table to make it visible
WebUI.scrollToElement(findTestObject('Object Repository/item_table/item003'), 10)

// Click on 'item003' in the item table
WebUI.click(findTestObject('Object Repository/item_table/item003'))

// Verify that 'item003' is selected in the table
WebUI.verifyElementAttributeValue(findTestObject('Object Repository/item_table/item003'), 'class', 'selected', 10)
CustomKeywords.'scroll.ScrollAndSelectItem.selectItemByName'(itemName, tableXpath)
