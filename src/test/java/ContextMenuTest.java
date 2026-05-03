import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

/*
- правый клик по элементу
- валидация текста на алерте
- закрытие алерта
 */
public class ContextMenuTest {

    @Test
    public void checkContextMenu() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/context_menu");
        Actions actions = new Actions(driver);
        actions
                .contextClick(driver.findElement(By.id("hot-spot")))
                .build()
                .perform();
        Alert alert = driver.switchTo().alert();
        assertEquals(alert.getText(), "You selected a context menu");
        alert.accept();
        wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        driver.quit();
    }
}
