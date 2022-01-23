package helper;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.AddProjectPage;
import pages.HomePage;
import pages.ProjectPage;
import utils.DriverUtils;

public class BaseTest {
    protected final HomePage homePage = new HomePage();
    protected final ProjectPage projectPage = new ProjectPage();
    protected final AddProjectPage addProjectPage = new AddProjectPage();

    @BeforeMethod
    protected void beforeMethod() {
        log("Go to site. Pass the necessary authorization.");
        DriverUtils.authorization();
        DriverUtils.maximize();
    }

    @AfterMethod
    public void afterTest() { DriverUtils.quit(); }

    protected static void log(String text) { AqualityServices.getLogger().info(text); }
}