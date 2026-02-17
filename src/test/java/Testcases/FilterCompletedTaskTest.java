package Testcases;

import BaseClass.BaseClass;
import Pages.TaskListPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class FilterCompletedTaskTest extends BaseClass {
    TaskListPage taskListPage;
    String task1 = "Task One";
    String task2 = "Task Two";
    String task3 = "Task Three";

    @Test
    public void filterCompletedTask() throws InterruptedException {
        taskListPage = new TaskListPage(driver);

        // Step 1: Create tasks
        createTask(task1, "Description 1");
        Thread.sleep(3000);
        createTask(task2, "Description 2");
        Thread.sleep(3000);
        createTask(task3, "Description 3");
        Thread.sleep(3000);

        // Step 2: Mark a few tasks as completed
        taskListPage.tapTaskCheckbox(task1);
        Thread.sleep(1000);
        taskListPage.tapTaskCheckbox(task2);
        Thread.sleep(1000);

        // Step 3: Click on filter menu → Select “Completed”
        taskListPage.openFilterMenu();
        Thread.sleep(1000);
        taskListPage.selectCompletedFilter();
        Thread.sleep(1000);

        // Step 4: Expected Result: Only completed tasks appear
        List<String> displayedTasks = taskListPage.getAllDisplayedTaskNames();

        Assert.assertTrue(displayedTasks.contains(task1), "Task One should appear in Completed filter!");
        Assert.assertTrue(displayedTasks.contains(task2), "Task Two should appear in Completed filter!");
        Assert.assertFalse(displayedTasks.contains(task3), "Task Three should NOT appear in Completed filter!");
    }
}



