import static org.junit.jupiter.api.Assertions.*;

import java.io.NotSerializableException;

import org.junit.jupiter.api.*;

import contracts.*;
import managers.SLL;


/**
 * @author ElMenshawy
 * @version 14-03-2020
 *
 */
public class LinkedListPassFailTests 
{
	/**
	 * References the linked list that is manipulated in each test.
	 */
	private LinkedListADT linkedList;
	
	@BeforeEach
	void init() 
	{
		try{
			// Create object from your implemented linked list here.
			linkedList = new SLL();
		}
		catch(NotSerializableException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Tests inserting node at invalid index.
	 */
	@Test //test-to-fail
	void testInsertNode() 
	{
		linkedList.append("a");
		linkedList.append("b");
		linkedList.append("c");
		linkedList.append("d");
		
		assertThrows( IndexOutOfBoundsException.class, () -> linkedList.insert("e", 4), "Insert a new node at 4th node should throw");
	}

	/**
	 * Tests replacing node at invalid index.
	 */
	@Test //test-to-fail
	void testReplaceNode() 
	{
		linkedList.append("a");
		linkedList.append("b");
		linkedList.append("c");
		linkedList.append("d");
		
		assertThrows( IndexOutOfBoundsException.class, () -> linkedList.replace("e", 4), "Replace data at the 4th node should throw");
	}

	/**
	 * Tests deleting node at invalid index.
	 */
	@Test //test-to-fail
	void testDeleteNode() 
	{
		linkedList.append("a");
		linkedList.append("b");
		linkedList.append("c");
		linkedList.append("d");
		
		assertThrows( IndexOutOfBoundsException.class, () -> linkedList.delete(4), "Delete the 4th node should throw");
	}

	/**
	 * Tests retrieving node at invalid index.
	 */
	@Test //test-to-fail
	void testRetrieveNode() 
	{
		linkedList.append("a");
		linkedList.append("b");
		linkedList.append("c");
		linkedList.append("d");
		
		assertThrows( IndexOutOfBoundsException.class, () -> linkedList.retrieve(4), "Retrieve data at 4th node should throw");
	}
	
	@AfterEach
	void tearDown() 
	{
		linkedList.clear();
	}
}