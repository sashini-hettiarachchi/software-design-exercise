/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fi.tuni.prog3.exercise02;
import org.apache.hc.core5.http.ParseException;


/**
 *
 * @author sashinihettiarachchi
 */
public class Exercise02 {

    public static void main(String[] args) throws ParseException {
        System.out.println("Starting....");
        APICaller apicaller = new APICaller();
        apicaller.callApi("https://jsonplaceholder.typicode.com/todos/1");
        Todo todo = new Todo();
        System.out.println("Todo Object: " + todo);
    }
}
 

