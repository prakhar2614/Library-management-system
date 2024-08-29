package com.example.library_management_system.Services;

import com.example.library_management_system.Entities.Author;
import com.example.library_management_system.Repositories.AuthorRepository;
import com.example.library_management_system.RequestDTOs.AddAuthorRequest;
import com.example.library_management_system.RequestDTOs.UpdateAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    JavaMailSender javaMailSender;


    public String addAuthor(AddAuthorRequest addAuthorRequest) {
        Author author = new Author(addAuthorRequest.getAuthorName(), addAuthorRequest.getAuthorAge(), addAuthorRequest.getEmailid());
        Author a = authorRepository.save(author);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Hi "+ a.getAuthorName());
        message.setFrom("hostyourwebyb@gmail.com");
        message.setTo(a.getEmailid());
        message.setText("You have been successfully added to our portal ! "+ "looking forward for adding more books");

        javaMailSender.send(message);
        return "Author created with author Id - "+a.getAuthorId();
    }

    public Author getAuthor(int authorId) throws Exception{
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if(optionalAuthor.isEmpty()){
            throw new Exception("Invalid author Id");
        }
        Author author = optionalAuthor.get();
        return author;
    }

    public Author updateAuthor(int authorId, UpdateAuthorRequest author) throws Exception{
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if(optionalAuthor.isEmpty()){
            throw new Exception("No author found with this Id.");
        }
        Author savedAuthor = optionalAuthor.get();
        savedAuthor.setAuthorAge(author.getAuthorAge());
        savedAuthor.setAuthorName(author.getAuthorName());
        authorRepository.save(savedAuthor);
        return savedAuthor;
    }

    public String removeAuthor(int authorId){
        authorRepository.deleteById(authorId);
        return "Author with author id -"+authorId+" is deleted";
    }
}
