package org.example.controller;

import org.example.dto.UserDTO;
import org.example.model.Greeting;
import org.example.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    // UC1 + UC2 + UC3 + UC4
    @PostMapping("/save")
    public Greeting saveGreeting(@RequestBody UserDTO userDTO) {
        return greetingService.createGreeting(userDTO);
    }

    // UC5
    @GetMapping("/{id}")
    public Greeting getGreetingById(@PathVariable long id) {
        return greetingService.getGreetingById(id);
    }

    // UC6
    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    // UC7
    @PutMapping("/{id}")
    public Greeting updateGreeting(@PathVariable long id,
                                   @RequestParam String message) {
        return greetingService.updateGreeting(id, message);
    }

    // UC8
    @DeleteMapping("/{id}")
    public String deleteGreeting(@PathVariable long id) {
        return greetingService.deleteGreeting(id);
    }
}
