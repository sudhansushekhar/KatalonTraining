import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert')

WebUI.click(findTestObject('Object Repository/Page_W3Schools Tryit Editor/button_Try it'))
WebUI.acceptAlert()

Thread.sleep(5)
WebUI.delay(5)
WebUI.switchToDefaultContent()

WebUI.closeBrowser()


