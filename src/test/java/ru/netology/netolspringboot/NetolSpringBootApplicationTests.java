package ru.netology.netolspringboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NetolSpringBootApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private final GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeEach
    public void seUp(){
        devApp.start();
        prodApp.start();
    }
    @Test
    void contextLoadsDev() {
        Integer devAppPort = devApp.getMappedPort(8080);
        ResponseEntity<String> forEntityResult = restTemplate.getForEntity("http://localhost:" + devAppPort + "/profile", String.class);
        String expected = "Current profile is dev";
        Assertions.assertEquals(expected, forEntityResult.getBody().toString());
    }
    @Test
    void contextLoadsProd() {
        Integer prodAppPort = prodApp.getMappedPort(8081);
        ResponseEntity<String> forEntityResult = restTemplate.getForEntity("http://localhost:" + prodAppPort + "/profile", String.class);
        String expected = "Current profile is production";
        Assertions.assertEquals(expected, forEntityResult.getBody().toString());
    }

}
