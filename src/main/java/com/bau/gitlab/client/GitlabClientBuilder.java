package com.bau.gitlab.client;

public class GitlabClientBuilder {

  private String url;
  private String privateToken;

  protected GitlabClientBuilder() {}

  public static GitlabClientBuilder newBuilder() {
    return new GitlabClientBuilder();
  }

  public GitlabClient build() {
    return new GitlabClientImpl(url, privateToken);
  }

  public GitlabClientBuilder withUrl(String url) {
    this.url = url;
    return this;
  }

  public GitlabClientBuilder withPrivateToken(String privateToken) {
    this.privateToken = privateToken;
    return this;
  }
}
