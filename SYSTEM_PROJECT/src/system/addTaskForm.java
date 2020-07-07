package system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

public class addTaskForm extends JFrame{
    private JButton cancelButton;
    private JButton createButton;
    private JTextArea contextField;
    private JComboBox assignedBox;
    private JTextField titleField;
    private JPanel assignmentPanel;
    private JRadioButton lowRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton highRadioButton;
    private JCheckBox makeAReminderCheckBox;
    private LocalTime timeOfReminder;
    private LocalDate dateOfReminder;

    public void setTimeOfReminder(LocalTime timeOfReminder) {
        this.timeOfReminder = timeOfReminder;
    }

    public void setDateOfReminder(LocalDate dateOfReminder) {
        this.dateOfReminder = dateOfReminder;
    }

    public addTaskForm(app ap) {
        //Creating Frame
        this.add(assignmentPanel);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //setting assignmentBox
        Employee[] employees = Employee.getEmployees();
        assignedBox.addItem(null);
        for(int i = 0 ; i < Employee.getNumOfEmployees() ; i++)
            assignedBox.addItem(employees[i].getName());

        //initial radio buttons
        ButtonGroup buttons = new ButtonGroup();
        buttons.add(lowRadioButton);
        buttons.add(mediumRadioButton);
        buttons.add(highRadioButton);


        //cancel button listener
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });

        //create button listener
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (ap.getUser().isManager() || ap.getUser().getName().equalsIgnoreCase(((String) assignedBox.getSelectedItem()))) {


                    String name = (String) assignedBox.getItemAt(assignedBox.getSelectedIndex());
                    Employee employee = Employee.searchEmployee(name);
                    String title = titleField.getText();
                    String context = contextField.getText();
                    int priority = 1;
                    if (lowRadioButton.isSelected())
                        priority = 1;
                    if (mediumRadioButton.isSelected())
                        priority = 2;
                    if (highRadioButton.isSelected())
                        priority = 3;
                    Assignment assignment;
                    if (makeAReminderCheckBox.isSelected())
                        assignment = new Reminder(Assignment.getNextAssignmentNum(), LocalTime.now(), LocalDate.now(), employee, priority, 1,  title, context, dateOfReminder, timeOfReminder);
                    else
                        assignment = new Assignment(Assignment.getNextAssignmentNum(), LocalTime.now(), LocalDate.now(), employee, priority, 1,  title, context);
                    assignment.addTask();
                    ap.setAssignmentList();
                    dispose();


                } else
                    new popUP("Only Manager can assign tasks to other people");

            }
        });

        //reminder checkBox listener
        makeAReminderCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new makeReminderForm(addTaskForm.this);

            }
        });

    }
}
