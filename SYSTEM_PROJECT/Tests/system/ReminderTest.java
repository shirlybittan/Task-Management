package system;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class ReminderTest extends AssignmentTest{

    protected static Reminder reminder = new Reminder(444, LocalTime.now(), LocalDate.now(),
            employee, 1,  new NotDone().status(),"reminderTitle","reminderContext",LocalDate.now(),LocalTime.now().plusMinutes(10));

    @Test
    public void Reminder_Adding_Searching_Deleting() {
        reminder.addTask();
        assertEquals(Reminder.searchAssignment(reminder.getAssignmentNum()),reminder);// check search by assignmentNum
        assertEquals(Reminder.searchAssignment(reminder.getTitle()),reminder);// check search by title
        Reminder.removeTask(reminder.getAssignmentNum());
        Assignment reminder1 = Reminder.searchAssignment(reminder.getAssignmentNum());
        assertNull(reminder1);
        Reminder.refreshData();
    }
}