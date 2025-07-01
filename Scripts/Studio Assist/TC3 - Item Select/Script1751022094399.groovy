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

//* create a tect case to select and verify itemcode, item price, tax, quantity, itemname with below steps
//* 1. Click on 'Add Item' add button
//* 2. enter item Code or item Namein the search box
//* 3. select item in the table
//* 4. Click 'Add' button
//* 5. Verify added item in the table by item code or item name, do the for other items ike itm01, itm02, itm03 these are in variables or add all item codes in list
//* 6. After addint all items, Click one added item one by one
//* 7. Capture item Code, iten name, item Price, tax & Quantity from each row.
//* 8. Then verify Item Name. Item Code and Tax from item Panel that displays to the right of added items
//* 9. Once all rows are done, sum the all item Qty in the items table, price in item table and verify themin Cart section
// Click on 'Add Item' add button

// Define list of item codes to add
List<String> itemCodes = ['itm01', 'itm02', 'itm03']

// Loop through each item code to add items
for (String itemCode : itemCodes) {
	// Click on 'Add Item' add button
	WebUI.click(findTestObject('Object Repository/AddItemButton'))
	
    // Enter item Code or item Name in the search box
    WebUI.setText(findTestObject('Object Repository/SearchBox'), itemCode)
    
    // Select item in the table by clicking the row containing the item code
    WebUI.click(findTestObject('Object Repository/ItemTableRow', [('itemCode') : itemCode]))
    
    // Click 'Add' button to add the selected item
    WebUI.click(findTestObject('Object Repository/AddButton'))
    
    // Verify added item in the table by item code
    WebUI.verifyElementPresent(findTestObject('Object Repository/AddedItemRow', [('itemCode') : itemCode]), 10)
}

// Initialize variables to sum quantity and price
int totalQuantity = 0
double totalPrice = 0.0

// Loop through each added item to capture details and verify
for (String itemCode : itemCodes) {
    // Click one added item one by one in the added items table
    WebUI.click(findTestObject('Object Repository/AddedItemRow', [('itemCode') : itemCode]))
    
    // Capture item Code from the row
    String capturedItemCode = WebUI.getText(findTestObject('Object Repository/AddedItemRow/ItemCode', [('itemCode') : itemCode]))
    
    // Capture item Name from the row
    String capturedItemName = WebUI.getText(findTestObject('Object Repository/AddedItemRow/ItemName', [('itemCode') : itemCode]))
    
    // Capture item Price from the row and convert to double
    String priceText = WebUI.getText(findTestObject('Object Repository/AddedItemRow/ItemPrice', [('itemCode') : itemCode]))
    double capturedItemPrice = Double.parseDouble(priceText.replaceAll('[^0-9.]', ''))
    
    // Capture tax from the row and convert to double
    String taxText = WebUI.getText(findTestObject('Object Repository/AddedItemRow/ItemTax', [('itemCode') : itemCode]))
    double capturedTax = Double.parseDouble(taxText.replaceAll('[^0-9.]', ''))
    
    // Capture quantity from the row and convert to int
    String quantityText = WebUI.getText(findTestObject('Object Repository/AddedItemRow/ItemQuantity', [('itemCode') : itemCode]))
    int capturedQuantity = Integer.parseInt(quantityText.replaceAll('[^0-9]', ''))
    
    // Verify Item Name from item panel displayed to the right
    WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/ItemPanel/ItemName')), capturedItemName, false)
    
    // Verify Item Code from item panel displayed to the right
    WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/ItemPanel/ItemCode')), capturedItemCode, false)
    
    // Verify Tax from item panel displayed to the right
    String panelTaxText = WebUI.getText(findTestObject('Object Repository/ItemPanel/ItemTax'))
    double panelTax = Double.parseDouble(panelTaxText.replaceAll('[^0-9.]', ''))
    WebUI.verifyEqual(panelTax, capturedTax)
    
    // Sum quantity and price for all items
    totalQuantity += capturedQuantity
    totalPrice += capturedItemPrice * capturedQuantity
}

// Capture total quantity from Cart section and convert to int
String cartQuantityText = WebUI.getText(findTestObject('Object Repository/CartSection/TotalQuantity'))
int cartTotalQuantity = Integer.parseInt(cartQuantityText.replaceAll('[^0-9]', ''))

// Capture total price from Cart section and convert to double
String cartPriceText = WebUI.getText(findTestObject('Object Repository/CartSection/TotalPrice'))
double cartTotalPrice = Double.parseDouble(cartPriceText.replaceAll('[^0-9.]', ''))

// Verify summed quantity matches Cart total quantity
WebUI.verifyEqual(cartTotalQuantity, totalQuantity)

// Verify summed price matches Cart total price
WebUI.verifyEqual(cartTotalPrice, totalPrice)

