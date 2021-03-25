package run.qontract.example.client;

import in.specmatic.LogTail;
import in.specmatic.stub.HttpStub;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static in.specmatic.stub.API.createStub;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = { PetstoreClientApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PetStoreClientEndpointTest {
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

    private final String base = "http://localhost:9090";

    @Test
    public void getPetTest() {
        try {
            ResponseEntity<Pet> response = new RestTemplate().getForEntity(URI.create(base + "/getPet?id=2"), Pet.class);

            assertThat(response.getStatusCode().value()).isEqualTo(200);
            assertThat(response.getBody().getId()).isEqualTo(2);
            assertThat(response.getBody().getName()).isEqualTo("JUDY");
            assertThat(response.getBody().getType()).isEqualTo("DOG");
            assertThat(response.getBody().getStatus()).isEqualTo("AVAILABLE");
        } catch(Throwable e) {
            System.out.println(LogTail.INSTANCE.getString());
            throw e;
        }
    }

    @Test
    public void addPetTest() {
        try {
            PetParams newPet = new PetParams();
            newPet.name = "HANS";
            newPet.type = "dog";
            newPet.status = "available";

            ResponseEntity<Integer> response = new RestTemplate().postForEntity(base + "/addPet", newPet, Integer.class);
            System.out.println(response);
        } catch(Throwable e) {
            System.out.println(LogTail.INSTANCE.getString());
            throw e;
        }
    }

    @Test
    public void searchPet() {
        try {
            PetParams queryDetails = new PetParams();
            queryDetails.name = "";
            queryDetails.type = "dog";
            queryDetails.status = "";

            ResponseEntity<Pet[]> response = new RestTemplate().postForEntity(base + "/search", queryDetails, Pet[].class);

            assertThat(response.getStatusCode().value()).isEqualTo(200);

            assertThat(response.getBody()[0].getId()).isEqualTo(1);
            assertThat(response.getBody()[0].getName()).isEqualTo("Archie");
            assertThat(response.getBody()[0].getType()).isEqualTo("dog");
            assertThat(response.getBody()[0].getStatus()).isEqualTo("available");

            assertThat(response.getBody()[1].getId()).isEqualTo(2);
            assertThat(response.getBody()[1].getName()).isEqualTo("Midge");
            assertThat(response.getBody()[1].getType()).isEqualTo("dog");
            assertThat(response.getBody()[1].getStatus()).isEqualTo("sold");
        } catch(Throwable e) {
            System.out.println(LogTail.INSTANCE.getString());
            throw e;
        }
    }

    @AfterAll
    public static void tearDown() {
        if(service != null)
            service.stop();

        if(stub != null)
            stub.close();
    }
}
