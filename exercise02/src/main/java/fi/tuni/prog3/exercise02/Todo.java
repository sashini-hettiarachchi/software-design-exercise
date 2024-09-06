/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.exercise02;

/**
 *
 * @author sashinihettiarachchi
 */
public class Todo {
    
    public int userId;
    public int id;
    public String title;
    public boolean completed;

    public Todo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Todo() {}

    public void setTodo(Todo todo) {
        this.userId = todo.userId;
        this.id = todo.id;
        this.title = todo.title;
        this.completed = todo.completed;
    }
    
    public Todo getTodo() {
        return this;
    }
    
    @Override
    public String toString() {
        return "Todo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
    
}

