package example.pages;

import example.common.annotations.PageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@PageObject
public class LoginPO extends AbstractPO {

    @FindBy(xpath = "//*[@id='mailbox:login']")
    WebElement loginfield;
    @FindBy(xpath = "//*[@id='mailbox:password']")
    WebElement passfield;
    @FindBy(xpath = "//*[@id='mailbox:saveauth']")
    WebElement remembermecheckbox;
    @FindBy(xpath = "//*[@class='o-control'][@type='submit']")
    WebElement loginbutton;
    @FindBy(xpath = "//*[@id='signup']")
    WebElement registerbutton;

    /**
     * Set login into loginfield
     *
     * @param login
     */
    @Step("loginfield.sendKeys(login): {0}")
    public void setLoginfield(String login) {
        loginfield.sendKeys(login);
//        helper.sendKeys(loginfield,login);
        attachments.attachScreenShot();
    }


    /**
     * Set login into passfield
     *
     * @param pass
     */
    @Step("passfield.sendKeys(pass): {0}")
    public void setPassfield(String pass) {
        passfield.sendKeys(pass);
//        helper.sendKeys(passfield,pass);
        attachments.attachScreenShot();
    }


    /**
     * Set remembercheckbox on
     */
    @Step("remembermecheckbox.click()")
    public void setCheckbox() {
        remembermecheckbox.click();
        remembermecheckbox.click();
        attachments.attachScreenShot();
    }

    /**
     * Click button login
     */
    @Step("loginbutton.click()")
    public void Loginbutton() {
        loginbutton.click();
//        helper.click(loginbutton);
        attachments.attachScreenShot();
    }

    /**
     * Click button register
     */
    @Step("registerbutton.click()")
    public void registerbutton() {
        helper.click(registerbutton);
//        registerbutton.size()
        attachments.attachScreenShot();
    }

    @Step("registerbutton.click()")
    public String gettextfrombutton() {
        attachments.attachScreenShot();
        attachments.attachPageSource();
        attachments.attachBrowserLog("LogofBrowser");
        attachments.attachPageTitle();
        return registerbutton.getText();
    }

    @Step("login as: {0}, with pass: {1}")
    public void loginas(String login, String pass) {
        loginfield.sendKeys(login);
        passfield.sendKeys(pass);
        remembermecheckbox.click();
        remembermecheckbox.click();
        attachments.attachScreenShot();
        loginbutton.click();
        attachments.attachScreenShot();
    }
}
