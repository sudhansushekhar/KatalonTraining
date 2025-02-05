import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration


// To specify the path to data files
String path = RunConfiguration.getProjectDir() + '/Data Files/'

// Get the total number of pages in a PDF document
CustomKeywords.'com.kms.katalon.keyword.pdf.PDF.getPageNumber'(path + 'PremiumCertificate.pdf')
CustomKeywords.'com.kms.katalon.keyword.pdf.PDF.getTextFromPage'(path + 'PremiumCertificate.pdf', 3)
CustomKeywords.'com.kms.katalon.keyword.pdf.PDF.extractImagesFromPage'(path + 'PremiumCertificate.pdf', 3)
CustomKeywords.'com.kms.katalon.keyword.pdf.PDF.savePageAsImage'(path + 'PremiumCertificate.pdf', 3)

