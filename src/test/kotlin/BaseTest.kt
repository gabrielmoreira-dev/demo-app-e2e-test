import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.service.local.AppiumDriverLocalService
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeSuite
import java.net.URL
import java.nio.file.Paths

open class BaseTest {
    private val userDir = System.getProperty("user.dir")
    private val resourcesDir = "src/test/resources"
    private val localApp = "MyDemoApp.app"

    private lateinit var service: AppiumDriverLocalService
    protected lateinit var driver: IOSDriver

    @BeforeSuite
    protected fun startAppium() {
        service = AppiumDriverLocalService.buildDefaultService().apply { start() }
    }

    @AfterSuite
    protected fun stopAppium() = service.stop()

    @BeforeClass
    fun setUp() = DesiredCapabilities().apply {
        val appPath = Paths.get(userDir, resourcesDir, localApp).toAbsolutePath().toString()
        setCapability("appium:app", appPath)
        setCapability("platformName", "IOS")
        setCapability("appium:automationName", "XCUITest")
        setCapability("appium:deviceName", "iPhone 15 Pro")
        setCapability("appium:platformVersion", "18.0")
    }.let { driver = IOSDriver(URL("http://127.0.0.1:4723"), it) }

    @AfterClass
    fun tearDown() = driver.quit()
}