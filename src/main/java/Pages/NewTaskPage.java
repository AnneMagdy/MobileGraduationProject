package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class NewTaskPage {

    AndroidDriver driver;

    public NewTaskPage(AndroidDriver driver) {
        this.driver = driver;
    }

    // Locators
    By taskTitleField = By.xpath("//android.widget.ScrollView/android.widget.EditText[1]");
    By taskDescriptionField = By.xpath("//android.widget.ScrollView/android.widget.EditText[2]");
    AppiumBy addButton = (AppiumBy) AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");

    // Actions
    public void enterTaskTitle(String name) {
        driver.findElement(taskTitleField).sendKeys(name);
    }

    public void enterTaskDescription(String description) {
        driver.findElement(taskDescriptionField).sendKeys(description);
    }

    public void clickAddButton() {
        driver.findElement(addButton).click();
    }
}
