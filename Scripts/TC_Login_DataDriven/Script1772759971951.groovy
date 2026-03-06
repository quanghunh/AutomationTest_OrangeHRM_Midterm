import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

// 1. Mở Chrome và truy cập website
WebUI.openBrowser('')
WebUI.navigateToUrl('https://opensource-demo.orangehrmlive.com')
WebUI.maximizeWindow()

// 2. Nhập username và password từ TestData (Dùng toán tử để xử lý các ô trống trong Excel)
WebUI.setText(findTestObject('Object Repository/Login_Page/Page_OrangeHRM/txt_Username'), username != null ? username : '')
WebUI.setText(findTestObject('Object Repository/Login_Page/Page_OrangeHRM/txt_Password'), password != null ? password : '')

// 3. Click nút LOGIN
WebUI.click(findTestObject('Object Repository/Login_Page/Page_OrangeHRM/btn_Login'))

// 4. Rẽ nhánh theo cột expectedResult
switch (expectedResult) {
    case 'Login Success':
        WebUI.verifyElementPresent(findTestObject('Object Repository/Login_Page/Page_OrangeHRM/lbl_Dashboard'), 10, FailureHandling.STOP_ON_FAILURE)
        
        // Ghi chú: Ở phiên bản OrangeHRM mới, bạn cần click vào Icon User (Avatar) góc trên bên phải thì menu Logout mới xổ xuống.
        // Bạn có thể cần thêm 1 lệnh WebUI.click() vào cái avatar đó trước lệnh click Logout dưới đây nhé.
        WebUI.click(findTestObject('Object Repository/Login_Page/Page_OrangeHRM/lnk_Logout'))
        break
        
    case 'Invalid credentials':
        WebUI.verifyElementPresent(findTestObject('Object Repository/Login_Page/Page_OrangeHRM/lbl_Error'), 10, FailureHandling.STOP_ON_FAILURE)
        break
        
    case 'Username cannot be empty':
        WebUI.verifyElementPresent(findTestObject('Object Repository/Login_Page/Page_OrangeHRM/lbl_ErrorUsername'), 10, FailureHandling.STOP_ON_FAILURE)
        break
        
    case 'Password cannot be empty':
        WebUI.verifyElementPresent(findTestObject('Object Repository/Login_Page/Page_OrangeHRM/lbl_ErrorPassword'), 10, FailureHandling.STOP_ON_FAILURE)
        break
        
    default:
        WebUI.comment("Không tìm thấy expectedResult tương ứng: " + expectedResult)
        break
}

// 5. Đóng trình duyệt
WebUI.closeBrowser()