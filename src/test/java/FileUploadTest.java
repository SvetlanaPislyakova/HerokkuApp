import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

/*
- загрузить файл
- проверить, что имя файла на странице совпадает с именем
загруженного файла
 */
public class FileUploadTest {

    @Test
    public void checkFileUpload() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/upload");

        File file = new File("src/test/resources/hello.txt");
        driver.findElement(By.cssSelector("[type=file]")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();
        assertEquals(file.getName(), driver.findElement(By.id("uploaded-files")).getText());
        driver.quit();
    }
}
