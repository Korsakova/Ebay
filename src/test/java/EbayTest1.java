import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class EbayTest1 {
    public ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","/Users/akorsakova/Downloads/chromedriver_win32");
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("test start");
    }

    @Test
    public void ETest() {
        driver.get("https://www.ebay.com/");

        String title = driver.getTitle();
        WebElement searchButton = driver.findElement(By.xpath("https://reg.ebay.com/reg/PartialReg?ru=https%3A%2F%2Fru.ebay.com%2F")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedCondition.visibilityOfElementLocated(By.xpath("giant-text-2"));
        WebElement searchField = driver.findElement(By.id("firstname")).sendKeys("Ana");
        WebElement searchField = driver.findElement(By.id("lastname")).sendKeys("Sorov");

        Random random = new Random();
        int n = random.nextInt(100)+1;
        String email = "test" + n + "@ya.ru";
        System.out.println(email);

        WebElement searchField = driver.findElement(By.id("email")).sendKeys(email);
        WebElement searchField = driver.findElement(By.id("PASSWORD")).sendKeys("123@Qw");
        WebElement searchButton = driver.findElement(By.id("ppaFormSbtBtn")).click();
        wait.until(ExpectedCondition.visibilityOfElementLocated(By.xpath("Мой eBay"));

        searchField.sendKeys("blackberry");
        WebElement searchButton = driver.findElement(By.xpath("gh-btn")).click();

        WebElement searchButton = driver.findElement(By.id("gh-ug")).click();
        WebElement searchButton = driver.findElement(By.xpath("m570.l2622")).click();
        wait.until = wait.until(ExpectedCondition.visibilityOfElementLocated(By.id("GREET-HELLO"));
    }

    @After
    public void close(){
        System.out.println("test close");
        driver.quit();
    }

}