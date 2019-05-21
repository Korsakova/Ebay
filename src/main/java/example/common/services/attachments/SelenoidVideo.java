package example.common.services.attachments;

import example.common.configuration.EnvProps;
import example.common.services.webdriver.WrappedWebdriver;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@Lazy
@Slf4j
public class SelenoidVideo {
    @Autowired
    @Lazy
    private WrappedWebdriver driver;
    @Autowired
    @Lazy
    public EnvProps props;

    public String getSessionId() {
        return ((RemoteWebDriver) driver.getWrappedDriver()).getSessionId().toString();
    }

    public URL getVideoUrl(String sessionId) {
        URL url = null;
        try {
            url = new URL("http://" + props.getSelenoidUrl() + "/video/" + sessionId + ".mp4");
        } catch (Exception e) {
            log.debug("getVideoUrl:", props.getSelenoidUrl() + "/video/" + sessionId + ".mp4");
            log.debug("printStackTrace:", e);
        }
        return url;
    }

    public static InputStream getSelenoidVideo(URL url) {
        int lastSize = 0;
        int exit = 1;
        for (int i = 0; i < 20; i++) {
            try {
                int size = Integer.parseInt(url.openConnection().getHeaderField("Content-Length"));
                log.debug("Content-Length: " + size);
                log.debug(String.valueOf(i));
                if (size > lastSize) {
                    lastSize = size;
                    Thread.sleep(500);
                } else if (size == lastSize) {
                    exit--;
                    Thread.sleep(200);
                }
                if (exit < 0) {
                    return url.openStream();
                }
            } catch (Exception e) {
                log.debug("checkSelenoidVideo");
                log.debug("printStackTrace:", e.getMessage());
            }
        }
        return null;
    }

    // curl -X DELETE http://127.0.0.1:4444/video/6e8a120c82d1ef17abb77e23a1cbc3a3.mp4 -> TODO try to correct decision
    public void deleteSelenoidVideo(URL url) {
        InputStream is = null;
        try {
            HttpURLConnection deleteConn = (HttpURLConnection) url.openConnection();
            deleteConn.setDoOutput(true);
            deleteConn.setRequestProperty("Accept", "application/json");
            deleteConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            deleteConn.setRequestMethod("DELETE");
            deleteConn.connect();
            is = deleteConn.getInputStream();
            if (is.available() != -1) {
                int b = is.read();
                String result = "";
                while (b != -1) {
                    result += (char) b;
                    b = is.read();
                }
            }
            deleteConn.disconnect();
//            is.close(); // TODO check this -> need?
            log.debug("DELETE VIDEO SUCCES");
        } catch (IOException e) {
            log.debug("deleteSelenoidVideo");
            log.debug("printStackTrace:", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("Error close is for delete selenoid video", e);
                }
            }
        }
    }

    public void attachAllureVideo(String sessionId) {
        try {
            URL videoUrl = new URL("http://" + props.getSelenoidUrl() + "/video/" + sessionId + ".mp4");
            InputStream is = getSelenoidVideo(videoUrl);
            Allure.addAttachment("Video", "video/mp4", is, "mp4");
            videoUrl = new URL(videoUrl.toString().replace("localhost", "127.0.0.1"));
            deleteSelenoidVideo(videoUrl);
        } catch (Exception e) {
            log.debug("attachAllureVideo");
            log.debug("printStackTrace:", e);
        }
    }
}
