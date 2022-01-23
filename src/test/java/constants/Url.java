package constants;

public enum Url {
    WEB("web/"),
    JSON("json/"),
    PUT("put/"),
    LOG("log/"),
    ATTACHMENT("attachment/"),
    GET("get/"),
    TOKEN("token/"),
    TEST("test/");

    private final String urlComponent;

    Url(String url) {
        this.urlComponent = url;
    }

    public String getUrl() {
        return urlComponent;
    }
}
