import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

/*
- Открыть iFrame
- Проверить, что текст внутри параграфа равен “Your content
goes here.”
 */
public class FramesTest {

    @Test
    public void  checkFrame() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/frames");

        driver.findElement(By.cssSelector("[href='/iframe']")).click();
        driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
        assertEquals(driver.findElement(By.cssSelector("p")).getText(), "Your content goes here.");
        driver.switchTo().defaultContent();
        driver.quit();
    }
}
