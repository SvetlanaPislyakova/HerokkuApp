import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

/*
2. Checkboxes - проверить, что первый чекбокс unchecked, отметить
первый чекбокс, проверить что он checked. Проверить, что второй чекбокс
checked, сделать unheck, проверить, что он unchecked
 */
public class CheckBoxesTest {

    @Test
    public void checkBoxes(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        WebElement checkBox1 = driver.findElements(By.cssSelector("[type=checkbox]")).get(0);
        softAssert.assertFalse(checkBox1.isSelected());
        checkBox1.click();
        softAssert.assertTrue(checkBox1.isSelected());

        WebElement checkBox2 = driver.findElements(By.cssSelector("[type=checkbox]")).get(1);
        softAssert.assertTrue(checkBox2.isSelected());
        checkBox2.click();
        softAssert.assertFalse(checkBox2.isSelected());

        driver.quit();
        softAssert.assertAll();
    }
}
