package ebay.ru.pages;

import ebay.ru.common.annotations.PageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

@PageObject
public class HomePO extends AbstractPO {

    @Step("Open main page")
    public void open() {
        if (props.getBaseauthenable()) {
            driver.get(props.getSiteUrlbaseath());
        }
        driver.get(props.getSiteUrl());
        attachments.attachScreenShot();
        attachments.attachENV();
    }

    /**
     * Return url of home page
     *
     * @return String
     */
    public String getSiteUrl() {
        return props.getSiteUrl();
    }

    public String gethomepagehandle() {
        helper.waitForPageUpdated();
        String homepagehandle = driver.getWindowHandle();
        return homepagehandle;
    }

    public String getCurrentUrl() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl;
    }

    @Step("Change URL path: {1}")
    public void changeurlpath(WebDriver driver, String NewURL) {
        driver.get(NewURL);
    }
}
