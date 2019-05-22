package ebay.ru.common.services.containers;

import lombok.Data;
import org.openqa.selenium.Cookie;

import java.util.Set;

@Data
public class BrowserDataContainer {
    private static BrowserDataContainer instance;

    private BrowserDataContainer() {
    }

    public static BrowserDataContainer getInstance() {
        if (instance == null) {
            instance = new BrowserDataContainer();
        }
        return instance;
    }

    private Set<Cookie> coockielist;
    private String homepagehandle;
    private String dropmailhandle;
}
