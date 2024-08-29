package com.example.library_management_system.Controllers;

import com.example.library_management_system.Entities.LibraryCard;
import com.example.library_management_system.RequestDTOs.AssociateCardStudentRequest;
import com.example.library_management_system.RequestDTOs.GetCardResponse;
import com.example.library_management_system.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("/generateACard")
    public String addCard(){
        String result = cardService.getFreshCard();
        return result;
    }

    @GetMapping("findById")
    public ResponseEntity findCardById(@RequestParam int cardId ){
        try {
            LibraryCard card = cardService.findCardById(cardId);
            GetCardResponse getCardResponse = new GetCardResponse(card);
            return new ResponseEntity(getCardResponse, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("associateCardAndStudent")
    public ResponseEntity associateCardAndStudent(@RequestBody AssociateCardStudentRequest associateCardStudentRequest){
        System.out.println("{{{{{{{{{{{{{{{{{{{{{{{}}}}}}}}}}}}}}}}}}}}}");
        try{
            String s =cardService.associateCardAndStudent(associateCardStudentRequest);
            return new ResponseEntity(s, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
