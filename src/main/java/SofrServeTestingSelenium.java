import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.time.Duration;
/*
  @author   george
  @project   sft-srv-selen
  @class  SofrServeTestingSelenium
  @version  1.0.0 
  @since 24.03.23 - 22.13
*/

public class SofrServeTestingSelenium {

    private static final String BASE_URL = "https://demo.opencart.com/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final Long ONE_SECOND_DELAY = 1000L;
    private static final int ONE_SECOND_WAIT = 1;

    private void presentationSleep(int seconds) throws InterruptedException {
            Thread.sleep(seconds * ONE_SECOND_DELAY);
    }

    @Test
    public void checkMacBook() throws InterruptedException {
        // given
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
        presentationSleep(ONE_SECOND_WAIT);
        // when
        driver.get(BASE_URL);
        presentationSleep(ONE_SECOND_WAIT);
 //   System.out.println(driver.getPageSource());
        driver.findElement(By.className("dropdown")).click();
        presentationSleep(ONE_SECOND_WAIT);
        driver.findElement(By.partialLinkText("Euro")).click();
        presentationSleep(ONE_SECOND_WAIT);
        driver.findElement(By.name("search")).click();
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("mac", Keys.ENTER);
        presentationSleep(ONE_SECOND_WAIT);
        //then
        WebElement price = driver.findElement(By.xpath("//a[text()='iMac']/../following-sibling::p[@class='price']"));
        Assert.assertFalse(price.getText().contains("111.55€"));

        // tears down
        driver.quit();
    }





}
