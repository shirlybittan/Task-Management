package system;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TimerTask;

public class Alarm extends TimerTask {
    private int num;
    private String string = null;
    private LocalDate reminderDate;
    private LocalTime reminderTime;
    private Employee employee;

    //getters & setters

    public int getNum() {
        return num;
    }

    public LocalDate getReminderDate() {
        return reminderDate;
    }

    public LocalTime getReminderTime() {
        return reminderTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Alarm(int num, LocalDate reminderDate, LocalTime reminderTime, Employee employee ){

    this.num = num;
    this.reminderDate = reminderDate;
    this.reminderTime = reminderTime;
    this.employee = employee;

}

    public Alarm(int num, String string, LocalDate reminderDate, LocalTime reminderTime, Employee employee ){

        this.num = num;
        this.string = string;
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
        this.employee = employee;

    }

    @Override
    public void run() {

            if(string == null)
                new popUP("Reminder for task number: " + num);
            else
                new popUP(string + num);

        }


    }

