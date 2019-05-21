package example.pages;

import example.common.configuration.EnvProps;
import example.common.services.attachments.Attachments;
import example.common.services.webdriver.LocalStorageJS;
import example.common.services.webdriver.WebDriverTabs;
import example.common.services.webdriver.WebdriverHelper;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class AbstractPO {
    @Autowired
    @Lazy
    public WebDriver driver;

    @Autowired
    @Lazy
    protected Attachments attachments;

    @Autowired
    @Lazy
    public WebdriverHelper helper;

    @Autowired
    @Lazy
    public WebDriverTabs tabs;

    @Autowired
    @Lazy
    public LocalStorageJS LSJS;

    @Autowired
    @Lazy
    public EnvProps props;
}
