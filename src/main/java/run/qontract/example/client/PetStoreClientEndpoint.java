package run.qontract.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import run.qontract.petstore.client.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PetStoreClientEndpoint {
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

    @PostMapping("/addPet")
    @ResponseBody
    public Integer addPet(@RequestBody PetParams petParams) {
        AddPetRequest addPetRequest = new AddPetRequest();

        addPetRequest.setType(petParams.type);
        addPetRequest.setStatus(petParams.status);
        addPetRequest.setName(petParams.name);

        AddPetResponse pet = petstoreAPIClient.addPet(addPetRequest);

        return pet.getId();
    }

    @PostMapping("/search")
    @ResponseBody
    public List<Pet> search(@RequestBody PetParams petParams) {
        SearchRequest searchRequest = new SearchRequest();

        searchRequest.setType(petParams.type);
        searchRequest.setStatus(petParams.status);
        searchRequest.setName(petParams.name);

        SearchResponse petResponse = petstoreAPIClient.search(searchRequest);

        ArrayList<Pet> pets = new ArrayList<>();

        for(int i = 0; i < petResponse.getPet().size(); i ++) {
            int id = petResponse.getPet().get(i).getId();
            String name = petResponse.getPet().get(i).getName();
            String type = petResponse.getPet().get(i).getType();
            String status = petResponse.getPet().get(i).getStatus();

            Pet newPet = new Pet(id, name, type, status);

            pets.add(newPet);
        }

        return pets;
    }
}