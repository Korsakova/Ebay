package example.common.services.attachments;

import com.google.gson.JsonElement;
import org.openqa.selenium.WebElement;

public interface Attachments {
    void attachScreenShot();

    void attachScreenShot(String nameOfScreenshot);

    void attachScreenShot(String nameOfScreenshot, WebElement webelement);

    void attachPageTitle();

    void attachPageText(String nameOfAttachment, String textOfAttachment);

    void attachPageSource();

    void attachJSON(String nameOfAttachment, JsonElement textOfAttachment);

    void attachENV();

    void attachCSV(String nameOfAttachment, String textOfAttachment);

    void attachBrowserLog(String nameOfAttachment);

    void attachPerfomanceLog(String nameOfAttachment);

    void attachProfilereLog(String nameOfAttachment);

    void attachServerLog(String nameOfAttachment);

    void attachClientLog(String nameOfAttachment);

    void attachVideoInHTML(String sessionId);

    void attachVideo(String sessionId);
}
