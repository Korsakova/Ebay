package example.common.services.webdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
@Lazy
public class WebDriverTabs {

    @Autowired
    private WrappedWebdriver driver;

    private JavascriptExecutor remoteWebDriver;

    @PostConstruct
    public void init() {
        this.remoteWebDriver = (JavascriptExecutor) driver.getWrappedDriver();
    }

    /**
     * open new tab
     */
    // TODO check this for firefox
    public void opentab() {
        remoteWebDriver.executeScript("window.open();");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWrappedDriver().getWindowHandles());
        driver.getWrappedDriver().switchTo().window(tabs.get(tabs.size() - 1));
        tabs.clear();
    }

    /**
     * can close only addition tabs
     */
    public void closetab() {
        remoteWebDriver.executeScript("window.close();");
    }

    public void switchtabint(int i) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWrappedDriver().getWindowHandles());
        driver.getWrappedDriver().switchTo().window(tabs.get(i));
        tabs.clear();

    }

    public void switchtabhandle(String windowshandle) {
        driver.getWrappedDriver().switchTo().window(windowshandle);
    }

}
