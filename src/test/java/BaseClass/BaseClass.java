package BaseClass;

import Pages.TaskListPage;
import Pages.NewTaskPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.URI;

public class BaseClass {

    protected AndroidDriver driver;
    protected AppiumDriverLocalService server;
    protected TaskListPage taskListPage;
    protected NewTaskPage newTaskPage;

    @BeforeClass
    public void appiumServerSetup() throws Exception {

        server = new AppiumServiceBuilder()
                .withAppiumJS(new File(
                        "C://Users//Admin//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"
                ))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();

        server.start();

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UIAutomator2");
        options.setDeviceName("emulator-5554");

        // APK
        options.setApp(
                "C://Users//Admin//Downloads//architecture-samples-main//architecture-samples-main//" +
                        "app//build//outputs//apk//debug//app-debug.apk"
        );

        options.setCapability("disableHiddenApiPolicy", true);
        options.setCapability("ignoreHiddenApiPolicyError", true);

        driver = new AndroidDriver(
                new URI("http://127.0.0.1:4723/").toURL(),
                options
        );
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (server != null) {
            server.stop();
        }
    }

    public void createTask(String title, String description) {

        TaskListPage taskListPage = new TaskListPage(driver);
        NewTaskPage newTaskPage = new NewTaskPage(driver);

        taskListPage.clickOnAddTask();
        newTaskPage.enterTaskTitle(title);
        newTaskPage.enterTaskDescription(description);
        newTaskPage.clickAddButton();
    }
}