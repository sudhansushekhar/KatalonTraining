import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

String result = CustomKeywords.'scroll.ScrollAndSelectItem.readFromExcelFile'(0, 0)
WebUI.comment("Excel Text is -" + result)