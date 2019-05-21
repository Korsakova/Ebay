package example.common.services.utils;

import example.common.services.attachments.Attachments;
import example.common.services.webdriver.WebdriverHelper;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
@Service
public class Check {
    // TODO asserts
    // users
    // basket
    // orders
    // requests
    // bills
    // roles
    // serps

    @Autowired
    @Lazy
    public WebDriver driver;

    @Autowired
    @Lazy
    public WebdriverHelper helper;

    @Autowired
    @Lazy
    protected Attachments attachments;

    /**
     * This class for check URL Assertions -> part of path
     */
    @Step("Check URL path: {1}")
    public void checkURLpath(SoftAssertions sa, String Path, String Description) throws URISyntaxException {
        helper.waitForPageUpdated();
        attachments.attachPageText("URL", driver.getCurrentUrl());
        attachments.attachScreenShot("Before Checking");
        attachments.attachBrowserLog("log before checking");
        String URL = new URI(driver.getCurrentUrl()).toString();
        sa.assertThat(URL).contains(Path).info.description(Description);
    }

    /**
     * This class for check String Assertions -> List part of path
     */
    @Step("Check List<String> path: {2} in {1}")
    public void checkStringListPath(SoftAssertions sa, String Main, List<String> Path) {
        attachments.attachScreenShot("page before checking");
        attachments.attachPageSource();
        attachments.attachBrowserLog("log before checking");
        sa.assertThat(Main).contains(Path);
    }

    /**
     * This class for check String Assertions -> part of path
     */
    @Step("Check String path: {2} in {1}")
    public void checkStringPath(SoftAssertions sa, String Main, String Path) {
        attachments.attachScreenShot("page before checking");
        attachments.attachPageSource();
        attachments.attachBrowserLog("log before checking");
        sa.assertThat(Main).contains(Path);
    }

    /**
     * This class for check Price Assertions ->
     */
    @Step("Check price: Mprice:{1}; discount{2}, Aprice{3}")
    public void checkprice(SoftAssertions sa, String mainprice, String discount, String price) {
        attachments.attachScreenShot("page before checking");
        attachments.attachPageSource();
        attachments.attachBrowserLog("log before checking");
        Double pr = Double.valueOf(mainprice) * (100 - Double.valueOf(discount));
        sa.assertThat(pr.toString()).isEqualTo(price);
    }

    /**
     * This class for check Discount Assertions ->
     */
    @Step("Check price: Mprice:{1}; discount{2}, Aprice{3}")
    public void checkdiscount(SoftAssertions sa, String mainprice, String discount, String price) {
        attachments.attachScreenShot("page before checking");
        attachments.attachPageSource();
        attachments.attachBrowserLog("log before checking");
        Double ds = Double.valueOf(price) / Double.valueOf(mainprice);
        sa.assertThat(ds.toString()).isEqualTo(discount);
    }
}
