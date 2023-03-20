package com.wildcodeschool.challengeSpringRest.controller;


import com.wildcodeschool.challengeSpringRest.entity.Book;
import com.wildcodeschool.challengeSpringRest.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> index(){
        return (List<Book>) bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable Long id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }
    @PostMapping("books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("Text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }
    @PutMapping("/books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){
        // getting blog
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("/books/{id}")
    public boolean delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return true;
    }

}
