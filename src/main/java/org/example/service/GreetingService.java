package org.example.service;

import org.example.dao.GreetingDAO;
import org.example.dto.UserDTO;
import org.example.exception.IdNotFoundException;
import org.example.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GreetingService {

    @Autowired
    private GreetingDAO greetingDAO;

    // UC1 + UC2 + UC3 + UC4
    public Greeting createGreeting(UserDTO userDTO) {

        String firstName = userDTO.getFirstName();
        String lastName = userDTO.getLastName();

        String message;

        if ((firstName == null || firstName.trim().isEmpty()) &&
                (lastName == null || lastName.trim().isEmpty())) {

            message = "Hello World";

        } else if (firstName != null && !firstName.trim().isEmpty() &&
                lastName != null && !lastName.trim().isEmpty()) {

            message = "Hello " + firstName + " " + lastName;

        } else if (firstName != null && !firstName.trim().isEmpty()) {

            message = "Hello " + firstName;

        } else {

            message = "Hello " + lastName;
        }

        Greeting greeting = new Greeting(message);
        return greetingDAO.save(greeting);
    }

    // UC5
    public Greeting getGreetingById(long id) {
        Greeting greeting = greetingDAO.findById(id);

        if (greeting == null) {
            throw new IdNotFoundException("Greeting not found with id: " + id);
        }

        return greeting;
    }

    // UC6
    public List<Greeting> getAllGreetings() {
        return greetingDAO.findAll();
    }

    // UC7
    public Greeting updateGreeting(long id, String message) {
        Greeting greeting = getGreetingById(id);
        greeting.setMessage(message);
        return greetingDAO.update(greeting);
    }

    // UC8
    public String deleteGreeting(long id) {
        Greeting greeting = getGreetingById(id);
        greetingDAO.delete(greeting);
        return "Greeting deleted successfully with id: " + id;
    }
}
