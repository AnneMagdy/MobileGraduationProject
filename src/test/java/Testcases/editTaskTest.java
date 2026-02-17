package Testcases;

import BaseClass.BaseClass;
import Pages.EditTask;
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

public class editTaskTest extends BaseClass {
    TaskListPage taskListPage;
    TaskDetails taskDetails;
    EditTask editTask;
    NewTaskPage newTaskPage;

    @Test
    public void EditNewTask() throws Exception {
        taskListPage = new TaskListPage(driver);
        taskDetails = new TaskDetails(driver);
        editTask = new EditTask(driver);
        newTaskPage = new NewTaskPage(driver);

        createTask("Do testing codelab", "finish that codelab!");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement taskItem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(@text,'Do testing codelab')]/..")
                )
        );

        taskItem.click();

        taskListPage.selectExistingTask("testing");
        taskDetails.clickEditIcon();
        editTask.updateTitle("Don't testing codelab");
        editTask.updateDescription("Start that codelab!");
        editTask.clickSaveButton();

        String newTitle = "Don't testing codelab";
        String newDescription = "Start that codelab!";

        // Assertion after editing (open task details then verify)
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By updatedTitle = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + newTitle + "\")");
        wait.until(ExpectedConditions.visibilityOfElementLocated(updatedTitle));

        driver.findElement(updatedTitle).click();

        By updatedDescription = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + newDescription + "\")");
        wait.until(ExpectedConditions.visibilityOfElementLocated(updatedDescription));

        Assert.assertTrue(driver.findElement(updatedTitle).isDisplayed(), "Title not updated!");
        Assert.assertTrue(driver.findElement(updatedDescription).isDisplayed(), "Description not updated!");


    }
}