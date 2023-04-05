import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import com.hd.studybuddy.model.Database;

import static org.junit.jupiter.api.Assertions.*;

public class TestDatabase{
	Database db = new TesterDatabase();
	@Test
	@DisplayName("Test add/get User")
	public void testAddUser(){
		db.createTables();
		System.out.println("Test add/get User");
		db.addUser("John", "123");
		String[] userTest1 = db.getUser("John");
		String name = userTest1[0];
		String passwd = userTest1[1];
		assertEquals("John", name);
		assertEquals("123", passwd);
		db.deleteUser("John");
		String[] userTest2 = db.getUser("John");
		assertNull(userTest2[0]);
		assertNull(userTest2[1]);
	}
	@Test
	@DisplayName("Test modify Password")
	public void testmodifyPassword(){
		db.createTables();	
		db.addUser("John", "123");
		db.modifyPassword("John", "456");
		String passwd = db.getUser("John")[1];
		assertEquals("456", passwd);
		assertNotEquals("123", passwd);
		db.deleteUser("John");

	}
}
