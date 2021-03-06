package com.application.data.service;

import com.application.data.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceUnitTest {

    @Autowired
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void getByIdGotCalled(){
        UserService userService = new UserService(userRepository);

        userService.get(1);

        Mockito.verify(userRepository).findById(1);
    }

    @Test
    public void getByIdGotCalledAndReturnedPerson(){
        UserService userService = new UserService(userRepository);

        Mockito.when(userService.get(1)).thenReturn(createOptionalTestUser());

        Optional<User> returnUser= userService.get(1);

        if(returnUser.isPresent()){
            Assert.assertEquals("Horst", returnUser.get().getUsername());
            Assert.assertEquals("Horst", returnUser.get().getName());
            Assert.assertEquals("https//fakeUrl.com", returnUser.get().getProfilePictureUrl());
            Assert.assertEquals(46.0, returnUser.get().getHomeLat(), 0);
            Assert.assertEquals(46.0, returnUser.get().getHomeLon(), 0);
        }

        Mockito.verify(userRepository).findById(1);
    }

    @Test
    public void UpdateGotCalled(){
        UserService userService = new UserService(userRepository);

        User testUser = createTestUser();

        userService.update(testUser);

        Mockito.verify(userRepository).save(testUser);
    }

    @Test
    public void UpdateGotCalledAndReturnedUser(){
        UserService userService = new UserService(userRepository);

        User testUser = createTestUser();

        Mockito.when(userService.update(testUser)).thenReturn(testUser);

        User returnUser = userService.update(testUser);

        Assert.assertEquals("J??rg", returnUser.getUsername());
        Assert.assertEquals("J??rg", returnUser.getName());
        Assert.assertEquals("https//fakeUrl.com", returnUser.getProfilePictureUrl());
        Assert.assertEquals(46.0, returnUser.getHomeLat(), 0);
        Assert.assertEquals(46.0, returnUser.getHomeLon(), 0);

        Mockito.verify(userRepository).save(testUser);
    }

    @Test
    public void deletedByIdGotCalled(){
        UserService userService = new UserService(userRepository);

        userService.delete(1);

        Mockito.verify(userRepository).deleteById(1);
    }

    @Test
    public void deletedByIdGotCalledOnlyOnce(){
        UserService userService = new UserService(userRepository);

        userService.delete(1);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1);
    }

    @Test
    public void findAllGotCalled() {
        UserService userService = new UserService(userRepository);

        Pageable pageable = mock(Pageable.class);

        userService.list(pageable);

        Mockito.verify(userRepository).findAll(pageable);
    }

    @Test
    public void countGotCalled(){
        UserService userService = new UserService(userRepository);

        userService.count();

        Mockito.verify(userRepository).count();
    }

    @Test
    public void countGotCalledCorrectly(){
        UserService userService = new UserService(userRepository);

        Mockito.when(((long) userService.count())).thenReturn(1L);

        Assert.assertEquals(1, userService.count());

        Mockito.verify(userRepository, Mockito.times(1)).count();
    }

    private Optional<User> createOptionalTestUser(){
        Optional<User> testUser = Optional.of(new User());

        String username = "Horst";
        String name = "Horst";
        String profilePictureUrl = "https//fakeUrl.com";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        testUser.get().setUsername(username);
        testUser.get().setName(name);
        testUser.get().setProfilePictureUrl(profilePictureUrl);
        testUser.get().setHomeLat(homeLat);
        testUser.get().setHomeLon(homeLon);

        return testUser;
    }

    private User createTestUser(){
        User testUser = new User();

        String username = "J??rg";
        String name = "J??rg";
        String profilePictureUrl = "https//fakeUrl.com";
        Double homeLat = 46.00;
        Double homeLon = 46.00;

        testUser.setUsername(username);
        testUser.setName(name);
        testUser.setProfilePictureUrl(profilePictureUrl);
        testUser.setHomeLat(homeLat);
        testUser.setHomeLon(homeLon);

        return testUser;
    }
}
