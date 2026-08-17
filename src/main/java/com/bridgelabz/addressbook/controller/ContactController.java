package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.model.Contact;
import com.bridgelabz.addressbook.model.ContactDetails;
import com.bridgelabz.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;


    // UC-01
    // POST /contacts

    @PostMapping
    public Contact save(
            @RequestBody Contact contact) {

        return contactService.save(contact);
    }


    // UC-02
    // GET /contacts/{id}

    @GetMapping("/{id}")
    public Contact getById(
            @PathVariable("id") int id) {

        return contactService.getById(id);
    }


    // UC-03
    // GET /contacts

    @GetMapping
    public List<Contact> getAll() {

        return contactService.getAll();
    }


    // UC-04
    // PUT /contacts/{id}

    @PutMapping("/{id}")
    public Contact update(
            @PathVariable("id") int id,
            @RequestBody Contact contact) {

        return contactService.update(
                id,
                contact
        );
    }


    // UC-05
    // DELETE /contacts/{id}

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable("id") int id) {

        boolean deleted =
                contactService.delete(id);

        if (deleted) {
            return "Contact deleted successfully";
        }

        return "Contact not found";
    }


    // UC-06
    // GET /contacts/search?name=hari

    @GetMapping("/search")
    public List<Contact> search(
            @RequestParam("name") String name) {

        return contactService.search(name);
    }


// UC-11 : View Contact Details
// GET /contacts/{id}/details


    @GetMapping("/{id}/details")
    public ContactDetails getContactDetails(
            @PathVariable("id") int id) {

        return contactService.getContactDetails(id);
    }
}