/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day02todos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Teacher
 */
public class TodoTest {
    
    /* // it's difficult to test static counter since we can't control the order of test execution
    @Test
    public void testGetInstanceCount() {
        System.out.println("getInstanceCount");
        assertEquals(0, Todo.getInstanceCount());
    } */

    @Test
    public void testToString() {
        System.out.println("toString");
        Todo instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testToDataString() throws ParseException, DataInvalidException {
        System.out.println("toDataString");
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("20/10/2001"); // ex
        Todo instance = new Todo("Run this test", date, 3, Todo.TaskStatus.Pending); // ex
        String expResult = "Run this test;2001-10-20;3;Pending";
        String result = instance.toDataString();
        assertEquals(expResult, result);
    }

    @Test
    public void testDataLineConstructor() throws DataInvalidException, ParseException {
        System.out.println("Todo(String dataLine)");
        Todo instance = new Todo("Run this test;2001-10-20;3;Done"); // ex
        assertEquals("Run this test", instance.getTask());
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("20/10/2001"); // ex
        assertEquals(date, instance.getDueDate());
        assertEquals(3, instance.getHoursOfWork());
        assertEquals(Todo.TaskStatus.Done, instance.getStatus());
    }
    
    @Test(expected = DataInvalidException.class)
    public void testDueDateInvalid() throws DataInvalidException, ParseException {
        System.out.println("Todo.setDueDate(invalid)");
        Date invalidDate = new SimpleDateFormat("dd/MM/yyyy").parse("20/10/2101"); // ex
        Todo instance = new Todo("Test", invalidDate, 3, Todo.TaskStatus.Done); // must throw exception
    }
    
    @Test
    public void testDueDateManyValid() throws DataInvalidException, ParseException {
        System.out.println("Todo.setDueDate(valid many)");
        String [] datesValid = { "01/01/1900", "05/11/2001", "31/12/2100" };
        for (String dateStr : datesValid) {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse("20/10/2101"); // ex
            Todo instance = new Todo("Test", date, 3, Todo.TaskStatus.Done); // no exception
            assertEquals(date, instance.getDueDate());
        }
    }

    @Test
    public void testDueDateManyInvalid() throws ParseException {
        System.out.println("Todo.setDueDate(valid many)");
        String [] datesInvalid = { "31/12/1899", "01/01/2101", "02/02/2222"};
        for (String dateStr : datesInvalid) {
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse("20/10/2101"); // ex
                Todo instance = new Todo("Test", date, 3, Todo.TaskStatus.Done); // must throw exception
                fail("Exception expected but did not happen");
            } catch (DataInvalidException ex) {
                // ignore - we expected the exception
            }
        }
    }

    
}
