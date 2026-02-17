package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditTask {

    AndroidDriver driver;
    WebDriverWait wait;

    public EditTask(AndroidDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By editTitleField = By.xpath("(//android.widget.EditText)[1]");
    By editDescriptionField = By.xpath("(//android.widget.EditText)[2]");
    By saveButton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");

    // Actions
    public void updateTitle(String name) {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(editTitleField));
        title.clear();
        title.sendKeys(name);
    }

    public void updateDescription(String desc) {
        WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(editDescriptionField));
        description.clear();
        description.sendKeys(desc);
    }

    public void clickSaveButton() {
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        save.click();
    }
}
