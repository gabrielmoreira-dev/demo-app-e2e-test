import io.appium.java_client.AppiumBy
import org.testng.annotations.Test

class ProductDetailTests: IOSBaseTest() {
    @Test
    fun testProductDetail() {
        driver.findElement(AppiumBy.xpath("(//XCUIElementTypeOther[@name=\"ProductItem\"])[1]")).click()
    }
}