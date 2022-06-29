package com.application.data.service;

import com.application.data.entity.User;
import com.github.dockerjava.api.exception.NotFoundException;
import org.junit.BeforeClass;
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

    //private static Optional<User> testUser;
    //private String userName;
    //private int count;

    /*
    //25.06.22 - Can not be implemented due to userRepository Connection neccessary
    //Before creates new Instance for every class
    //Before class has to be static - not possible due to database connection
    //https://stackoverflow.com/questions/20295578/difference-between-before-beforeclass-beforeeach-and-beforeall
    @BeforeClass
    public static void setUp(){
        testUser = Optional.of(new User());

        String userName = "Horst";
        String name = "Horst";
        String profilPictureUrl = "asdf";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        testUser.get().setUsername(userName);
        testUser.get().setName(name);
        testUser.get().setProfilePictureUrl(profilPictureUrl);
        testUser.get().setHomeLat(homeLat);
        testUser.get().setHomeLon(homeLon);

        userRepository.save(testUser.get());
    }

    public void safeUser(User user){
        userRepository.save(user);
    }
    */


    @Test
    public void getById() {
        Optional<User> testUser = Optional.of(new User());

        String username = "Horst";
        String name = "Horst";
        String profilPictureUrl = "asdf";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        testUser.get().setUsername(username);
        testUser.get().setName(name);
        testUser.get().setProfilePictureUrl(profilPictureUrl);
        testUser.get().setHomeLat(homeLat);
        testUser.get().setHomeLon(homeLon);

        userRepository.save(testUser.get());
        Integer repoId = userRepository.findByUsername("Horst").getId();

        if (userService.get(repoId).isPresent()) {
            Assertions.assertEquals(userService.get(repoId).get().getUsername(), "Horst");
        } else {
            throw new NoSuchElementException("User with ID " + repoId + " does not exist");
        }
    }

    @Test
    public void update() {
        Optional<User> testUser = Optional.of(new User());

        String username = "Peter";
        String name = "Peter";
        String profilPictureUrl = "asdf";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        testUser.get().setUsername(username);
        testUser.get().setName(name);
        testUser.get().setProfilePictureUrl(profilPictureUrl);
        testUser.get().setHomeLat(homeLat);
        testUser.get().setHomeLon(homeLon);

        userRepository.save(testUser.get());

        String newName = "Jens";

        testUser.get().setName(newName);

        userService.update(testUser.get());

        Assertions.assertEquals(userRepository.findByUsername(username).getName(), newName);
    }

    @Test
    public void deleteById() {
        Optional<User> testUser = Optional.of(new User());

        String username = "Thorben";
        String name = "Thorben";
        String profilPictureUrl = "asdf";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        testUser.get().setUsername(username);
        testUser.get().setName(name);
        testUser.get().setProfilePictureUrl(profilPictureUrl);
        testUser.get().setHomeLat(homeLat);
        testUser.get().setHomeLon(homeLon);

        userRepository.save(testUser.get());

        Integer repoId = userRepository.findByUsername(username).getId();

        //Test if Skiresort is saved, not necessary but done anyways
        if (userRepository.findById(repoId).isPresent()) {
            System.out.println(userRepository.findById(repoId).get().getUsername() + ": name of the user");
        } else {
            throw new NotFoundException(userRepository.findById(repoId).get().getUsername() + " not found");
        }

        userService.delete(repoId);

        if (userRepository.findById(repoId).isEmpty()) {
            System.out.println("User " + name + " with ID " + repoId + " deleted successfully");
        } else {
            //throw new Exception(skiResortRepository.findById(repoId).get().getName() + " not found");
            throw new NoSuchElementException("User " + name + " and ID " + repoId + " still in database");
        }
    }

    /*
    //25.06.22 - No implmentation found, test neccessary?
    @Test
    public void list(){

    }
     */
}