package example.common.services.webdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

@Component
@Lazy

// TODO check this && optimize
public class LocalStorageJS {
    @Autowired
    WrappedWebdriver driver;

    private JavascriptExecutor jsExecutor;

    @PostConstruct
    public void init() {
        this.jsExecutor = (JavascriptExecutor) driver.getWrappedDriver();
    }

    /**
     * Get local storage size
     *
     * @return length
     */
    public long getLocalStorageLength() {
        return (long) jsExecutor.executeScript("return window.localStorage.length;");
    }

    /**
     * Set item in LocalStorage
     *
     * @param item  - item name
     * @param value - item value
     */
    public void setItemInLocalStorage(String item, String value) {
        jsExecutor.executeScript(String.format(
                "window.localStorage.setItem('%s','%s');", item, value));
    }

    /**
     * Clear LocalStorage
     */
    public void clearLocalStorage() {
        jsExecutor.executeScript("window.localStorage.clear();");
    }

    /**
     * Remove item from LocalStorage
     *
     * @param item - item name
     */
    public void removeItemFromLocalStorage(String item) {
        jsExecutor.executeScript(String.format(
                "window.localStorage.removeItem('%s');", item));
    }

    /**
     * Is item present in LocalStorage
     *
     * @param item - item name
     * @return true - if present<br/>false - if doesn`t present
     */
    public boolean isItemPresentInLocalStorage(String item) {
        return jsExecutor.executeScript(String.format(
                "return window.localStorage.getItem('%s');", item)) != null;
    }

    /**
     * Get item value from LocalStorage
     *
     * @param key - item name
     * @return - item value
     */
    public String getItemFromLocalStorage(String key) {
        return (String) jsExecutor.executeScript(String.format(
                "return window.localStorage.getItem('%s');", key));
    }

    /**
     * @param key
     * @return
     */
    public String getKeyFromLocalStorage(int key) {
        return (String) jsExecutor.executeScript(String.format(
                "return window.localStorage.key('%s');", key));
    }

    public List<String> getLocalStorage() {
        List<String> temp = new ArrayList<String>();
        String k = "";
        String l;
        int length = toIntExact(getLocalStorageLength());
        for (int i = 0; i < length; i++) {
            l = (
                    (String) jsExecutor.executeScript(String.format(
                            "return window.localStorage.key('%s');", i
                            )
                    ));
            if (l != null) {
                k = k.concat(l);
            }
            k = k.concat(": ");
            l = (
                    ((String) jsExecutor.executeScript(String.format(
                            "return window.localStorage.getItem(localStorage.key('%s'));", i
                            )
                    ))
            );
            if (l != null) {
                k = k.concat(l);
            }
            temp.add(k);
            k = "";
        }
        return temp;
    }
}
