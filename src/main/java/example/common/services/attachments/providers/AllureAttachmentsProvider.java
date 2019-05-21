package example.common.services.attachments.providers;

import com.google.common.io.Files;
import com.google.gson.JsonElement;
import example.common.configuration.EnvProps;
import example.common.services.attachments.SelenoidVideo;
import example.common.services.webdriver.WrappedWebdriver;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
@Lazy
@Slf4j
public class AllureAttachmentsProvider implements AttachmentsProvider {
    //    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Lazy
    private WrappedWebdriver driver;

    @Autowired
    @Lazy
    private EnvProps props;

    @Autowired
    @Lazy
    private SelenoidVideo selenoid;

    public static byte[] getScreenshotBytes(WebElement webElement) {
        return webElement.getScreenshotAs(OutputType.BYTES);
    }

    @Override
    @Attachment(value = "Page Screenshot", type = "image/png", fileExtension = ".png")
    public byte[] attachScreenshot() {
        log.debug("Attach screenshot to the Allure report");
        return driver.takeScreenshot();
    }

    @Override
    @Attachment(value = "{0}", type = "image/png", fileExtension = ".png")
    public byte[] attachScreenshot(String nameOfScreenshot) {
        log.debug("Attach screenshot to the Allure report");
        return driver.takeScreenshot();
    }

    @Override
    @Attachment(value = "{0}", type = "image/png", fileExtension = ".png")
    public byte[] attachScreenshot(String nameOfScreenshot, WebElement webelement) {
        log.debug("Attach screenshot to the Allure report");
        return getScreenshotBytes(webelement);
    }

    @Override
    @Attachment(value = "PageTitle", type = "text/html")
    public String attachPageTitle() {
        return driver.getTitle();
    }

    @Override
    @Attachment(value = "{0}", type = "text/html")
    public String attachText(String nameOfAttachment, String textOfAttachment) {
        return textOfAttachment;
    }

    @Attachment(value = "Html source", type = "text/html", fileExtension = ".html")
    public String attachPageSource() {
        return driver.getPageSource();
    }

    @Override
    @Attachment(value = "{0}", type = "application/json", fileExtension = "json")
    public JsonElement attachJSON(String nameOfAttachment, JsonElement textOfAttachment) {
        log.debug("Attach json to the Allure report");
        return textOfAttachment;
    }

    @Override
    @Attachment(value = "Environment", type = "application/json", fileExtension = "properties")
    public Properties attachENV() {
        log.debug("Attach properties to the Allure report");
        Properties env = new Properties();
        FileOutputStream fos = null;
        try {
            new File("./target/allure-results").mkdir();
            fos = new FileOutputStream("./target/allure-results/environment.properties");
            env.clear();
            env.setProperty("Browser", props.getBrowserName());
            env.setProperty("Browser.Version.Chrome", props.getChromeVersion());
            env.setProperty("Browser.Version.Firefox", props.getFirefoxVersion());
            env.setProperty("Site.url", props.getSiteUrl());
            env.setProperty("webdriver.explicit.timeout", String.valueOf(props.getTimeout()));
//            env.setProperty("Selenium.url", props.getSelenoidUrl());
//            env.setProperty("VNC", props.getEnableVNC());
            env.setProperty("VIDEO", props.getEnableVideo());
            env.setProperty("Resolution", String.valueOf(props.getVideoScreenSizeWidth()) + "x" + String.valueOf(props.getVideoScreenSizeHeight()));
            env.store(fos, "Environment");
            fos.close();
        } catch (IOException e) {
            log.error("IO problem when writing allure properties file", e);
        } finally {
            IOUtils.closeQuietly(fos);
        }
        return env;
    }

    @Attachment(value = "{0}", type = "text/csv", fileExtension = ".csv")
    public String attachCSV(String nameOfAttachment, String textOfAttachment) {
        log.debug("Attach json to the Allure report");
        return textOfAttachment;
    }

    @Attachment(value = "{0}", type = "text/plain", fileExtension = ".txt")
    public String attachBrowserLog(String nameOfAttachment) {
        log.debug("Attach BrowserLog to the Allure report");
        String log = ""; // TODO Check Firefox options
        if (!props.getBrowserName().toLowerCase().contains("firefox")) {
            LogEntries logentries = driver.manage().logs().get(LogType.BROWSER);
            for (LogEntry entry : logentries) {
                log = log + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage() + "\n";
            }
        } else {
            log = "Browser Logs not accessible in firefox";
        }
        return log;
    }

    @Attachment(value = "{0}", type = "text/plain", fileExtension = ".txt")
    public String attachPerfomanceLog(String nameOfAttachment) {
        log.debug("Attach PerfomanceLog to the Allure report");
        LogEntries logentries = driver.manage().logs().get(LogType.PERFORMANCE);
        String log = "";
        for (LogEntry entry : logentries) {
            log = log + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage() + "\n";
        }
        return log;
    }

    @Attachment(value = "{0}", type = "text/plain", fileExtension = ".txt")
    public String attachProfilereLog(String nameOfAttachment) {
        log.debug("Attach ProfileLog to the Allure report");
        LogEntries logentries = driver.manage().logs().get(LogType.PROFILER);
        String log = "";
        for (LogEntry entry : logentries) {
            log = log + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage() + "\n";
        }
        return log;
    }

    @Attachment(value = "{0}", type = "text/plain", fileExtension = ".txt")
    public String attachServerLog(String nameOfAttachment) {
        log.debug("Attach ServerLog to the Allure report");
        LogEntries logentries = driver.manage().logs().get(LogType.SERVER);
        String log = "";
        for (LogEntry entry : logentries) {
            log = log + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage() + "\n";
        }
        return log;
    }

    @Attachment(value = "{0}", type = "text/plain", fileExtension = ".txt")
    public String attachClientLog(String nameOfAttachment) {
        log.debug("Attach ClientLog to the Allure report");
        LogEntries logentries = driver.manage().logs().get(LogType.CLIENT);
        String log = "";
        for (LogEntry entry : logentries) {
            log = log + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage() + "\n";
        }
        return log;
    }

    // TODO check this for correct decision (Video attachment) && or use current method
    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Video HTML", type = "text/html", fileExtension = ".html")
    public String attachVideoInHTML(String sessionId) {
        log.debug("Attach VideoInHTML to the Allure report");
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + selenoid.getVideoUrl(sessionId)
                + "' type='video/mp4'></video></body></html>";
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Video", type = "video/mp4", fileExtension = ".mp4")
    public byte[] attachVideo(String sessionId) {
        log.debug("Attach Video to the Allure report");
        try {
            File mp4 = new File(System.getProperty("java.io.tmpdir") + "temp.mp4");
            mp4.deleteOnExit();
            FileUtils.copyURLToFile(selenoid.getVideoUrl(sessionId), mp4);
            return Files.toByteArray(mp4);
        } catch (Exception e) {
            log.debug("printStackTrace:", e);
        }
        return new byte[0];
    }
}
