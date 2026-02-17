package Testcases;

import BaseClass.BaseClass;
import Pages.TaskListPage;
import Pages.NewTaskPage;
import Pages.TaskDetails;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class addTaskTest extends BaseClass {
    TaskListPage taskListPage;
    NewTaskPage newTaskPage;
    TaskDetails taskDetails;

    @Test
    public void AddNewTask() throws Exception {
        taskListPage = new TaskListPage(driver);
        newTaskPage = new NewTaskPage(driver);
        taskDetails = new TaskDetails(driver);

        createTask("Do testing codelab", "finish that codelab!");

        // Expected Result Assertion: The new task appears in the main task list with correct title and description

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        String title = "Do testing codelab";
        String description = "finish that codelab!";


        By taskTitle = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + title + "\")");
        wait.until(ExpectedConditions.visibilityOfElementLocated(taskTitle));

        Assert.assertTrue(driver.findElement(taskTitle).isDisplayed(), "❌ Task title not found in main list!");


        driver.findElement(taskTitle).click();


        By taskDescription = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + description + "\")");
        wait.until(ExpectedConditions.visibilityOfElementLocated(taskDescription));

        Assert.assertTrue(driver.findElement(taskDescription).isDisplayed(), "❌ Task description not correct!");


    }
}
