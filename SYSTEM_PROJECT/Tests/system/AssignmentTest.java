package system;


import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AssignmentTest extends EmployeeTest {

    protected static Assignment assignment = new Assignment(555, LocalTime.now(), LocalDate.now(),
            employee, 1,  new NotDone().status(),"title","context" );



    @Test
    public void getAssignmentNum() {
        assertEquals(assignment.getAssignmentNum(),555);
    }

    @Test
    public void getPriority() {
        assertEquals(assignment.getPriority(),1);

    }

    @Test
    public void getAssignedTo() {
        assertEquals(assignment.getAssignedTo(),employee);

    }

    @Test
    public void getMadeDate() {
        assertEquals(assignment.getMadeDate(),LocalDate.now());

    }

    @Test
    public void getMadeTime() {
        assertEquals(assignment.getMadeTime().format(Assignment.myFormatObj),LocalTime.now().format(Assignment.myFormatObj));

    }

    @Test
    public void getTitle() {
        assertEquals(assignment.getTitle(),"title");

    }

    @Test
    public void getContext() {
        assertEquals(assignment.getContext(),"context");

    }

    @Test
    public void setAssignedTo() {
        Employee employee1 = new Employee(666,"employee1",666,false);
        assignment.setAssignedTo(employee1);
        assertEquals(assignment.getAssignedTo(),employee1);

    }

    @Test
    public void setTitle() {
        assignment.setTitle("title1");
        assertEquals(assignment.getTitle(),"title1");
        assignment.setTitle("title");
    }

    @Test
    public void setContext() {
        assignment.setContext("context1");
        assertEquals(assignment.getContext(),"context1");
        assignment.setContext("context");
    }

    @Test
    public void getStatus() {
        assertEquals(assignment.getStatus().status(),1);
    }

    @Test
    public void setStatus() {
        assignment.setStatus(new InProgress());
        assertEquals(assignment.getStatus().status(),2);
        assignment.setStatus(new Done());
        assertEquals(assignment.getStatus().status(),3);
        assignment.setStatus(new NotDone());

    }

    @Test
    public void Assignment_Adding_Searching_Deleting() {
        assignment.addTask();
        assertEquals(Assignment.searchAssignment(assignment.getAssignmentNum()),assignment);// check search by assignmentNum
        assertEquals(Assignment.searchAssignment(assignment.getTitle()),assignment);// check search by title
        Assignment.removeTask(assignment.getAssignmentNum());
        Assignment assignment1 = Assignment.searchAssignment(assignment.getAssignmentNum());
        assertNull(assignment1);
    }
}