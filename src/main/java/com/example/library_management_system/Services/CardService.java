package com.example.library_management_system.Services;

import com.example.library_management_system.Entities.LibraryCard;
import com.example.library_management_system.Entities.Student;
import com.example.library_management_system.Enum.CardStatus;
import com.example.library_management_system.Repositories.LibraryCardRepository;
import com.example.library_management_system.Repositories.StudentRepository;
import com.example.library_management_system.RequestDTOs.AssociateCardStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private LibraryCardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;
    public String getFreshCard(){
        LibraryCard newCard= new LibraryCard();
        newCard.setCardStatus(CardStatus.NEW);
        newCard.setNoOfBooksIssued(0);
        LibraryCard savedCard = cardRepository.save(newCard);

        return "New card with card no." + savedCard.getCardId()+" has been generated";
    }

    public LibraryCard findCardById(int cardId) throws Exception{
        Optional<LibraryCard> optionalCard = cardRepository.findById(cardId);
        if(optionalCard.isEmpty()){
            throw new Exception("Incorrect Card number");
        }
        LibraryCard card = optionalCard.get();
        return card;
    }

    public String associateCardAndStudent(AssociateCardStudentRequest associateCardStudentRequest){
        Integer cardId = associateCardStudentRequest.getCardId();
        Integer studentId = associateCardStudentRequest.getStudentId();
        Optional<LibraryCard> optionalCard = cardRepository.findById(cardId);
        Optional<Student> optionalStudent =  studentRepository.findById(studentId);
        if(optionalCard.isEmpty()){
            return "No library card found with this id";
        }
        if(optionalStudent.isEmpty()){
            return "No student found with this id";
        }
        LibraryCard card= optionalCard.get();
        Student student = optionalStudent.get();

        card.setCardStatus(CardStatus.ACTIVE);
        card.setStudent(student);
        card.setNoOfBooksIssued(0);

        cardRepository.save(card);
        return "Student with student ID "+ student.getStudentId()+" is allotted card with card id:"+card.getCardId();
    }
}
