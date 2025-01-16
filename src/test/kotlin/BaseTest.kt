import io.appium.java_client.AppiumDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.ios.options.XCUITestOptions
import io.appium.java_client.service.local.AppiumDriverLocalService
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeSuite
import java.net.URL
import java.nio.file.Paths

open class BaseTest {
    companion object {
        const val APP_DIR = "src/test/resources/MyDemoApp.app"
        const val AUTOMATION_NAME = "XCUITest"
        const val DEVICE_NAME = "iPhone 15 Pro"
        const val PLATFORM_NAME = "IOS"
        const val PLATFORM_VERSION = "18.0"
        const val SERVER_URL = "http://127.0.0.1:4723"
    }

    private lateinit var service: AppiumDriverLocalService
    protected lateinit var driver: AppiumDriver

    @BeforeSuite
    protected fun startAppium() {
        service = AppiumDriverLocalService.buildDefaultService().apply { start() }
    }

    @AfterSuite
    protected fun stopAppium() = service.stop()

    @BeforeClass
    protected fun setUpDriver() = XCUITestOptions()
        .setApp(Paths.get(System.getProperty("user.dir"), APP_DIR).toAbsolutePath().toString())
        .setAutomationName(AUTOMATION_NAME)
        .setDeviceName(DEVICE_NAME)
        .setPlatformName(PLATFORM_NAME)
        .setPlatformVersion(PLATFORM_VERSION)
        .let { driver = IOSDriver(URL(SERVER_URL), it) }

    @AfterClass
    protected fun dispose() = driver.quit()
}