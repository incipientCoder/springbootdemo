package com.example.user;

import java.net.URI;
import java.util.List;

import com.example.user.User;
import com.example.user.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource
{
    @Autowired
    private UserDaoService service;
    @RequestMapping(method =RequestMethod.GET,path="/users")
    public List<User> retriveAllUsers()
    {
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveUser(@PathVariable Integer id){
        User user = service.findOne(id);
        if(user == null)
            throw new UserNotFoundException(id.toString());
        return user;
    }

    @ExceptionHandler

//    @PostMapping(path = "/users")
//    public void createUser(@RequestBody User user){
//        User savedUser=service.save(user);
//    }

    //method that posts a new user detail and returns the status of the user resource
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user)
    {
        User savedUser=service.save(user);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        User user= service.deleteById(id);
        if(user==null)
//runtime exception
            throw new UserNotFoundException("id: "+ id);
    }

}