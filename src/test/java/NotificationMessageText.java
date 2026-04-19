import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

/*
8. * Notification Messages - кликнуть на кнопку, дождаться появления
нотификации, проверить соответствие текста ожиданиям
 */
public class NotificationMessageText {

    @Test
    public void checkNotificationMessage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

        WebElement button = driver.findElement(By.linkText("Click here"));
        button.click();
        String message = driver.findElement(By.id("flash")).getText();
        softAssert.assertTrue(message.contains("Action unsuccesful, please try again") ||
                message.contains("Action successful"));

        driver.quit();
        softAssert.assertAll();
    }
}
