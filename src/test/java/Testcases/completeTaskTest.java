package Testcases;

import BaseClass.BaseClass;
import Pages.TaskListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class completeTaskTest extends BaseClass {

    TaskListPage taskListPage;
    WebDriverWait wait;

    @Test
    public void checkTaskCheckboxTest() {

        taskListPage = new TaskListPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String taskName = "Do testing codelab";

        createTask(taskName, "finish that codelab!");

        taskListPage.tapTaskCheckbox(taskName);

        Assert.assertTrue(isTaskCheckboxChecked(taskName), "Checkbox is NOT checked!");
    }

    public boolean isTaskCheckboxChecked(String taskName) {

        By checkboxLocator = By.xpath(
                "//*[contains(@text,'" + taskName + "')]/preceding-sibling::*"
        );

        WebElement checkbox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(checkboxLocator)
        );

        String isChecked = checkbox.getAttribute("checked");
        String isSelected = checkbox.getAttribute("selected");

        return "true".equalsIgnoreCase(isChecked) || "true".equalsIgnoreCase(isSelected);
    }
}
