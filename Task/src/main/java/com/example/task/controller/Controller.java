package com.example.task.controller;

import com.example.task.entity.User;
import com.example.task.repository.UserRepository;
import com.example.task.service.UserService;
import com.example.task.util.RequestUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")

public class Controller {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @PostMapping()
    @ApiOperation(value = "Add User")
    @ApiResponses(value = {

            @ApiResponse(code = 201, message = "User id"),
            @ApiResponse(code = 400, message = "User not created")
    })
    public ResponseEntity<String> addUser(@RequestBody User user) {
        RequestUtil.delayRequest();
        try {
            userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User id: " + user.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
        }

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Search User By Id")
    @ApiImplicitParam(name = "id", value = "User ID")
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "User parameters"),
            @ApiResponse(code = 404, message = "User is not found")
    })
    public ResponseEntity<String> getUser(@PathVariable("id") long id) {
        RequestUtil.delayRequest();

        try {
            User user = userService.getById(id);
            return ResponseEntity.ok(user.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is not found");
        }


    }

    @PatchMapping("/{id}/{status}")
    @ApiOperation(value = "Update User Status")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "User ID"),
            @ApiImplicitParam(name = "status", value = "User Status")
    })
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "User id, User status before and User status now"),
            @ApiResponse(code = 400, message = "User status not changed")
    })
    public ResponseEntity<String> updateStatus(@PathVariable(name = "id") long id,
                                               @PathVariable(name = "status") String status) {
        RequestUtil.delayRequest();
        try {


            User user = userService.getById(id);
            String statusBefore;
            if (user.getStatus() != null) {
                if (user.getStatus().equals(true)) {
                    statusBefore = "Online";
                } else {
                    statusBefore = "Offline";
                }
            } else {
                statusBefore = "User has no status before";
            }
            Boolean statusNow;
            if (status.equals("Online")) {
                statusNow = true;
            } else if (status.equals("Offline")) {
                statusNow = false;

            } else {
                return ResponseEntity.badRequest().body("User status not changed");
            }
            user.setStatus(statusNow);
            userRepository.save(user);
            return ResponseEntity.ok("User id: " + user.getId() +
                    ", user status before: " + statusBefore + ", user status now: " +
                    status);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User status not changed");
        }

    }

}
