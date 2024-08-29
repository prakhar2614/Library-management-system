package com.example.library_management_system.RequestDTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociateCardStudentRequest {

    private int studentId;

    private int cardId;
}
