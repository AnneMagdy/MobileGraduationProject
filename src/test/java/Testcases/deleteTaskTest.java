package Testcases;

import BaseClass.BaseClass;
import Pages.TaskListPage;
import Pages.NewTaskPage;
import Pages.TaskDetails;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class deleteTaskTest extends BaseClass {
    TaskListPage taskListPage;
    TaskDetails taskDetails;
    NewTaskPage newTaskPage;
    @Test
    public void DeleteTask() throws InterruptedException {

        String taskTitle = "Do testing codelab";
        taskListPage = new TaskListPage(driver);
        taskDetails = new TaskDetails(driver);
        newTaskPage = new NewTaskPage(driver);

        createTask("Do testing codelab", "finish that codelab!");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement taskItem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(@text,'Do testing codelab')]/..")
                )
        );

        taskItem.click();
        taskDetails.DeleteTask();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By taskLocator = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + taskTitle + "\")");
        boolean isDeleted;
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(taskLocator));
            isDeleted = true;
        } catch (Exception e) {
            isDeleted = false;
        }

        // Assertion: Expected Result
        Assert.assertTrue(isDeleted, "The selected task is removed from the task list.");
    }
}


