package test;

import api.Api;
import constants.Parameter;
import helper.BaseTest;
import models.JsonResponseModel;
import models.TestModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DriverUtils;
import utils.ParserUtils;
import utils.RandomUtils;
import utils.SortUtils;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;

public class ExamTest extends BaseTest {

    @Test()
    void test() {
        log("Get a token.");
        JsonResponseModel token = Api.PostGetToken();
        log("Check token.");
        Assert.assertEquals(token.getStatus(),  HttpURLConnection.HTTP_OK, "Token didn't generated.");
        Assert.assertNotNull(token.getBody(),"Token didn't generated.");

        log("Check if home page is opened");
        Assert.assertTrue(homePage.isPageOpened(),"The home page didn't opened.");
        log("Pass the generated token.");
        DriverUtils.addCookie(Parameter.TOKEN.getParameter(), token.getBody());
        log("Refresh the page.");
        DriverUtils.refresh();
        log("Compare versions.");
        Assert.assertEquals(homePage.getVersion(), ParserUtils.parseTestData("version"),"Invalid version.");

        log("project id is received.");
        String projectId = homePage.getProjectId(homePage.getAttribute("href"));
        log("Navigate project page.");
        homePage.navigateProjectPage(ParserUtils.parseTestData("project"));
        projectPage.waitingForThePageToLoad();
        log("Check sorting times and tests.");
        List<TestModel> testsModelList = ParserUtils.parseElementsList(projectPage.getTestsList());
        Assert.assertTrue(SortUtils.isTimeSorted(testsModelList),"Time didn't sorted.");
        Assert.assertTrue(SortUtils.isJsonResponseSorted(
                ParserUtils.parseJsonIntoArray(projectId),
                testsModelList,
                testsModelList.size()),
                "Tests didn't sorted."
        );

        log("Return to previous page.");
        DriverUtils.back();
        log("Project is added.");
        homePage.addProject();
        addProjectPage.switchToActiveTab();
        String newProject = RandomUtils.setRandomText();
        addProjectPage.enterProjectName(newProject);
        log("Project is Saved.");
        addProjectPage.clickSaveBtn();
        log("Check if message is added.");
        Assert.assertTrue(addProjectPage.isAddedProject(),"Successful save message didn't appear.");
        log("ClosePopUp.");
        DriverUtils.closePopUp();
        Assert.assertEquals(1, DriverUtils.getNumbersWindows(),"Window didn't close.");
        log("Refresh.");
        DriverUtils.refresh();
        log("Check if project is added.");
        Assert.assertTrue(homePage.isProjectAdded(newProject),"The project is not added to the list of projects");

        log("Navigate project page.");
        homePage.navigateProjectPage(newProject);
        String testName = RandomUtils.setRandomText();
        log("Add test.");
        String testId = Api.postAddTest(String.valueOf(new Date()), newProject, testName, RandomUtils.setRandomText());
        Assert.assertNotNull(testId,"Test didn't added.");
        log("Add log.");
        String logId = Api.postAddLog(testId, RandomUtils.setRandomText());
        Assert.assertNotNull(logId,"Log didn't added.");
        log("Add attachment.");
        String attachmentId =  Api.postAddAttachment(testId, DriverUtils.getScreenshot(), ParserUtils.parseTestData("format"));
        Assert.assertNotNull(attachmentId,"Attachment didn't added.");
        projectPage.waitingForTheTestToAppear();
        log("Check if test is added.");
        Assert.assertTrue(projectPage.isTestAdded(testName),"The test didn't added.");
    }
}