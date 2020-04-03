package managers;

import contracts.*;
import problemdomain.*;

import java.io.*;

/**
 * @author Thai Nguyen, Kim Seulgi
 * @version 31-03-2020
 */
public class SLL implements LinkedListADT, Serializable
{
	/**
	 * The first node in the linked list.
	 */
	private Node head;
	
	/**
	 * The number of nodes in the linked list.
	 */
	private int size;
	
	/**
	 * constructor
	 * set empty list when initialized
	 * @throws NotSerializableException is throw when unserializable
	 */
	public SLL() throws NotSerializableException
	{
		this.head = null;
		this.size = 0;
	}
	
	/**
	 * Prepends (adds to beginning) data to the list.
	 * @param data Data to store as a first element.
	 */
	public void prepend(Object data) 
	{
		// Create a new node object to prepend.
		Node newNode = new Node (data);
		
		// Set next of new node to head
		// This must be done before we change the head.
		newNode.setNext(head);
		
		// Set head to new node
		head = newNode;
		
		// Increment the size
		size++;
	}

	@Override
	/**
	 * Checks if the list is empty.
	 * @return True if it is empty.
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return ( this.head == null ) ? true : false;
	}

	@Override
	/**
	 * Clears the list.
	 */
	public void clear() {
		// TODO Auto-generated method stub
		this.head = null;
		this.size = 0;
	}

	@Override
	/**
	 * Adds to the end of the list.
	 * @param data Data to append.
	 */
	public void append(Object data) {
		// TODO Auto-generated method stub
		Node newNode = new Node(data);
		
		if ( this.head == null ) {
			this.head = newNode;
		}
		else {
			Node lastNode = this.head;
			while ( lastNode.getNext() != null ) {
				lastNode = lastNode.getNext();
			}
			lastNode.setNext(newNode);
		}
		
		this.size++;
	}

	@Override
	/**
	 * Adds a new element at a specific position.
	 * @param data Data to insert at specific index.
	 * @param index Index to add a new element at.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or past the size of the list.
	 */
	public void insert(Object data, int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if ( index == 0 ) {
			prepend(data);
		}
		else if ( index > 0 && index < this.size ) {
			Node newNode = new Node(data);
			Node previousNode =  this.head;
			int i = 0;
			while ( ( i - index ) != -1 ) {
				previousNode = previousNode.getNext();
				i++;
			}
			
			newNode.setNext(previousNode.getNext());
			previousNode.setNext(newNode);
			
			this.size++;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	/**
	 * Replaces the data  at index.
	 * @param data Data to replace at specific index.
	 * @param index Index of the element to replace.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or larger than size - 1 of list.
	 */
	public void replace(Object data, int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if ( index < 0 || index >= this.size ) {
			throw new IndexOutOfBoundsException();
		} else {
			Node currentNode = this.head;
			int i = 0;
			while ( ( i - index ) != 0  ) {
				currentNode = currentNode.getNext();
				i++;
			}
			
			currentNode.setData(data);
		}
	}

	@Override
	/**
	 * Gets the number of elements in the list.
	 * @return Size of list (0 meaning empty)
	 */
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	/**
	 * Removes element at index from list, reducing the size.
	 * @param index Index of the element to remove.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or past the size - 1.
	 */
	public void delete(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if ( index == 0 ) {
			Node delete = head;
			head = head.getNext();
			delete.setNext(null);
			this.size--;
		}
		else if ( index > 0 && index < this.size ){
			int i = 0;
			Node previousNode =  this.head;
			while ( ( i - index ) != -1 ) {
				previousNode = previousNode.getNext();
				i++;
			}
			Node delete = previousNode.getNext();
			previousNode.setNext(delete.getNext());
			delete.setNext(null);
			this.size--;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	/**
	 * Gets the data at the specified index.
	 * @param index Index of the element to get.
	 * @return Data in element or null if it was not found.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or larger than size - 1 of the list.
	 */
	public Object retrieve(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if ( index < 0 || index >= this.size ) {
			throw new IndexOutOfBoundsException();
		} else {
			int i = 0;
			Node currentNode  = this.head;
			while ( ( i - index ) != 0 ) {
				currentNode = currentNode.getNext();
				i++;
			}
			return currentNode.getData();
		}
	}

	@Override
	/**
	 * Gets the first index of element containing data.
	 * @param data Matched data to find at the first index of.
	 * @return First of index of the element with matching data or -1 if not found.
	 */
	public int indexOf(Object data) {
		// TODO Auto-generated method stub
		Node currentNode = this.head;
		int i = 0;
		while ( currentNode != null ) {
			if ( currentNode.getData().equals(data) ) return i;
			else {
				currentNode = currentNode.getNext();
				i++;
			}
		}
		return -1;
	}

	@Override
	/**
	 * Go through elements and check if we have one with data.
	 * @param data Data to search for.
	 * @return True if element exists with value.
	 */
	public boolean contains(Object data) {
		// TODO Auto-generated method stub
		return ( this.indexOf(data) >= 0 ) ? true : false;
	}
	
	
}