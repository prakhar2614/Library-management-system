package com.example.library_management_system.RequestDTOs;


import com.example.library_management_system.Entities.LibraryCard;
import com.example.library_management_system.Entities.Transaction;
import com.example.library_management_system.Enum.CardStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetCardResponse {

    public GetCardResponse(LibraryCard card){
        this.cardId = card.getCardId();
        this.cardStatus = card.getCardStatus();
        this.noOfBooksIssued = card.getNoOfBooksIssued();
        if(card.getStudent() != null) {
            this.studentId = card.getStudent().getStudentId();
        }
        for(Transaction t: card.getTransactionList()){
            this.transactionIds.add(t.getTransactionId());
        }
    }

    private int cardId;

    private CardStatus cardStatus;

    private int noOfBooksIssued;

    private int studentId;

    private List<String> transactionIds = new ArrayList<>();
}
