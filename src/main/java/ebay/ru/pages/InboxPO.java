package ebay.ru.pages;


import ebay.ru.common.annotations.PageObject;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

@PageObject
@Slf4j
public class InboxPO extends AbstractPO {

    @FindBy(xpath = "//*[@href='/messages/inbox/']")
    List<WebElement> inboxbutton;

    //    @FindBy(xpath = "//*[@id='b-letters']")
    @FindBy(xpath = "//*[@class='b-datalist__item__subj']")
    List<WebElement> thememessages;

    //    @FindBy(xpath = "//*[@class='class_1551309823']")
    @FindBy(xpath = "//*[@class='js-body b-letter__body__wrap']")
    WebElement textofmessage;


    /**
     * Click button Go to inbox
     */
    @Step("inboxbutton.click()")
    public void inboxbutton() {
        helper.click(inboxbutton.get(1));
        attachments.attachScreenShot();
    }

    /**
     * Go to message with theme sended step before
     * $param
     */
    @Step("newincome.click()")
    public int getmessagenumber(String thememessage) {
        int messagenumber = 0;
//        while (helper.getText(thememessages.get(messagenumber)).equalsIgnoreCase(thememessage)) {
//        String k = helper.getText(thememessages.get(0));
//        need make conteins besides equals
//        while (true) {
        while (!helper.getText(thememessages.get(messagenumber)).contains(thememessage)) {
            driver.navigate().refresh();
            for (int i = 0; i < thememessages.size(); i++) {
                if (helper.getText(thememessages.get(i)).contains(thememessage)) {
                    messagenumber = i;
                }
            }
//            if (helper.getText(thememessages.get(messagenumber)).contains(thememessage)) {
//                break;
//            }
        }
        return messagenumber;
    }


    @Step
    public void clickmessagebyid(int number) {
        helper.click(thememessages.get(number));
        attachments.attachScreenShot();
    }


    @Step()
    public void equalsgetmessage(SoftAssertions sa, String message) {
        log.info("Text message assertions: {}", textofmessage.getText());
        attachments.attachScreenShot();
        sa.assertThat(textofmessage.getText()).contains(message);
    }

}