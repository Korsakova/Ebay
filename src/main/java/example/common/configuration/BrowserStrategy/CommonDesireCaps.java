package example.common.configuration.BrowserStrategy;

import example.common.configuration.EnvProps;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.logging.Level;

@Configuration
@Lazy
public class CommonDesireCaps {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Lazy
    @Autowired
    public EnvProps props;

    public DesiredCapabilities getCommonDesCaps() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", Boolean.valueOf(props.getEnableVNC()));
        capabilities.setCapability("enableVideo", Boolean.valueOf(props.getEnableVideo()));
        if (Boolean.valueOf(props.getEnableVideo())) {
            capabilities.setCapability("videoFrameRate", 24);
        }
        capabilities.setCapability("videoScreenSize", String.valueOf(props.getVideoScreenSizeWidth()) + "x" + String.valueOf(props.getVideoScreenSizeHeight()));
        capabilities.setCapability("timeZone", "W-SU"); // timeZone: "Europe/Moscow"
        capabilities.setCapability("hostsEntries", props.getHosts()); // add to /etc/hosts in containers
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability("javascriptEnabled",true);
        capabilities.acceptInsecureCerts();
        capabilities.setCapability("acceptInsecureCerts", true);
        capabilities.setCapability("marionette", true);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_WEB_STORAGE, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_SQL_DATABASE, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_LOCATION_CONTEXT, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);

        // Set Logging Level
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, getLogPrefs());
        return capabilities;
    }

    private LoggingPreferences getLogPrefs() {
        LoggingPreferences logPref = new LoggingPreferences();
        logPref.enable(LogType.BROWSER, Level.ALL);
        logPref.enable(LogType.CLIENT, Level.SEVERE);
        logPref.enable(LogType.DRIVER, Level.WARNING);
        logPref.enable(LogType.PERFORMANCE, Level.INFO);
//        logPref.enable(LogType.PROFILER, Level.ALL);
//        logPref.enable(LogType.SERVER, Level.ALL);
        return logPref;
    }
}
