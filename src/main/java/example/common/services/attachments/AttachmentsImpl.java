package example.common.services.attachments;

import com.google.gson.JsonElement;
import example.common.services.attachments.providers.AllureAttachmentsProvider;
import example.common.services.attachments.providers.AttachmentsProvider;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@Lazy
public class AttachmentsImpl implements Attachments {
    private Set<AttachmentsProvider> attachmentProviders = new HashSet<>();

    @Autowired
    @Lazy
    AllureAttachmentsProvider allureAttachments;

    @PostConstruct
    public void init() {
        attachmentProviders.add(allureAttachments);
        attachmentProviders = Collections.unmodifiableSet(attachmentProviders);
    }

    @Override
    public void attachPageTitle() {
        attachmentProviders.stream().forEach(provider -> provider.attachPageTitle());
    }

    @Override
    public void attachPageText(String nameOfAttachment, String textOfAttachment) {
        attachmentProviders.stream().forEach(provider -> provider.attachText(nameOfAttachment, textOfAttachment));
    }

    @Override
    public void attachPageSource() {
        attachmentProviders.stream().forEach(provider -> provider.attachPageSource());
    }

    @Override
    public void attachScreenShot() {
        attachmentProviders.stream().forEach(provider -> provider.attachScreenshot());
    }

    @Override
    public void attachScreenShot(String nameOfScreenshot) {
        attachmentProviders.stream().forEach(provider -> provider.attachScreenshot(nameOfScreenshot));
    }

    @Override
    public void attachScreenShot(String nameOfScreenshot, WebElement webelement) {
        attachmentProviders.stream().forEach(provider -> provider.attachScreenshot(nameOfScreenshot, webelement));
    }

    // added for json element (file *.txt with String)
    public void attachJSON(String nameOfAttachment, JsonElement textOfAttachment) {
        attachmentProviders.stream().forEach(provider -> provider.attachJSON(nameOfAttachment, textOfAttachment));
    }

    public void attachENV() {
        attachmentProviders.stream().forEach(provider -> provider.attachENV());
    }

    public void attachCSV(String nameOfAttachment, String textOfAttachment) {
        attachmentProviders.stream().forEach(provider -> provider.attachCSV(nameOfAttachment, textOfAttachment));
    }

    public void attachBrowserLog(String nameOfAttachment) {
        attachmentProviders.stream().forEach(provider -> provider.attachBrowserLog(nameOfAttachment));
    }

    public void attachPerfomanceLog(String nameOfAttachment) {
        attachmentProviders.stream().forEach(provider -> provider.attachPerfomanceLog(nameOfAttachment));
    }

    public void attachProfilereLog(String nameOfAttachment) {
        attachmentProviders.stream().forEach(provider -> provider.attachProfilereLog(nameOfAttachment));
    }

    public void attachServerLog(String nameOfAttachment) {
        attachmentProviders.stream().forEach(provider -> provider.attachServerLog(nameOfAttachment));
    }

    public void attachClientLog(String nameOfAttachment) {
        attachmentProviders.stream().forEach(provider -> provider.attachClientLog(nameOfAttachment));
    }

    public void attachVideoInHTML(String sessionId) {
        attachmentProviders.stream().forEach(provider -> provider.attachVideoInHTML(sessionId));
    }

    public void attachVideo(String sessionId) {
        attachmentProviders.stream().forEach(provider -> provider.attachVideo(sessionId));
    }

}
