package ebay.ru.pages;

import ebay.ru.common.annotations.PageObject;
import ebay.ru.common.services.webdriver.WrappedWebdriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;


@PageObject
public class NewMessagePO extends AbstractPO {

    @Autowired
    @Lazy
    private WrappedWebdriver driver;


//        @FindBy(xpath = "//*[@id='jsHtml']")
    @FindBy(xpath = "//*[substring(name(), string-length(name()) - 6) = 'iframe'][@id]")
    WebElement messagefieldframe;

        @FindBy(xpath = "//*[@id='tinymce']")
//    @FindBy(xpath = "//*[@class='mceContentBody compose2']")
    WebElement messagebody;

    @FindBy(xpath = "//*[@class='b-input']")
    WebElement themeofmessage;

    @FindBy(xpath = "//*[@data-original-name='To']")
    WebElement addressofmessage;

    @FindBy(xpath = "//*[@data-name='send'][@data-bem='b-toolbar__btn']")
    List<WebElement> sendbutton;

    /**
     * Set message text into messagefield
     *
     * @param newmsg
     */
    @Step("messagebody.sendKeys(newmsg): {0}")
    public void setMessagefield(String newmsg) {
//        Wait<WebDriver> wait = new WebDriverWait(driver.getWrappedDriver(), props.getTimeout() / 7000, 20);
//        wait.until(ExpectedConditions.visibilityOf(messagefieldframe));
//        driver.switchTo().frame(0);
//       driver.switchTo().frame(messagefieldframe.get(0));
//        driver.switchTo().frame(messagefieldframe.get(0));
//
//        helper.SwitchFrame(messagefieldframe.get(4));
//        helper.click(messagebody);
//        helper.sendKeys(messagebody,newmsg);
        helper.SwitchFrame(messagefieldframe);
        helper.waitForElementIsVisible(messagebody);
//        messagebody.click();
//        messagebody.clear();//работает
//        helper.SwitchDefaultFrame();

//        driver.findElement(By.xpath("//*[substring(name(), string-length(name()) - 6) = 'iframe'][@id]"));
//        driver.switchTo().defaultContent();

        messagebody.sendKeys(newmsg);
        attachments.attachScreenShot();
//        driver.switchTo().defaultContent();
        helper.SwitchDefaultFrame();
    }

    /**
     * Click button send message
     */
    @Step("sendbutton.click()")
    public void sendbutton() {
        sendbutton.get(0).click();
//        helper.click(sendbutton.get(0));
        attachments.attachScreenShot();
    }

    /**
     * Set theme text into field themeofmessage
     *
     * @param newtheme
     */
    @Step("themeofmessage.sendKeys(newtheme): {0}")
    public void setThemeofmessage(String newtheme) {
        themeofmessage.sendKeys(newtheme);
        attachments.attachScreenShot();

    }

    /**
     * Set E-mail addres into field addressofmessage
     *
     * @param address
     */

    @Step("addressofmessage.sendKeys(address): {0}")
    public void setAddressofmessage(String address) {

        addressofmessage.sendKeys(address);
        attachments.attachScreenShot();
    }





}

