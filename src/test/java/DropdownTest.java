import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

/*
3. Dropdown - Взять все элементы дроп-дауна и проверить их наличие.
Выбрать первый, проверить, что он выбран, выбрать второй, проверить, что
он выбран
 */
public class DropdownTest {

    @Test
    public void checkDropdown() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dropdown");

        Select select = new Select(driver.findElement(By.id("dropdown")));
        List<WebElement> ddOptions = select.getOptions();
        Set<String> expectedItems = Set.of("Please select an option", "Option 1", "Option 2");
        softAssert.assertEquals(ddOptions.size(), expectedItems.size());
        for(WebElement option : ddOptions) {
            softAssert.assertTrue(expectedItems.contains(option.getText()));
        }
        select.selectByValue("1");
        softAssert.assertEquals(select.getFirstSelectedOption().getAttribute("value"), "1");
        select.selectByValue("2");
        softAssert.assertEquals(select.getFirstSelectedOption().getAttribute("value"), "2");
        driver.quit();
        softAssert.assertAll();
    }
}
