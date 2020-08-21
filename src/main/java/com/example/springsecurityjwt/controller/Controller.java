package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.models.AuthenticationResponse;
import com.example.springsecurityjwt.models.Book;
import com.example.springsecurityjwt.models.User;
import com.example.springsecurityjwt.services.BooksService;
import com.example.springsecurityjwt.services.MyUserDetailsService;
import com.example.springsecurityjwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BooksService booksService;


    @RequestMapping("/books")
    public List<Book> books(){
        return booksService.extractAllBooks();
    }

    @RequestMapping("/books/{name}")
    public List<Book> hello(@PathVariable String name){
        return booksService.extractBooksByName(name);
    }

    @RequestMapping(value = "/books/", method = RequestMethod.POST)
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        booksService.createBook(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/books/{name}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateUser(@PathVariable("name") String bookName, @RequestBody Book book) {
        booksService.modifyBookByName(bookName, book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/books/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("name") String bookName) {
        booksService.deleteBookByName(bookName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {

        //check if user valid to receive token
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect user name and password");
        }

        final UserDetails userdetails = userDetailsService.loadUserByUsername(user.getName());
        final String token = jwtUtil.generateToken(userdetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

}
