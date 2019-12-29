package com.aman.springbootfts.controller;

import com.aman.springbootfts.dal.UserDAL;
import com.aman.springbootfts.entity.User;
import com.aman.springbootfts.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDAL userDAL;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("{firstName}")
    public ResponseEntity getUsersByFirstName(final @PathVariable("firstName") String firstName) throws InterruptedException {
        logger.info("Fetching users for firstName : {}", firstName);
        List<User> users = userDAL.getUserByFirstName(firstName);
        return CollectionUtils.isEmpty(users)? new ResponseEntity("No User Found ... ", HttpStatus.OK) : new ResponseEntity(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveUser(final @RequestBody User user){
        return new ResponseEntity(userRepository.save(user), HttpStatus.CREATED);
    }


}
