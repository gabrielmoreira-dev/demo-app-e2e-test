import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import java.net.URL
import java.nio.file.Paths

open class IOSBaseTest {
    lateinit var driver: IOSDriver
    private val serverURL = URL("http://127.0.0.1:4723")
    private val userDir = System.getProperty("user.dir")
    private val resourcesDir = "src/test/resources"
    private val localApp = "MyDemoApp.app"

    @BeforeClass
    fun setUp() {
        val capabilities = DesiredCapabilities()
        val appPath = Paths.get(userDir, resourcesDir, localApp).toAbsolutePath().toString()
        capabilities.setCapability("appium:app", appPath)
        capabilities.setCapability("platformName", "IOS")
        capabilities.setCapability("appium:automationName", "XCUITest")
        capabilities.setCapability("appium:deviceName", "iPhone 15 Pro")
        capabilities.setCapability("appium:platformVersion", "18.0")
        driver = IOSDriver(serverURL, capabilities)
    }

    @AfterClass
    fun tearDown() {
        driver.quit()
    }
}