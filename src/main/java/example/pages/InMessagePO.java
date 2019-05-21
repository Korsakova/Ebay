package example.pages;

import example.common.annotations.PageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageObject
public class InMessagePO extends AbstractPO {

    @FindBy(xpath = "//*[@data-name='compose']")
    WebElement newmsgbtn;


    /**
     * Click button Write new massage
     */
    @Step("newmsgbtn.click()")
    public void Newmsgbtn() {
        newmsgbtn.click();
        attachments.attachScreenShot();
    }
}
