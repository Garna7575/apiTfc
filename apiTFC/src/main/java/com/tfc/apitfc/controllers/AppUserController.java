package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.dto.ForgotPasswordDTO;
import com.tfc.apitfc.domain.dto.PasswordChangeRequestDTO;
import com.tfc.apitfc.domain.dto.UserRequest;
import com.tfc.apitfc.domain.entity.AppUser;
import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.service.AppUserService;
import com.tfc.apitfc.service.NeighborService;
import com.tfc.apitfc.service.NeighborhoodService;
import com.tfc.apitfc.service.PasswordHashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pocket/users")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @Autowired
    PasswordHashService passwordHashService;

    @Autowired
    NeighborhoodService neighborhoodService;

    @Autowired
    NeighborService neighborService;

    @GetMapping
    public ResponseEntity<List<AppUser>> findAll() {
        List<AppUser> users = appUserService.getAllUsers();

        if (!users.isEmpty()){
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/recovery/{email}")
    public ResponseEntity<AppUser> findByEmail(@PathVariable String email) {
        AppUser user = appUserService.getUserByEmail(email);

        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> findByUsername(@PathVariable String username) {
        AppUser user = appUserService.getUserByUsername(username);

        if (user != null){
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{username}/{password}")
    public ResponseEntity<AppUser> findByUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        String passwordHash = passwordHashService.hashPassword(password);
        AppUser user = appUserService.getUserByUsernameAndPassword(username, passwordHash);

        if (user != null){
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Neighbor> createNeighbor(@RequestBody UserRequest request) {
        AppUser newUser = new AppUser();

        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword());
        newUser.setName(request.getName());
        newUser.setSurname(request.getSurname());
        newUser.setEmail(request.getEmail());
        newUser.setBirthDate(request.getBirthDate());
        newUser.setRole("NEIGHBOR");
        newUser.setTlphNumber(request.getTlphNumber());

        AppUser savedUser = appUserService.addUser(newUser);

        Neighbor neighbor = new Neighbor();
        neighbor.setUser(savedUser);
        neighbor.setHouse(request.getHouse());
        neighbor.setNeighborhood(neighborhoodService.getNeighborhoodById(request.getNeighborhoodId()));

        Neighbor savedNeighbor = neighborService.createNeighbor(neighbor);

        return ResponseEntity.ok(savedNeighbor);
    }

    @PostMapping("/change-password/{id}")
    public void changePassword(@RequestBody PasswordChangeRequestDTO request, @PathVariable int id) {
        appUserService.changePassword(id, request.getCurrentPassword(), request.getNewPassword());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordDTO request) throws Exception {
        if (appUserService.forgotPassword(request.getEmail(), request.getId())){
            return ResponseEntity.ok().body("Email enviado con éxito");
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody AppUser newUser, @PathVariable int id) {
        AppUser oldUser = appUserService.getUserById(id);

        oldUser.setId(id);
        oldUser.setUsername(newUser.getUsername());
        oldUser.setName(newUser.getName());
        oldUser.setSurname(newUser.getSurname());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setBirthDate(newUser.getBirthDate());
        oldUser.setTlphNumber(newUser.getTlphNumber());

        appUserService.updateUser(oldUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        AppUser user = appUserService.getUserById(id);

        if (user != null) {
            appUserService.deleteUser(user);
        }
    }
}
