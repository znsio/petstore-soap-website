package run.qontract.example.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class PetstoreClientConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("run.qontract.petstore.client");
        return marshaller;
    }

    @Bean
    public PetstoreAPIClient petstoreClient(Jaxb2Marshaller marshaller) {
        PetstoreAPIClient client = new PetstoreAPIClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}