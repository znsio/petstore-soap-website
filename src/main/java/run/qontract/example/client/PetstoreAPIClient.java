package run.qontract.example.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import run.qontract.petstore.client.AddPetRequest;
import run.qontract.petstore.client.GetPetResponse;

public class PetstoreAPIClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(PetstoreAPIClient.class);
    private final static String baseURL = "http://localhost:8080";

    public int addPet(AddPetRequest addPetRequest) {
        return (int) getWebServiceTemplate()
                .marshalSendAndReceive(baseURL + "/ws", addPetRequest, new SoapActionCallback(""));
    }

    public GetPetResponse getPet(run.qontract.petstore.client.GetPetRequest petId) {
        return (GetPetResponse) getWebServiceTemplate()
                .marshalSendAndReceive(baseURL + "/ws", petId, new SoapActionCallback(""));
    }
}