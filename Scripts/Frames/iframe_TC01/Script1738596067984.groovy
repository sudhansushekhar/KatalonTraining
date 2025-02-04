import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.junit.Assert.assertEquals

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


WebUI.openBrowser('')
WebUI.maximizeWindow()

WebUI.navigateToUrl('https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert')
WebUI.waitForPageLoad(10)
WebUI.switchToFrame(findTestObject('Object Repository/Frames/frame1'), 2)

WebUI.click(findTestObject('Object Repository/Frames/TryItBtn'))

//accept alert
String alertTextActual = WebUI.getAlertText()
String alertTextExp = "Hello! I am an alert box!"

//if you don't want to run any particular test step add text "not_run:" at the beginning to the step
not_run: assertEquals(alertTextActual, alertTextExp)  //disabled step
assertEquals(alertTextActual, alertTextExp)  //enabled step
//Accept alert

WebUI.acceptAlert()

//Return Back to main content
WebUI.switchToDefaultContent()

String runTextActual = WebUI.getText(findTestObject('Object Repository/Frames/RunBtn'))
String runTextExp = "Run ❯"
assertEquals(runTextActual, runTextExp)
WebUI.closeBrowser()