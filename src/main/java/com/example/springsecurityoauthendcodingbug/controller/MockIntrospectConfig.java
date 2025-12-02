package com.example.springsecurityoauthendcodingbug.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Configuration
public class MockIntrospectConfig {

    private static final String INTROSPECT_RESOURCE = "/introspect";
    private static final ObjectWriter WRITER = new ObjectMapper().writer();

    @Bean(destroyMethod = "shutdown")
    public WireMockServer wireMockServer() throws JsonProcessingException {
        WireMockServer wireMockServer = new WireMockServer(
                new WireMockConfiguration().port(7171)
        );
        wireMockServer.start();
        log.info("WireMockServer started on port: {}", wireMockServer.port());
        WireMock.configureFor("localhost", wireMockServer.port());
        mockIntrospection();
        return wireMockServer;
    }

    private void mockIntrospection() throws JsonProcessingException {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        long issuedAt = now.toEpochSecond(ZoneOffset.UTC);
        long expires = now.plusMinutes(60).toEpochSecond(ZoneOffset.UTC);
        String introspectResponse = WRITER.writeValueAsString(
                new IntrospectResponse(
                        true,
                        "https://www.some-issuer.co.uk",
                        "oauth-token-899aade5-25b2-4a7e-a9da-8e5bee968552",
                        "7526928368",
                        issuedAt,
                        expires,
                        "some-client",
                        WebSecurityConfig.VALID_SCOPE
                )
        );
        WireMock.stubFor(WireMock.post(WireMock.urlMatching(INTROSPECT_RESOURCE))
                .willReturn(
                        WireMock.aResponse()
                                .withBody(introspectResponse)
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withStatus(HttpStatus.OK.value()
                                )
                )
        );
    }
}