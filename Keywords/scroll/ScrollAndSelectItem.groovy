package scroll

import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


public class ScrollAndSelectItem {
	@Keyword
	def selectItemByName(String itemName, String tableXpath) {
		WebDriver driver = DriverFactory.getWebDriver()
		boolean itemFound = false
		int scrollAttempts = 0
		int maxScrolls = 20  // prevent infinite loop

		while (!itemFound && scrollAttempts < maxScrolls) {
			List<WebElement> items = driver.findElements(By.xpath(tableXpath + "//div[contains(@class,'item-name')]"))
			for (WebElement item : items) {
				if (item.getText().trim().equalsIgnoreCase(itemName)) {
					item.click()
					itemFound = true
					println "Clicked on item: " + itemName
					break
				}
			}
			if (!itemFound) {
				scrollAttempts++
				WebUI.executeJavaScript("window.scrollBy(0, 500)", null)
				WebUI.delay(1)
			}
		}

		if (!itemFound) {
			WebUI.comment("Item '${itemName}' not found after scrolling.")
			assert false : "Item not found: ${itemName}"
		}
	}
	@Keyword
	def writeToExcel(int rowIndex, int cellIndex, String value) {
		String filePath = "Excel File/writeToExcel.xlsx"
		String sheetName = "writeToExcel"
		
		File file =new File(filePath)
		XSSFWorkbook workbook
		XSSFSheet sheet
		
		// If file exists, open it, otherwise create a new workbook
		if(file.exists()) {
			workbook = new XSSFWorkbook(new FileInputStream(file))
		}
		else
			workbook = new XSSFWorkbook()
			
		// get the sheet, create if it doesn't exists create new sheet
		sheet = workbook.getSheet(sheetName)
		if (sheet == null) {
			sheet = workbook.createSheet(sheetName)
		}
		
		// get the row, create if it doesn't exists create new row
		XSSFRow row = sheet.getRow(rowIndex)
		if (row == null) {
			row = sheet.createRow(rowIndex)
		}
		// get the cell, create if it doesn't exists create new cell
		XSSFCell cell = row.createCell(cellIndex)
		cell.setCellValue(value)
		
		// save the changes back to file
		FileOutputStream fos = new FileOutputStream(filePath)
		workbook.write(fos)
		
		// close resource to prevent memory leaks
		fos.close()
		workbook.close()
	}
	
	@Keyword
	def readFromExcelFile(int rowIndex, int cellIndex) {
		// default excel file path and sheet name
		String filePath = "Excel File/writeToExcel.xlsx"
		String sheetName = "writeToExcel"
		
		// Open Excel file to write
		FileInputStream fis = new FileInputStream(filePath)
		XSSFWorkbook workbook = new XSSFWorkbook(fis)
		
		// Access the sheet
		XSSFSheet sheet = workbook.getSheet(sheetName)
		
		// Access the row
		XSSFRow row = sheet.getRow(rowIndex)
		
		//Access the cell and get its value
		XSSFCell cell =row.getCell(cellIndex)
		String value = cell?.getStringCellValue()?:""
		
		// close resource to prevent memory leaks
		workbook.close()
		fis.close()
		
		// return cell value
		return value
	}
	
}
