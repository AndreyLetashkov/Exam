package constants;

public enum Parameter {
    SID("SID"),
    PROJECT_NAME("projectName"),
    TEST_NAME("testName"),
    METHOD_NAME("methodName"),
    ENV("env"),
    TOKEN("token"),
    VARIANT("variant"),
    CONTENT_TYPE("contentType"),
    PROJECT_ID("projectId"),
    TEST_ID("testId"),
    CONTENT("content");

    private final String parameter;

    Parameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}