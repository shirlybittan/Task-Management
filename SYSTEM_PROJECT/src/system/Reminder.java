package system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Timer;

public class Reminder extends Assignment {
    private LocalDate reminderDate;
    private LocalTime reminderTime;
    private static int NumOfReminders = 0;
    private static Alarm[] alarms = new Alarm[100];
    private static Timer timer = new Timer();

    public static void setAlarms() {
        Reminder.alarms = setAlarmsInFunc();
    }

    public Reminder(int assignmentNum, LocalTime madeTime, LocalDate madeDate, Employee assignTo, int priority, int status, String title, String context, LocalDate reminderDate, LocalTime reminderTime) {
        super(assignmentNum, madeTime, madeDate, assignTo, priority, status,  title, context);
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
    }

    public static void activateTimer(long delay, LocalDate reminderDate ,LocalTime reminderTime, Employee employee, int assignmentNum){

        Alarm alarm = new Alarm(assignmentNum,reminderDate,reminderTime,employee);
        alarms[NumOfReminders] = alarm;
        NumOfReminders++;

        if(reminderDate.isEqual(LocalDate.now()) && employee == app.getUser())
            timer.schedule(alarm, delay);


    }



    private static Alarm[] setAlarmsInFunc() {
        Scanner x;
        Alarm[] alarms = new Alarm[100];
        try {
            x = new Scanner(new File("Alarms_Data.txt"));
            while (x.hasNext()) {

                int tempAssignmentNum = Integer.parseInt(x.next());
                LocalTime tempTime = LocalTime.parse(x.next());
                LocalDate tempDate = LocalDate.parse(x.next());
                String tempEmployeeName = x.nextLine();
                tempEmployeeName = x.nextLine();

                long delay = (((tempTime.getHour() - LocalTime.now().getHour()) * 60) + ((tempTime.getMinute() - LocalTime.now().getMinute()))) * 1000 * 60;
                if((tempDate.isEqual(LocalDate.now())  && delay<=0) || (tempDate.isBefore(LocalDate.now()))){
                    removeTask(tempAssignmentNum, false);
                }else
                  activateTimer(delay,tempDate, tempTime, Employee.searchEmployee(tempEmployeeName),tempAssignmentNum);
            }
            x.close();
        } catch (Exception e) {
            System.out.println("error occurred in Reminder/setAlarmsInFunc");
        }

        return alarms;
    } // collecting initial data from file

    public void addReminderToFile() {

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("Alarms_Data.txt",true));
            if(NumOfReminders != 0)
                writer.newLine();
            writer.append(Integer.toString(this.getAssignmentNum()));
            writer.newLine();
            writer.append(reminderTime.toString());
            writer.newLine();
            writer.append(reminderDate.toString());
            writer.newLine();
            writer.append(this.getAssignedTo().getName());
            writer.close();

        }catch (IOException e){
            System.out.println("error occurred in Reminder/addReminderToFile");

        }
    }

    public void addTask(){
        super.addTask();
        this.addReminderToFile();
        long delay = (((reminderTime.getHour() - LocalTime.now().getHour()) * 60) + ((reminderTime.getMinute() - LocalTime.now().getMinute()))) * 1000 * 60;
        activateTimer(delay,this.reminderDate,this.reminderTime, this.getAssignedTo(),this.getAssignmentNum());
    }

    public static void removeTask(int numOfAssignment, boolean removeAssignment){

        if(removeAssignment)
            Assignment.removeTask(numOfAssignment);
        for(int i = 0 ;i < NumOfReminders ; i++){
            if (alarms[i].getNum() == numOfAssignment) {
                for (int j = i , k = 0; j <NumOfReminders; j++ , k++)
                    alarms[i + k] = alarms[j+1];
                NumOfReminders--;
            }
        }
        refreshData();
    }

    public static void refreshData(){

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("Alarms_Data.txt"));
            for (int i = 0 ; i < NumOfReminders ; i++) {

                writer.append(Integer.toString(alarms[i].getNum()));
                writer.newLine();
                writer.append(alarms[i].getReminderTime().toString());
                writer.newLine();
                writer.append(alarms[i].getReminderDate().toString());
                writer.newLine();
                writer.append(alarms[i].getEmployee().getName());
                if(i+1 < NumOfReminders)
                    writer.newLine();

            }

            writer.close();
        }catch (IOException e){
            System.out.println("error occurred in Reminder/refreshData");

        }
    } // moving data from array to file


}
