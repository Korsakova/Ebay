package example.common.configuration;

import example.common.configuration.BrowserStrategy.ChromeBrowserClass;
import example.common.configuration.BrowserStrategy.CommonDesireCaps;
import example.common.configuration.BrowserStrategy.FirefoxBrowserClass;
import example.common.services.containers.TestProps;
import example.common.services.webdriver.WrappedWebdriver;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.net.MalformedURLException;
import java.net.URI;

import static javaslang.API.Case;
import static javaslang.API.Match;

@Configuration
@Slf4j
public class WebdriverConfig {
    //    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final String CHROME = "Chrome";
    private final String FIREFOX = "Firefox";
    private final String CHROME_SELENOID = "Chrome_Selenoid";
    private final String FIREFOX_SELENOID = "Firefox_Selenoid";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Lazy
    @Autowired
    public EnvProps props;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Lazy
    @Autowired
    public CommonDesireCaps caps;

    private String testname() {
        String testname = "";
        testname = TestProps.des.getDisplayName().replace("sbb2b.tests.", "");
        return testname;
    }

    //    @Bean(destroyMethod = "quit") // destroyMethod => public void attachVideo()
    @Bean(destroyMethod = "")
    public WrappedWebdriver webDriver() {
        return Match(props.getBrowserName()).of(
                Case(CHROME::equalsIgnoreCase, this::initChrome),
                Case(FIREFOX::equalsIgnoreCase, this::initFirefox),
                Case(CHROME_SELENOID::equalsIgnoreCase, this::initSelenoidChrome),
                Case(FIREFOX_SELENOID::equalsIgnoreCase, this::initSelenoidFirefox)
        );
    }

    private WrappedWebdriver initFirefox() {
        WebDriverManager.firefoxdriver().setup();
//        FirefoxDriverManager.getInstance().arch32().setup();
        WebDriver driver = new FirefoxDriver(FirefoxBrowserClass.getFirefoxOptions());
        SetBrowserWindowSize(driver);
        return new WrappedWebdriver(driver);
    }

    private WrappedWebdriver initChrome() {
        WebDriverManager.chromedriver().setup();
//        ChromeDriverManager.getInstance().setup();
//        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver(ChromeBrowserClass.getChromeOptions());
        SetBrowserWindowSize(driver);
        return new WrappedWebdriver(driver);
    }

    private WrappedWebdriver initSelenoidFirefox() {
        log.debug("Start Firefox initialization");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion(props.getFirefoxVersion());
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, FirefoxBrowserClass.getFirefoxOptions());
        capabilities.setCapability("name", testname());
//        TODO set video name by TestName
//        capabilities.setCapability("videoName", testname());
        capabilities.merge(caps.getCommonDesCaps());
        RemoteWebDriver driver = getRemoteDriver(capabilities);
        return new WrappedWebdriver(driver);
    }

    private WrappedWebdriver initSelenoidChrome() {
        log.debug("Start Chrome initialization");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion(props.getChromeVersion());
        capabilities.setCapability(ChromeOptions.CAPABILITY, ChromeBrowserClass.getChromeOptions());
        capabilities.setCapability("name", testname());
//        TODO set video name by TestName by class
//        capabilities.setCapability("videoName", testname());
        capabilities.merge(caps.getCommonDesCaps());
        RemoteWebDriver driver = getRemoteDriver(capabilities);
        return new WrappedWebdriver(driver);
    }

    private RemoteWebDriver getRemoteDriver(DesiredCapabilities capabilities) {
        RemoteWebDriver driver = null;
        try {
            log.debug("SELENOID URL is {}", props.getSelenoidUrl());
            driver = new RemoteWebDriver(
                    URI.create("http://" + props.getSelenoidUrl() + "/wd/hub").toURL(),
                    capabilities
            );
            SetBrowserWindowSize(driver);
        } catch (MalformedURLException e) {
            log.error("Error in chrome initialization. Error message: {}", e.getMessage());
        }
        return driver;
    }

    private void SetBrowserWindowSize(WebDriver driver) {
//        driver.manage().window().maximize();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(props.getVideoScreenSizeWidth(), props.getVideoScreenSizeHeight()));
    }
}
