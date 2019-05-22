package ebay.ru.common.services.attachments.providers;

import com.google.gson.JsonElement;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public interface AttachmentsProvider {
    byte[] attachScreenshot();

    byte[] attachScreenshot(String nameOfScreenshot);

    byte[] attachScreenshot(String nameOfScreenshot, WebElement webelement);

    String attachPageTitle();

    String attachText(String nameOfAttachment, String textOfAttachment);

    String attachPageSource();

    JsonElement attachJSON(String nameOfAttachment, JsonElement textOfAttachment);

    Properties attachENV();

    String attachCSV(String nameOfAttachment, String textOfAttachment);

    String attachBrowserLog(String nameOfAttachment);

    String attachPerfomanceLog(String nameOfAttachment);

    String attachProfilereLog(String nameOfAttachment);

    String attachServerLog(String nameOfAttachment);

    String attachClientLog(String nameOfAttachment);

    String attachVideoInHTML(String sessionId);

    byte[] attachVideo(String sessionId);
}
