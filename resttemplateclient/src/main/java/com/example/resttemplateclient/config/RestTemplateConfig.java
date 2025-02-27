package com.example.resttemplateclient.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;

@Configuration
public class RestTemplateConfig {

        @Bean
        public RestTemplate restTemplate() throws Exception {
            // Create SSL context that trusts all certificates (for development only)
            SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] chain, String authType) {
                            return true; // Trust all certificates
                        }
                    })
                    .build();

            CloseableHttpClient httpClient = HttpClients.custom()
                    .setConnectionManager(
                        PoolingHttpClientConnectionManagerBuilder.create()
                            .setSSLSocketFactory(
                                SSLConnectionSocketFactoryBuilder.create()
                                    .setSslContext(sslContext)
                                    .setHostnameVerifier((hostname, session) -> true)
                                    .build()
                            )
                            .build()
                    )
                    .build();

            HttpComponentsClientHttpRequestFactory factory =
                    new HttpComponentsClientHttpRequestFactory();
            factory.setHttpClient(httpClient);

            return new RestTemplate(factory);
        }
}