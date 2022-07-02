package com.application.data.service;

import com.application.data.entity.User;
import com.github.dockerjava.api.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceIntegrationTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    public void getById() {
        Optional<User> testUser = createOptionalUser();

        String username = "Horst";

        testUser.ifPresent(user -> user.setUsername(username));

        testUser.ifPresent(user -> userRepository.save(user));

        Integer repoId = userRepository.findByUsername("Horst").getId();

        if (userService.get(repoId).isPresent()) {
            Assertions.assertEquals(userService.get(repoId).get().getUsername(), "Horst");
        } else {
            throw new NoSuchElementException("User with ID " + repoId + " does not exist");
        }
    }

    @Test
    public void update() {
        Optional<User> testUser = createOptionalUser();

        String username = "Peter";

        testUser.ifPresent(user -> user.setUsername(username));

        testUser.ifPresent(user -> userRepository.save(user));

        String newName = "Jens";

        testUser.ifPresent(user -> user.setName(newName));

        testUser.ifPresent(user -> userService.update(testUser.get()));

        Assertions.assertEquals(userRepository.findByUsername(username).getName(), newName);
    }

    @Test
    public void deleteById() {
        Optional<User> testUser = createOptionalUser();

        String username = "Julia";

        testUser.ifPresent(user -> user.setUsername(username));

        testUser.ifPresent(user -> userRepository.save(user));

        Integer repoId = userRepository.findByUsername(username).getId();

        if (userRepository.findById(repoId).isPresent()) {
            System.out.println(userRepository.findById(repoId).get().getUsername() + ": name of the user");
        } else {
            throw new NotFoundException(userRepository.findById(repoId).get().getUsername() + " not found");
        }

        userService.delete(repoId);

        if (userRepository.findById(repoId).isEmpty()) {
            System.out.println("User " + username + " with ID " + repoId + " deleted successfully");
        } else {
            throw new NoSuchElementException("User " + username + " and ID " + repoId + " still in database");
        }
    }

    private Optional<User> createOptionalUser(){
        Optional<User> testUser = Optional.of(new User());

        String name = "Julia";
        String profilePictureUrl = "https//fakeUrl.com";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        testUser.get().setName(name);
        testUser.get().setProfilePictureUrl(profilePictureUrl);
        testUser.get().setHomeLat(homeLat);
        testUser.get().setHomeLon(homeLon);

        return testUser;
    }
}