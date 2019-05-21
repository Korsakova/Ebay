package example.tests;

import example.steps.CommonStepDef;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.net.URISyntaxException;

@Slf4j
public class BaseTest extends CommonStepDef {

    /*
    Scenario
    1. открыть мэйл ру
    2. ввести логин и пароль, поиграть чекбоком запомнить меня
    3. нажать кнопку войти и попасть в почту
    4. нажать написать новое письмо
    5. ввести текст письма и получателя самого себя
    6. отправить письмо
    7. перейти во входящие и перейти в пришедшее письмо с темой (рандомной)
    8. которое было отправлено шагом ранее
    9. проверить рандомный текст отправленного письма с текстом пришедшим (рандомный текст)
    */
    String loginname = "preymana";

    @Test
    public void sendmessage()throws URISyntaxException  {
        String thememessage = gen.getrandomstring(8);
        String message = gen.getrandomstring(20) + "\n" + gen.getrandomstring(10);
        SoftAssertions sa = new SoftAssertions();
        homePO.open();

        loginPO.setLoginfield(loginname);
        loginPO.setPassfield("asdf1234");
        loginPO.setCheckbox();
        loginPO.Loginbutton();
        // do not use it please at anonymous
        check.checkURLpath(sa, "mail.ru", "URL ASSECRTION FOR HOMEPAGE");


        //String k2 = loginPO.gettextfrombutton();
//        log.info("Text from button: {}", k2);
//        loginPO.loginas("user", "pass");
//        sa.assertThat();

        log.info("Current Page: {}", homePO.getCurrentUrl());


        inMessagePO.Newmsgbtn();
        log.info("Current Page: {}", homePO.getCurrentUrl());
        newMessagePO.setThemeofmessage(thememessage);
        newMessagePO.setAddressofmessage(loginname + "@mail.ru");
        newMessagePO.setMessagefield(message);
        log.info("Current Page: {}", homePO.getCurrentUrl());
        newMessagePO.sendbutton();   //uncomment it after made works under setMessagefield
        log.info("Current Page: {}", homePO.getCurrentUrl());
        inboxPO.inboxbutton();
        inboxPO.clickmessagebyid(inboxPO.getmessagenumber(thememessage));
        inboxPO.equalsgetmessage(sa, message);

//        sa.assertThat("").contains(loginname);


        sa.assertAll();
    }


}