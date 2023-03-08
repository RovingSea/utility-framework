package io.github.utilityframework.boot.example.web.response;

import io.github.utilityframework.boot.share.response.ResponseHeader;

/**
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
@ResponseHeader
public class ExampleResponseHeader {

    private String project;

    public ExampleResponseHeader() {
    }

    public ExampleResponseHeader(String project) {
        this.project = project;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
