package com.burek.volume.Controllers;

import com.burek.volume.Classes.Exercise;
import com.burek.volume.Classes.Plan;
import com.burek.volume.Repositories.UserRepository;
import com.burek.volume.Requests.ExerciseCompletionRequest;
import com.burek.volume.Requests.LoginRegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity registerController(
            @RequestBody LoginRegisterRequest loginRegisterRequest) {

        System.out.println("register "+loginRegisterRequest.getEmail()+" "+loginRegisterRequest.getPassword());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity loginController(
            @RequestBody LoginRegisterRequest loginRegisterRequest) {

        System.out.println("login "+loginRegisterRequest.getEmail()+" "+loginRegisterRequest.getPassword());

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
