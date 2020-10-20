package run.qontract.example.client;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ConfigurableApplicationContext;
import run.qontract.petstore.client.GetPetRequest;
import run.qontract.petstore.client.GetPetResponse;
import run.qontract.stub.HttpStub;

import static org.assertj.core.api.Assertions.assertThat;
import static run.qontract.stub.API.createStub;

@SpringBootTest(classes = { PetstoreClientApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PetStoreClientComponentTest {
    private static ConfigurableApplicationContext service;
    private static HttpStub stub;

    @LocalServerPort
    private static int port = 9090;

    @Autowired
    private PetstoreAPIClient client;

    @BeforeAll
    public static void setUp() {
        stub = createStub("localhost", 8080);
    }

    @Test
    public void getPetTest() {
        GetPetRequest request = new GetPetRequest();
        request.setId(2);
        GetPetResponse response = client.getPet(request);
        assertThat(response.getId()).isEqualTo(2);
    }

    @AfterAll
    public static void tearDown() {
        if(service != null)
            service.stop();

        if(stub != null)
            stub.close();
    }
}
