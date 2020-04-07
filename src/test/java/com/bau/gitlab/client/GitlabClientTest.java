package com.bau.gitlab.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bau.gitlab.client.request.GetPipelineRequest;
import com.bau.gitlab.client.response.GetPipelineResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

public class GitlabClientTest {

  @Test
  void getPipelines() throws IOException {
    MockWebServer server = new MockWebServer();

    server.enqueue(
        new MockResponse()
            .setBody(
                "[" //
                    + "  {" //
                    + "    \"id\": 47," //
                    + "    \"status\": \"pending\"," //
                    + "    \"ref\": \"new-pipeline\"," //
                    + "    \"sha\": \"a91957a858320c0e17f3a0eca7cfacbff50ea29a\"," //
                    + "    \"web_url\": \"https://example.com/foo/bar/pipelines/47\"," //
                    + "    \"created_at\": \"2016-08-11T11:28:34.085Z\"," //
                    + "    \"updated_at\": \"2016-08-11T11:32:35.169Z\"" //
                    + "  }," //
                    + "  {" //
                    + "    \"id\": 48," //
                    + "    \"status\": \"pending\"," //
                    + "    \"ref\": \"new-pipeline\"," //
                    + "    \"sha\": \"eb94b618fb5865b26e80fdd8ae531b7a63ad851a\"," //
                    + "    \"web_url\": \"https://example.com/foo/bar/pipelines/48\"," //
                    + "    \"created_at\": \"2016-08-12T10:06:04.561Z\"," //
                    + "    \"updated_at\": \"2016-08-12T10:09:56.223Z\"" //
                    + "  }" //
                    + "]"));
    server.enqueue(new MockResponse().setBody("{}"));
    server.enqueue(new MockResponse().setBody("{}"));
    server.start();

    HttpUrl baseUrl = server.url("/api/v4/");

    GitlabClient client =
        GitlabClientBuilder.newBuilder()
            .withUrl(baseUrl.toString())
            .withPrivateToken("xxxx")
            .build();
    GetPipelineRequest request = new GetPipelineRequest();
    request.setProjectId("1");

    List<GetPipelineResponse> responses =
        client.get(request, new TypeReference<List<GetPipelineResponse>>() {});

    assertEquals(2, responses.size());

    GetPipelineResponse response = responses.get(0);

    assertEquals(47, response.getId());
    assertEquals("pending", response.getStatus());
    assertEquals("new-pipeline", response.getRef());
    assertEquals("a91957a858320c0e17f3a0eca7cfacbff50ea29a", response.getSha());
    assertEquals("https://example.com/foo/bar/pipelines/47", response.getWebUrl());
    assertEquals("2016-08-11T11:28:34.085Z", response.getCreatedAt());
    assertEquals("2016-08-11T11:32:35.169Z", response.getUpdatedAt());

    responses = client.get(request, new TypeReference<List<GetPipelineResponse>>() {});
    assertEquals(new ArrayList<>(), responses);
    responses = client.get("", new TypeReference<List<GetPipelineResponse>>() {});
    assertEquals(new ArrayList<>(), responses);

    server.close();
  }
}
