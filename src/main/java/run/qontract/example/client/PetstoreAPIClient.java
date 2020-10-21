package run.qontract.example.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import run.qontract.petstore.client.AddPetRequest;
import run.qontract.petstore.client.AddPetResponse;
import run.qontract.petstore.client.GetPetResponse;
import run.qontract.petstore.client.SearchResponse;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class PetstoreAPIClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(PetstoreAPIClient.class);
    private final static String baseURL = "http://localhost:8080";

    public AddPetResponse addPet(AddPetRequest addPetRequest) {
        return (AddPetResponse) getWebServiceTemplate()
                .marshalSendAndReceive(baseURL + "/ws", addPetRequest, new SoapActionCallback(""));
    }

    public GetPetResponse getPet(run.qontract.petstore.client.GetPetRequest getPetRequest) {
        return (GetPetResponse) getWebServiceTemplate()
                .marshalSendAndReceive(baseURL + "/ws", getPetRequest, new SoapActionCallback(""));
    }

    public SearchResponse search(run.qontract.petstore.client.SearchRequest searchRequest) {
        return (SearchResponse) getWebServiceTemplate()
                .marshalSendAndReceive(baseURL + "/ws", searchRequest, new SoapActionCallback(""));
    }
}