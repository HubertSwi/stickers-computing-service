package pl.com.sticker.scs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.sticker.scs.exceptions.SaveStickerException;
import pl.com.sticker.scs.model.User;
import pl.com.sticker.scs.services.UsersService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Api(value = "/scs/users", description = "Provides info about users")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @ApiOperation(value="Saves user")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "User saved", response = User.class),
            @ApiResponse(code = 404, message = "Page was not found"),
            @ApiResponse(code = 500, message = "Internal processing error")
    })
    @PutMapping("/scs/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        System.out.println("Called PUT: /scs/users");

        try {
            usersService.generateDefaultStickers(user);

        } catch (SaveStickerException sse) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

        return ResponseEntity.ok()
                .body(usersService.saveUser(user));
    }

    @ApiOperation(value="Returns user by its UID",
            notes = "NOTES NOTES",
            response = User.class,
            responseContainer = "List")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "User fetched", response = User.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Page was not found"),
            @ApiResponse(code = 500, message = "Internal processing error")
    })
    @GetMapping("/scs/users")
    public ResponseEntity<List<User>> getUserByUid(@RequestParam(name = "uid") String uid) {
        if (StringUtils.isNotBlank(uid)) {
            System.out.println("Called GET: /scs/users?uid=" + uid);

            return ResponseEntity.ok()
                    .body(Stream.of(usersService.findUserByUid(uid)).collect(Collectors.toList()));
        } else {
            System.out.println("Called GET: /scs/users");

            return ResponseEntity.ok()
                .body(usersService.getAllUsers());
        }
    }
}
