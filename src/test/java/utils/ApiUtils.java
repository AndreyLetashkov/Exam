package utils;

import kong.unirest.HttpResponse;
import models.JsonResponseModel;
import kong.unirest.*;
import java.util.Map;

public class ApiUtils {
    static { Unirest.config().defaultBaseUrl(String.format("%s%s", ParserUtils.parsePath("localhost"), ParserUtils.parsePath("api"))); }

    public static JsonResponseModel postRequest(String request) {
        HttpResponse<String> response = Unirest.post(request).asString();
        Unirest.shutDown();
        return new JsonResponseModel(response.getStatus(),response.getBody());
    }

    public static MultipartBody post(String url, Map<String, Object> values){ return Unirest.post(url).fields(values); }
}