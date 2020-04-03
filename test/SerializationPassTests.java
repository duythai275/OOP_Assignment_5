import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.*;

import contracts.LinkedListADT;
import managers.SLL;
import problemdomain.User;

/**
 * @author Thai Nguyen, Kim
 * @version 31-03-2020
 */

public class SerializationPassTests {
	/**
	 * References the linked list that is manipulated in each test.
	 */
	private LinkedListADT linkedList;
	String file = "res/users.dat";
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	User user1;
	User user2;
	User user3;
	User user4;
	
	@BeforeEach
	void init()
	{
		try {
			
			user1 = new User(1, "Joe Blow", "jblow@gmail.com", "password");
			user2 = new User(2, "Joe Schmoe", "joe.schmoe@outlook.com", "abcdef");
			user3 = new User(3, "Colonel Sanders", "chickenlover1890@gmail.com", "kfc5555");
			user4 = new User(4, "Ronald McDonald", "burgers4life63@outlook.com", "mcdonalds999");
		
		} catch (NotSerializableException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Tests the serialization process
	 */
	@Test //test-to-pass
	void testSerialization() {
		try {
			linkedList = new SLL();
			
			linkedList.append(user1);
			linkedList.append(user2);
			linkedList.append(user3);
			linkedList.append(user4);
			
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(linkedList);
			oos.flush();
			oos.close();
			
			ois = new ObjectInputStream(new FileInputStream (file));
			assertTrue(ois.readObject() instanceof SLL );
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Tests the deserialization process
	 */
	@Test //test-to-pass
	void testDeserialization () {
		try {
			ois = new ObjectInputStream(new FileInputStream (file));
			
			linkedList = (SLL) ois.readObject();
			
			User user = (User) linkedList.retrieve(0);
			assertTrue(user1.equals(user));
			assertFalse(user.isValidPassword("password"));
			
			user = (User) linkedList.retrieve(1);
			assertTrue(user2.equals(user));
			assertFalse(user.isValidPassword("abcdef"));
			
			user = (User) linkedList.retrieve(2);
			assertTrue(user3.equals(user));
			assertFalse(user.isValidPassword("kfc5555"));
			
			user = (User) linkedList.retrieve(3);
			assertTrue(user4.equals(user));
			assertFalse(user.isValidPassword("mcdonalds999"));
			
			ois.close();
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@AfterEach
	void tearDown()
	{
		linkedList.clear();
	}
}
