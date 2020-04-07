package com.bau.gitlab.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetPipelineResponse extends GitlabResponse {

  private String sha;
  private String ref;
  private String status;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("updated_at")
  private String updatedAt;

  @JsonProperty("web_url")
  private String webUrl;

  public String getSha() {
    return sha;
  }

  public void setSha(String sha) {
    this.sha = sha;
  }

  public String getRef() {
    return ref;
  }

  public void setRef(String ref) {
    this.ref = ref;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getWebUrl() {
    return webUrl;
  }

  public void setWebUrl(String webUrl) {
    this.webUrl = webUrl;
  }
}
