package models;

import lombok.Data;

@Data
public class JsonResponseModel {
    private int status;
    private String body;

    public JsonResponseModel(int statusCode, String body) {
        this.status = statusCode;
        this.body = body;
    }
}