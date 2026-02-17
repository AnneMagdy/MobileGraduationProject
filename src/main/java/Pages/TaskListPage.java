package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TaskListPage {
    AndroidDriver driver;
    WebDriverWait wait;

    public TaskListPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locators
    AppiumBy newTaskLocator = (AppiumBy) AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(3)");
    AppiumBy selectTaskLocator = (AppiumBy) AppiumBy.androidUIAutomator("new UiSelector().text(\"Do testing codelab\")");
    By editIconLocator = By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.Button\n");
    AppiumBy deleteTask = (AppiumBy) AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");
    By filterMenuBtn = AppiumBy.accessibilityId("Filter");
    By completedOption = By.xpath("//*[contains(@text,'Completed')]");
    By taskNames = By.xpath("//android.widget.TextView");

    //Actions
    public void clickOnAddTask() {
        driver.findElement(newTaskLocator).click();
    }
    public void selectExistingTask(String taskName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectTaskLocator))
                .click();
    }
    public TaskDetails clickEditTask() {
        driver.findElement(editIconLocator).click();
        return new TaskDetails(driver);
    }
    public TaskDetails clickTheDeleteIcon() {
        driver.findElement(deleteTask).click();
        return new TaskDetails(driver);
    }

    // Complete a task by its name
    public void completeTaskByName(String taskName) {
        WebElement taskCheckbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath("//*[contains(@text,'" + taskName + "')]/../android.widget.CheckBox")
                )
        );
        taskCheckbox.click();
        System.out.println("Task '" + taskName + "' marked as complete successfully!");
    }
    public boolean tapTaskCheckbox(String taskName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By checkboxLocator = By.xpath(
                "//*[contains(@text,'" + taskName + "')]/preceding-sibling::*"
        );

        wait.until(ExpectedConditions.elementToBeClickable(checkboxLocator))
                .click();
        return false;
    }

    public void openFilterMenu() {
        driver.findElement(filterMenuBtn).click();
    }

    public void selectCompletedFilter() {
        driver.findElement(completedOption).click();
    }

    public List<String> getAllDisplayedTaskNames() {

        List<WebElement> tasks = driver.findElements(taskNames);
        List<String> taskList = new ArrayList<>();

        for (WebElement task : tasks) {
            String text = task.getText();
            if (!text.isEmpty()) {
                taskList.add(text);
            }
        }

        return taskList;
    }
}


