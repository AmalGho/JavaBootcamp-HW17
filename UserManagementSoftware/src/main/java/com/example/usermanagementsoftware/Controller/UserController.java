package com.example.usermanagementsoftware.Controller;

import com.example.usermanagementsoftware.ApiResponse.ApiResponse;
import com.example.usermanagementsoftware.Model.User;
import com.example.usermanagementsoftware.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        Boolean isUpdate = userService.updateUser(id,user);
        if (isUpdate) return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user updated"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("something went wrong"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        Boolean isDeleted = userService.deleteUser(id);

        if (isDeleted) return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user deleted"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("something went wrong"));
    }
}
