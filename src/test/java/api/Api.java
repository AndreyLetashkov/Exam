package api;

import constants.Parameter;
import constants.Url;
import models.JsonResponseModel;
import utils.ApiUtils;
import utils.FieldUtils;
import utils.ParserUtils;
public class Api {

    public static JsonResponseModel PostGetToken() {
        return ApiUtils.postRequest(String.format(
                "%s%s?%s=%s", Url.TOKEN.getUrl(), Url.GET.getUrl(), Parameter.VARIANT.getParameter(), ParserUtils.parseTestData("version"))
        );
    }

    public static String postGetTestsList(String id){
        String[] value = { id };
        String[] key = { Parameter.PROJECT_ID.getParameter() };
        return ApiUtils.post(String.format(
                "%s%s%s", Url.TEST.getUrl(), Url.GET.getUrl(), Url.JSON.getUrl()),
                FieldUtils.getFields(key, value)).asString().getBody();

    }

    public static String postAddTest(String sid, String project, String test, String method){
        String[] value = {sid, project, test, method, ParserUtils.parsePath("localhost")};
        String[] key = {
                Parameter.SID.getParameter(), Parameter.PROJECT_NAME.getParameter(),
                Parameter.TEST_NAME.getParameter(), Parameter.METHOD_NAME.getParameter(), Parameter.ENV.getParameter()
        };
        return ApiUtils.post(String.format(
                "%s%s", Url.TEST.getUrl(), Url.PUT.getUrl()),
                FieldUtils.getFields(key, value)).asString().getBody();
    }

    public static String postAddLog(String id, String content){
        String[] value = { id, content };
        String[] key = { Parameter.TEST_ID.getParameter(), Parameter.CONTENT.getParameter() };
        return ApiUtils.post(String.format(
                "%s%s%s", Url.TEST.getUrl(), Url.PUT.getUrl(), Url.LOG.getUrl()),
                FieldUtils.getFields(key, value)).asString().getBody();
    }

    public static String postAddAttachment(String id, String content, String format){
        String[] value = { id, content, format };
        String[] key = { Parameter.TEST_ID.getParameter(), Parameter.CONTENT.getParameter(), Parameter.CONTENT_TYPE.getParameter() };
        return ApiUtils.post(String.format("%s%s%s", Url.TEST.getUrl(), Url.PUT.getUrl(), Url.ATTACHMENT.getUrl()),
                FieldUtils.getFields(key, value)).asString().getBody();
    }
}