package example.common.configuration.BrowserStrategy;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowserClass {

    // TODO setup profile settings && directory ( plugins & etc)
    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--load_extension=/Users/timur/Library/Application Support/Google/Chrome/Default/Extensions/cfhdojbkjhnklbpkdaibdccddilifddb");
//        chromeOptions.addExtensions(new File("chrome/Adblock-Plus_v1.12.4.crx"));
//        chromeOptions.addExtensions();
//        chromeOptions.addArguments("user-data-dir=./src/test/profiles/chrome/testProfile/");
//        chromeOptions.setBinary("/path/to/other/chrome/binary");
        chromeOptions.addArguments("disable-web-security");
        chromeOptions.addArguments("disable-popup-blocking", "true");
        chromeOptions.addArguments("disable-infobars");
//        Map<String, Object> prefs = new HashMap<String, Object>();
//        prefs.put("credentials_enable_service", false);
//        prefs.put("profile.password_manager_enabled", false);
//        chromeOptions.setExperimentalOption("prefs", prefs);
        return chromeOptions;
    }
}
