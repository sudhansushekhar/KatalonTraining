import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys
import org.testng.Assert

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://books-pwakit.appspot.com/')

WebUI.setText(findTestObject('ShadowDOM/inputInShadowDOM'), 'abc')

WebUI.sendKeys(findTestObject('ShadowDOM/inputInShadowDOM'), Keys.chord(Keys.ENTER))

//WebUI.delay(4)
//
//String bookTitleActual = WebUI.getText(findTestObject('ShadowDOM/BookTitle'))
//
//String bookTitleExp = 'ABC of Reading'
//
//Assert.assertEquals(bookTitleActual, bookTitleExp)

WebUI.closeBrowser()

