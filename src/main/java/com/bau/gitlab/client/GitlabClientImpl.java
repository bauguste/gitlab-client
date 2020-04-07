package com.bau.gitlab.client;

import com.bau.gitlab.client.request.GitlabGetRequest;
import com.bau.gitlab.client.response.GitlabResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GitlabClientImpl implements GitlabClient {

  private static final Logger LOGGER = LogManager.getLogger(GitlabClientImpl.class);

  private String url;
  private String privateToken;
  private ObjectMapper mapper;

  protected GitlabClientImpl(String url, String privateToken) {
    this.url = url;
    this.privateToken = privateToken;
    mapper = new ObjectMapper();
  }

  @Override
  public <P extends GitlabResponse> P get(String path, Class<P> entityResponseType) {
    try {
      return mapper.readValue(get(path), entityResponseType);
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  @Override
  public <P extends GitlabResponse> List<P> get(String path, TypeReference<List<P>> typeReference) {
    try {
      return mapper.readValue(get(path), typeReference);
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return new ArrayList<>();
  }

  @Override
  public <R extends GitlabGetRequest, P extends GitlabResponse> P get(
      R request, Class<P> entityResponseType) {
    try {
      return mapper.readValue(get(request.toPath()), entityResponseType);
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }

  @Override
  public <R extends GitlabGetRequest, P extends GitlabResponse> List<P> get(
      R request, TypeReference<List<P>> typeReference) {
    try {
      return mapper.readValue(get(request.toPath()), typeReference);
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return new ArrayList<>();
  }

  private String get(String path) {
    OkHttpClient client = new OkHttpClient();
    HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();

    HttpUrl httpUrl = urlBuilder.addPathSegments(path).build();

    Request request =
        new Request.Builder().url(httpUrl).addHeader("PRIVATE-TOKEN", privateToken).build();

    try (Response response = client.newCall(request).execute()) {
      LOGGER.debug(httpUrl);
      String body = response.body().string();
      response.body().close();
      return body;
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
    }

    return null;
  }
}
