package example.common.configuration.BrowserStrategy;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxBrowserClass {

    // TODO setup profile settings && directory ( plugins & etc)
    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxoptions = new FirefoxOptions();
        FirefoxProfile fp = new FirefoxProfile();
        fp.setAcceptUntrustedCertificates(true);
        fp.setAssumeUntrustedCertificateIssuer(true);
        fp.setPreference("security.checkloaduri", false);
        firefoxoptions.setProfile(fp);
//        firefoxoptions.setBinary("/path/to/other/chrome/binary");
//         TODO check https://github.com/SeleniumHQ/selenium/issues/3827
//        Map<String, Object> prefs = new HashMap<String, Object>();
//        prefs.put("credentials_enable_service", false);
//        prefs.put("profile.password_manager_enabled", false);
        // Exp options?
        return firefoxoptions;
    }
}
