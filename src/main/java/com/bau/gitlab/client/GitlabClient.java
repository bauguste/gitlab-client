package com.bau.gitlab.client;

import com.bau.gitlab.client.request.GitlabGetRequest;
import com.bau.gitlab.client.response.GitlabResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

public interface GitlabClient {

  <P extends GitlabResponse> P get(String path, Class<P> entityResponseType);

  <P extends GitlabResponse> List<P> get(String path, TypeReference<List<P>> typeReference);

  <R extends GitlabGetRequest, P extends GitlabResponse> P get(
      R request, Class<P> entityResponseType);

  <R extends GitlabGetRequest, P extends GitlabResponse> List<P> get(
      R request, TypeReference<List<P>> typeReference);
}
