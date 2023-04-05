
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import com.hd.studybuddy.controller.managers.LoginManager;

public class TestLoginManager {
    LoginManager login = new TesterLoginManager();

    public void dbClearTest() {
        login.deleteUser("marcTest");
        login.deleteUser("JeanTest");
    }

    @Test
    @DisplayName("Test Unknown User Login")
    public void testUnknownUserLogin() {
        dbClearTest();
        assertFalse(login.verifyLoginCredentials("marcTest", "admin"));
    }

    @Test
    @DisplayName("Test Wrong Password User Login")
    public void testWrongPasswordUserLogin() {
        dbClearTest();
        login.register("marcTest", "123456789");
        assertFalse(login.verifyLoginCredentials("marcTest", "admin"));
        dbClearTest();

    }

    @Test
    @DisplayName("Test Succesful Login")
    public void testSuccesfulLogin() {
        dbClearTest();
        login.register("marcTest", "123456789");
        assertTrue(login.verifyLoginCredentials("marcTest", "123456789"));
        dbClearTest();
    }

    @Test
    @DisplayName("isValideUser")
    public void testisValideUser() {
        dbClearTest();
        login.register("marcTest", "123456789");
        assertTrue(login.isValidUser("marcTest"));
        assertFalse(login.isValidUser(""));
        dbClearTest();
    }

    @Test
    @DisplayName("isValideNewUser")
    public void testisValideNewUser() {
        dbClearTest();
        login.register("marcTest", "123456789");
        assertFalse(login.isValidNewUser("marcTest"));
        assertTrue(login.isValidNewUser("JeanTest"));
        dbClearTest();
    }

    @Test
    @DisplayName("isValidePassword")
    public void testisValidePassword() {
        assertTrue(login.isValidPassword("1234567"));
        assertFalse(login.isValidPassword("123"));
        dbClearTest();
    }

    @Test
    @DisplayName("register")
    public void testregister() {
        dbClearTest();
        login.register("Jean", "123456789");
        assertTrue(login.verifyLoginCredentials("Jean", "123456789"));
        assertFalse(login.isValidNewUser("Jean"));
        assertTrue(login.isValidUser("Jean"));
        dbClearTest();
    }

    @Test
    @DisplayName("modifyPassword")
    public void testmodifyPassword() {
        dbClearTest();
        login.register("marcTest", "123456789");
        login.modifyPassword("marcTest", "azertyuio");
        assertTrue(login.verifyLoginCredentials("marcTest", "azertyuio"));
        assertFalse(login.verifyLoginCredentials("marcTest", "123456789"));
        dbClearTest();
    }
}
