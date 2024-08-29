package com.example.library_management_system.Controllers;

import com.example.library_management_system.Entities.Author;
import com.example.library_management_system.RequestDTOs.AddAuthorRequest;
import com.example.library_management_system.RequestDTOs.GetAuthorResponse;
import com.example.library_management_system.RequestDTOs.UpdateAuthorRequest;
import com.example.library_management_system.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("addAuthor")
    public ResponseEntity addAuthor(@RequestBody AddAuthorRequest addAuthorRequest){
        String response = authorService.addAuthor(addAuthorRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("getAuthor")
    public ResponseEntity getAuthor(@RequestParam int authorId){
        try {
            Author author = authorService.getAuthor(authorId);
            GetAuthorResponse getAuthorResponse = new GetAuthorResponse(author);
            return new ResponseEntity(getAuthorResponse, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updateAuthor")
    public ResponseEntity updateAuthor(@RequestParam int authorId, @RequestBody UpdateAuthorRequest updateAuthorRequest){
        try{
            Author savedAuthor = authorService.updateAuthor(authorId, updateAuthorRequest);
            GetAuthorResponse getAuthorResponse = new GetAuthorResponse(savedAuthor);
            return new ResponseEntity(getAuthorResponse, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("removeAuthor")
        public ResponseEntity removeAuthor(@RequestParam int authorId){
        String response = authorService.removeAuthor(authorId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
