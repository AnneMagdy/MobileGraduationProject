package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TaskDetails {

    AndroidDriver driver;

    public TaskDetails(AndroidDriver driver) {
        this.driver = driver;
    }

    By selectTaskLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"Do testing codelab\")"
    );

    AppiumBy editIcon = (AppiumBy) AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)");
    AppiumBy deleteIcon = (AppiumBy) AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");

    public void selectTask() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectTaskLocator)).click();
    }
    public EditTask clickEditIcon() {
      driver.findElement(editIcon).click();
        return new EditTask(driver);
    }
    public void DeleteTask() {
        driver.findElement(deleteIcon).click();
    }

}







