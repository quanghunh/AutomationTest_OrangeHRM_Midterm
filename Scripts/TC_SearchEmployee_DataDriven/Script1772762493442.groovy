import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

// 1. Mở Chrome, truy cập và Đăng nhập (Yêu cầu fix cứng tài khoản Admin)
WebUI.openBrowser('')
WebUI.navigateToUrl('https://opensource-demo.orangehrmlive.com')
WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/Login_Page/Page_OrangeHRM/txt_Username'), 'Admin')
WebUI.setText(findTestObject('Object Repository/Login_Page/Page_OrangeHRM/txt_Password'), 'admin123')
WebUI.click(findTestObject('Object Repository/Login_Page/Page_OrangeHRM/btn_Login'))

// 2. Vào menu PIM -> Employee List
WebUI.click(findTestObject('Object Repository/PIM_Page/mnu_PIM'))
WebUI.click(findTestObject('Object Repository/PIM_Page/mnu_EmployeeList'))

// 3. Nhập từ khóa vào ô tìm kiếm và click Search
WebUI.setText(findTestObject('Object Repository/PIM_Page/txt_SearchName'), keyword != null ? keyword : '')
WebUI.click(findTestObject('Object Repository/PIM_Page/btn_Search'))

// 4. Rẽ nhánh theo cột shouldFound
if (shouldFound.toString().toLowerCase() == 'true') {
    // true -> verify tên nhân viên đầu tiên trong bảng chứa expectedName
    String actualName = WebUI.getText(findTestObject('Object Repository/PIM_Page/itm_FirstResult'))
    WebUI.verifyMatch(actualName, '.*' + expectedName + '.*', true, FailureHandling.STOP_ON_FAILURE)
} else {
    // false -> verify bảng hiển thị thông báo "No Records Found"
    WebUI.verifyElementPresent(findTestObject('Object Repository/PIM_Page/lbl_NoRecord'), 10, FailureHandling.STOP_ON_FAILURE)
}

// 5. Click Logout và đóng trình duyệt
// (Có thể cần click vào Icon Profile trước khi click lnk_Logout tùy giao diện thực tế)
WebUI.click(findTestObject('Object Repository/Login_Page/Page_OrangeHRM/lnk_Logout'))
WebUI.closeBrowser()