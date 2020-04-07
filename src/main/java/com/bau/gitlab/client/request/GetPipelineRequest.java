package com.bau.gitlab.client.request;

public class GetPipelineRequest extends GitlabGetRequest {

  private String projectId;

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  @Override
  public String toPath() {
    StringBuilder builder = new StringBuilder();
    builder.append("projects").append('/').append(projectId).append('/').append("pipelines");
    return builder.toString();
  }
}
