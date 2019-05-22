package ebay.ru.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
//@Data
public class EnvProps {
    @Value("${WEB_SCHEME:http://}")
    private String webscheme;
    @Value("${BASE_AUTH_LOGIN:egazin}")
    private String baseauthlogin;
    @Value("${BASE_AUTH_PASS:isnzr1Uc}")
    private String baseauthpass;
    @Value("${WEBDRIVER_BROWSER:Firefox}")
    private String browserName;
    @Value("${SELENOID_URL:localhost}")
    protected String selenoidUrl;
    @Value("${FIREFOX_VERSION:latest}")
    private String firefoxVersion;
    @Value("${CHROME_VERSION:latest}")
    private String chromeVersion;
    @Value("${SITE_URL:localhost}")
    protected String siteUrl;
    @Value("${SITE_URL_AUTH:localhost}")
    protected String siteUrlbaseath;
    @Value("${SELENOID_ENABLE_VNC:true}")
    private String enableVNC;
    @Value("${SELENOID_ENABLE_VIDEO:true}")
    private String enableVideo;
    @Value("${SELENOID_SCREEN_WIDTH:1920}")
    private int videoScreenSizeWidth;
    @Value("${SELENOID_SCREEN_HEIGHT:1080}")
    private int videoScreenSizeHeight;
    @Value("${MAVEN_TIMEOUT:30000}")
    private int timeout;
    @Value("${PROFILER:true}")
    protected Boolean debug;
    @Value("#{'${HOSTS}'.split(',')}")
    private String[] hosts;
    @Value("${BASE_AUTH_ENABLE:true}")
    protected Boolean baseauthenable;

    public String getBrowserName() {
        return browserName;
    }

    public String getSelenoidUrl() {
        System.setProperty("selenoidUrl", selenoidUrl);
        return selenoidUrl;
    }

    public String getFirefoxVersion() {
        return firefoxVersion;
    }

    public String getChromeVersion() {
        return chromeVersion;
    }

    public String getSiteUrl() {
        return webscheme + siteUrl;
    }

    public Boolean getBaseauthenable() {
        return baseauthenable;
    }

    public String getSiteUrlbaseath() {
        return webscheme + baseauthlogin + ":" + baseauthpass + "@" + siteUrl;
    }

    public String getEnableVNC() {
        return enableVNC;
    }

    public String getEnableVideo() {
        return enableVideo;
    }

    public int getVideoScreenSizeWidth() {
        return videoScreenSizeWidth;
    }

    public int getVideoScreenSizeHeight() {
        return videoScreenSizeHeight;
    }

    public int getTimeout() {
        return timeout;
    }

    public String[] getHosts() {
        for (int i = 0; i < hosts.length; i++) {
            hosts[i] = siteUrl + ":" + hosts[i];
        }
        return hosts;
    }
}
