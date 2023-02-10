import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class JunitClass2 {
    WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void selectDate() {
        driver.get("https://demoqa.com/date-picker");
        WebElement calendar = driver.findElement(By.id("datePickerMonthYearInput"));
        calendar.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        calendar.sendKeys("03/08/2023");
        calendar.sendKeys(Keys.ENTER);
    }

    @Test
    public void selectDropdownClassic() {
        driver.get("https://demoqa.com/select-menu");
        Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
        select.selectByValue("3");
        select.selectByIndex(5);
        select.selectByVisibleText("Green");
    }

    @Test
    public void selectDropDownAjax() throws InterruptedException {
        driver.get("https://demoqa.com/select-menu");
        driver.findElements(By.className("css-yk16xz-control")).get(2).click();
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ARROW_DOWN).sendKeys(Keys.ENTER, Keys.ENTER, Keys.ENTER).perform();

    }

    @Test
    public void capsLetter() {
        driver.get("https://www.google.com/");
        WebElement searchElement = driver.findElement(By.name("q"));
        Actions action = new Actions(driver);
        action.moveToElement(searchElement)
                .keyDown(Keys.SHIFT)
                .sendKeys("Selenium Webdriver")
                .keyUp(Keys.SHIFT)
                .perform();
    }

    @Test
    public void takeScreenShot() throws IOException {
        driver.get("https://demoqa.com");
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String time = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        String fileWithPath = "./src/test/resources/screenshots/" + time + ".png";
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(screenshotFile, DestFile);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
