/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sashinihettiarachchi
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import fi.tuni.prog3.exercise02.Todo;

public class TodoTest {

    @Test
    public void testConstructorAndGetTodo() {
        Todo todo = new Todo(1, 101, "Learn Java", true);

        Todo result = todo.getTodo();
        assertEquals(1, result.userId); 
        assertEquals(101, result.id);    
        assertEquals("Learn Java", result.title);  
        assertTrue(result.completed);   // Assert that completed is true
    }

    @Test
    public void testSetTodo() {
        Todo todo1 = new Todo(1, 101, "Learn Java", true);

        Todo todo2 = new Todo(2, 202, "Write a blog", false);

        todo1.setTodo(todo2);

        Todo result = todo1.getTodo();
        assertEquals(2, result.userId); 
        assertEquals(202, result.id);   
        assertEquals("Write a blog", result.title); 
        assertFalse(result.completed); 
    }

    @Test
    public void testToString() {
        Todo todo = new Todo(1, 101, "Learn Java", true);

        String expected = "Todo{userId=1, id=101, title='Learn Java', completed=true}";
        assertEquals(expected, todo.toString());
    }
}

