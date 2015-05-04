package jgluna.potlach.ws;

import jgluna.potlach.model.Gender;
import jgluna.potlach.model.User;
import jgluna.potlach.repository.RepositoryInterface;
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

        RepositoryInterface repo = mock(RepositoryInterface.class);
        when(repo.saveUser(newUser)).thenReturn(newUser);
        when(repo.findUserByEmail("some email")).thenReturn(newUser);
        when(repo.updateUser(newUser)).thenReturn(newUser);

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
        boolean response = controller.updateUser(newUser);
        assertTrue(response);
    }

    @Test
    public void authenticateUser() {

    }

}