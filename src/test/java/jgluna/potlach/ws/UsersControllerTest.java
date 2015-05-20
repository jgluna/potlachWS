package jgluna.potlach.ws;

import jgluna.potlach.model.Gender;
import jgluna.potlach.model.User;
import jgluna.potlach.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersControllerTest {

    private static UsersController controller;
    private static User newUser;

    @BeforeClass
    public static void setUp() {

        newUser = new User();
        newUser.setCountry("country");
        newUser.setEmail("some email");
        newUser.setGender(Gender.MALE);
        newUser.setMemberSince(new Date());
        newUser.setName("user");
        newUser.setPassword("123");

        UserRepository repo = mock(UserRepository.class);
        when(repo.save(newUser)).thenReturn(newUser);
        when(repo.findOne("some email")).thenReturn(newUser);

        controller = new UsersController(repo);
    }

    @Test
    public void createUser() {
        User savedUser = controller.addUser(newUser);
        assertNotNull(savedUser);
    }

    @Test
    public void getUser() {
        User responseUSer = controller.getUser("some email");
        assertNotNull(responseUSer);
        assertThat(responseUSer, Matchers.<User>hasProperty("email", is("some email")));
    }

    @Test
    public void updateUser() {
        newUser.setPassword("456");
        User response = controller.updateUser(newUser);
        assertEquals(response.getPassword(), "456");
    }

    @Test
    public void authenticateUser() {

    }

}