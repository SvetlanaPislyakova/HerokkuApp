import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/*
- нажать на кнопку Remove около чекбокса
- дождаться надписи “It’s gone”
- проверить, что чекбокса нет
- найти инпут
- проверить, что он disabled
- нажать на кнопку
- дождаться надписи “It's enabled!”
- проверить, что инпут enabled
 */
public class DynamicControlsTest {

    @Test
    public void checkDynamicControls() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        driver.findElement(By.xpath("//*[text()='Remove']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        WebElement input = driver.findElement(By.xpath("//input[@type='text']"));
        assertNotNull(input.getAttribute("disabled"));
        driver.findElement(By.xpath("//*[@onclick='swapInput()']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        assertTrue(input.isEnabled());
        driver.quit();
    }
}
