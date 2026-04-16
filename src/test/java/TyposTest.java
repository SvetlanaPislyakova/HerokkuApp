import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TyposTest {

    @Test
    public void checkTypos() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/typos");
        String correctText = "Sometimes you'll see a typo, other times you won't.";
        for (int i= 0; i < 10; i++) {
            WebElement text = driver.findElement(By.xpath("//p[2]"));
            softAssert.assertEquals(text.getText(), correctText);
            driver.navigate().refresh();
        }
        driver.quit();
        softAssert.assertAll();
    }
}
