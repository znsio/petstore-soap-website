package run.qontract.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import run.qontract.petstore.client.GetPetRequest;
import run.qontract.petstore.client.GetPetResponse;

@Controller
public class PetStoreClientComponent {
    @Autowired
    PetstoreAPIClient petstoreAPIClient;

    @GetMapping("/getPet")
    @ResponseBody
    public Pet getPet(@RequestParam(name="id", required=true) int id) {
        GetPetRequest petId = new GetPetRequest();
        petId.setId(id);
        GetPetResponse pet = petstoreAPIClient.getPet(petId);

        return new Pet(pet.getId(), pet.getName().toUpperCase(), pet.getType().toUpperCase(), pet.getStatus().toUpperCase());
    }

}