package system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class makeReminderForm extends JFrame {
    private JButton SetButton;
    private JButton cancelButton;
    private JSpinner timeSpinner;
    private JSpinner dateSpinner;
    private JPanel ReminderPanel;

    public makeReminderForm(addTaskForm origin) {
        //Creating Frame
        this.add(ReminderPanel);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //setting Jspinners
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date,null,null, Calendar.HOUR_OF_DAY);
        timeSpinner.setModel(sm);
        JSpinner.DateEditor de = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(de);

        Date date1 = new Date();
        SpinnerDateModel sm1 = new SpinnerDateModel(date1,null,null, Calendar.HOUR_OF_DAY);
        dateSpinner.setModel(sm1);
        JSpinner.DateEditor de1 = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(de1);

        //cancel button listener
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });

        //set button listener
        SetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{

                    Date tempDate = (Date)timeSpinner.getValue();
                    Time time = new Time(tempDate.getTime());
                    origin.setTimeOfReminder(time.toLocalTime());

                    Date tempDate1 = (Date)dateSpinner.getValue();
                    LocalDate date = Instant.ofEpochMilli(tempDate1.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();;


                    origin.setDateOfReminder(date);

                    dispose();

                }catch (Exception ee) {

                    new popUP("Invalid input");

                }


            }
        });
    }
}
