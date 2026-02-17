package Testcases;

import BaseClass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class AppBehaviorAfterScreenRotationTest extends BaseClass {

    @Test
    public void VerifyAppBehaviorAfterScreenRotation() throws Exception {

        createTask("Do testing codelab", "finish that codelab!");

        driver.rotate(ScreenOrientation.LANDSCAPE);
        Thread.sleep(3000);
        driver.rotate(ScreenOrientation.PORTRAIT);

        // Verify task still exists (Not Lost)
        List<WebElement> taskList = driver.findElements(
                By.xpath("//*[contains(@text,'Do testing codelab')]")
        );

        Assert.assertTrue(taskList.size() > 0, "Task is LOST after rotation!");
        Assert.assertEquals(taskList.size(), 1, "Task is DUPLICATED after rotation!");

    }
}
