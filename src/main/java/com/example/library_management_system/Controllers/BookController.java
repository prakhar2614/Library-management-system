package com.example.library_management_system.Controllers;


import com.example.library_management_system.Entities.Book;
import com.example.library_management_system.RequestDTOs.AddBookRequest;
import com.example.library_management_system.RequestDTOs.GetBookResponse;
import com.example.library_management_system.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody AddBookRequest addBookRequest){
        try{
            String result = bookService.addBook(addBookRequest);
            return  new ResponseEntity(result, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("changeName/{bookName}")
    public ResponseEntity updateBookName(@PathVariable("bookName") String bookName, @RequestParam int bookId){
        try{
            String response =bookService.updateBookName(bookId,bookName);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("removeBook")
    public ResponseEntity removeBook(@RequestParam int bookId){
        String response = bookService.removeBook(bookId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/getBook/{bookId}")
    public ResponseEntity getBook(@PathVariable int bookId){
        try{
            Book book = bookService.getBook(bookId);
            GetBookResponse getBookResponse = new GetBookResponse(book);
            return new ResponseEntity(getBookResponse, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
