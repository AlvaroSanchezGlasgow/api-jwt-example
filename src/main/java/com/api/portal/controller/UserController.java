package com.api.portal.controller;

import com.api.portal.dto.ErrorDTO;
import com.api.portal.dto.User;
import com.api.portal.service.UserService;
import com.api.portal.util.Utils;
import com.api.portal.util.Validations;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@Api(tags = {"Users"}, value = "Everything to do with users (login, find user details, user creation,...)")
@RequestMapping(value = "/users-management")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value = "/users")
    @ApiOperation(value = "List all the users included in the system", httpMethod = "GET")
    public ResponseEntity<Object> listUser() {
        log.info("listUser");

        List<User> oListUsers = new ArrayList<User>();
        try {
            oListUsers = userService.findAll();
        } catch (Exception ex) {

            log.error("Error querying user data. Cause: " + ex.getMessage());
            return new ResponseEntity<>(new ErrorDTO(new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(oListUsers, HttpStatus.OK);

    }

    @ApiOperation(value = "Get the user details by User ID", httpMethod = "GET")
    @GetMapping(value = "/users/{username}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "username") String username) {
        log.info("getUserById");
        User userDTO = new User();

        try {
            userDTO = userService.fidUserByUsername(username);

        } catch (Exception ex) {
            log.error("Error retrieving user by username. Cause: " + ex.getMessage());
            return new ResponseEntity<>(new ErrorDTO(new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a user once received the details form the User registration screen", httpMethod = "POST", consumes = "Form URL Encoded", produces = "Application/JSON")
    @PostMapping(value = "/users/save",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveUser(@RequestParam Map<String, String> body) throws Exception {


        User user = new User();
        log.info("saveUser");


        try {

            if (Validations.validateEmptyValues(body)){
                log.error("Bad Request. All the fields need to be populated");
                return new ResponseEntity<Object>(new ErrorDTO(new Date(),"Bad Request. All the fields need to be populated", HttpStatus.INTERNAL_SERVER_ERROR.toString()), HttpStatus.INTERNAL_SERVER_ERROR);

            }


            user.setComment(body.get("comment"));
            user.setFirstName(body.get("firstName"));
            user.setLastName(body.get("lastName"));
            user.setEmail(body.get("email"));
            user.setUsername(body.get("username"));
            user.setIsActive(body.get("isActive"));
            user.setPassword(body.get("password"));
            user.setPasswordConfirmation(body.get("confirmPassword"));

        }catch(Exception error){
            log.error(error.getMessage());
            return new ResponseEntity<>(new ErrorDTO(new Date(), error.getMessage(), HttpStatus.BAD_REQUEST.toString()),HttpStatus.BAD_REQUEST);
        }
        try {

            if (body.get("id") == null) {


                if (!user.getPassword().equals(user.getPasswordConfirmation())){
                    log.error("Provided passwords can not be different");
                    return new ResponseEntity<Object>(new ErrorDTO(new Date(), "Provided passwords can not be different", HttpStatus.BAD_REQUEST.toString()),HttpStatus.BAD_REQUEST);

                }else {
                    user.setRegistrationDate(new Date());
                    user.setPassword(passwordEncoder.encode(body.get("password")));

                    userService.save(user);
                }

            } else {
                user.setId(Integer.parseInt(body.get("id")));
                user.setIsActive(body.get("isActive"));

                userService.update(user);

            }


        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<Object>(new ErrorDTO(new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "Delete User", httpMethod = "DELETE")
    @DeleteMapping(value = "/users/{ids}")
    public ResponseEntity<Object>  deleteUser(Authentication authentication, @PathVariable(value = "ids") String ids) {
        log.info("deleteUser");


        List<String> ListUsersToDelete = Utils.splitHelper(ids);
        User userDTO;

        try {
            for (String item : ListUsersToDelete) {
                userDTO = userService.fidUserById(item);

                if (userDTO.getUsername().equals(authentication.getName())) {
                    log.error("Action not allowed. The user "+authentication.getName()+" can not delete himself");
                    return new ResponseEntity<>(new ErrorDTO(new Date(), "Action not allowed. The user "+authentication.getName()+" can not delete himself", HttpStatus.FORBIDDEN.toString()),HttpStatus.FORBIDDEN);

                } else {
                    userService.delete(item);
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new ErrorDTO(new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}