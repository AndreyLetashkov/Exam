package utils;

import api.Api;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.interfaces.IElement;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.TestModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserUtils {
    public static List<TestModel> parseElementsList(List<IElement> list){
        List<TestModel> modelsList = new ArrayList<>();
        int i = 0;
        while (i< list.size()){
            TestModel testmodel = new TestModel();
            testmodel.setName(list.get(i).getText());
            testmodel.setMethod(list.get(++i).getText());
            testmodel.setStatus(list.get(++i).getText());
            testmodel.setStartTime(list.get(++i).getText());
            testmodel.setEndTime(list.get(++i).getText());
            testmodel.setDuration(list.get(++i).getText());
            modelsList.add(testmodel);
            i+=2;
        }
        return modelsList;
    }

    public static TestModel[] parseJsonIntoArray(String projectId){
        ObjectMapper mapper = new ObjectMapper();
        TestModel[] apiModelsOfTests = null;
        try {
            apiModelsOfTests = mapper.readValue(Api.postGetTestsList(projectId), TestModel[].class);
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.toString());
        }
        return apiModelsOfTests;
    }

    public static String parsePath(String text) {
        return new JsonSettingsFile("path.json").getValue(String.format("/%s", text)).toString();
    }

    public static String parseTestData(String text) {
        return new JsonSettingsFile("testData.json").getValue(String.format("/%s", text)).toString();
    }

    public static String parseConfig(String text) {
        return new JsonSettingsFile("config.json").getValue(String.format("/%s", text)).toString();
    }
}