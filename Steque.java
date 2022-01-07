/*
 *  File: Steque.java
 *  Author: 
 *  Date: 18th Nov, 2021
 *  ---------------------------------------
 *  Steque is stack-ended queue data structure which supports
 *  stack operations: pop and push, along with queue enqueue 
 *  operation.
 *  
 *  Salient features:
 *  1. Operations like push, pop, enqueue are supported.
 *  2. NullPointerException is thrown when null element is inserted.
 *  3. UnsupportedOperationException is thrown when using remove() method.
 *  4. The data structure is iterable and is implemented for generic type.
 *  
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.lang.model.util.ElementScanner6;


/**
 * 
 * Steque is a stack-ended data structure which 
 * supports stack operations as well as queue's 
 * enqueue operation.
 * 
 * @author 
 * @version 1.0
 *
 */
public class Steque<Item> implements Iterable<Item> {
    private Node first, last;
    private class Node{
        Item item;
        Node next;
    }

    /**
     * constructs a steque object.
     */
    public Steque() {
        first=last=null;

    }
    
    
    /**
     * inserts an item in the steque in queue fashion.
     * @param item Item to be inserted.
     */
    public void enqueue(Item item) {
        Node oldLast =last;
        last = new Node();
        last.item =item;
        last.next=null;
        if(first == null)
            first = last;
        else
            oldLast.next = last;
    }
    
    
    /**
     * inserts an item in the steque in stack fashion.
     * @param item Item to be inserted.
     */
    public void push(Item item) {
        Node oldFirst = first;
        first= new Node();
        first.item = item;
        first.next = oldFirst; 
        if (last == null)
            last =first;
    }
    
    /**
     * pops a least recent item in steque.
     * @return Item object from steque.
     */
    public Item pop() {
        if (isEmpty())
            throw new NoSuchElementException("No element exists in Steque");
        Item item =first.item;
        first =first.next;
        return item;
    }
    
    /**
     * checks to see if steque is empty.
     * @return true if steque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return first == null || last == null;
    }
    
    /**
     * return the number of elements currently in the steque.
     * @return size as integer.
     */
    public int size() {
        int count = 0;
        for(Item item: this){
            count++;
        }
        return count;
    }
    
    /**
     * returns an iterator over the elements 
     * stored in steque.
     * 
     */
    public Iterator<Item> iterator() {
        return new StequeIterator();
    }
    public class StequeIterator implements Iterator<Item>{
        Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove(){
            throw new UnsupportedOperationException("Operation not supported");
        }
    }
    public static void main(String[] args){
        Steque<Integer> steq = new Steque<Integer>();
        steq.enqueue(2); 
        steq.enqueue(1); 
        steq.push(7); 
        steq.push(14); 
        Iterator<Integer> i=steq.iterator(); 
        System.out.println("elements in steque: "); 
        while(i.hasNext()){ 
            System.out.println(i.next()+" "); 
        } 
        System.out.println("Deleted elements: ");
        while(!steq.isEmpty()){ 
            System.out.println(steq.pop());
        }
    }
}
