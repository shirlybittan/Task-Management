package system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignmentDetails extends JFrame {
    private JButton saveChangesButton;
    private JButton exitButton;
    private JLabel taskNumLabel;
    private JTextField titleField;
    private JTextArea contextArea;
    private JComboBox assignedToBox;
    private JRadioButton doneRadioButton;
    private JRadioButton inProgressRadioButton;
    private JRadioButton notDoneRadioButton;
    private JPanel TaskDetailsPanel;


    public AssignmentDetails(app ap , Assignment assignment) {
        //Creating Frame
        this.add(TaskDetailsPanel);
        this.setSize(450, 350);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //setting assignedBox
        Employee[] employees = Employee.getEmployees();
        assignedToBox.addItem(null);
        int j = 0;
        for(int i = 0 ; i < Employee.getNumOfEmployees() ; i++) {
            assignedToBox.addItem(employees[i].getName());
            if(employees[i].getName().equalsIgnoreCase(assignment.getAssignedTo().getName())){
                j = i;
            }
        }
        assignedToBox.setSelectedIndex(j+1);

        //setting
        taskNumLabel.setText("Task Number: " + assignment.getAssignmentNum());
        titleField.setText(assignment.getTitle());
        contextArea.setText(assignment.getContext());

        //initial radio buttons
        ButtonGroup buttons = new ButtonGroup();
        buttons.add(doneRadioButton);
        buttons.add(inProgressRadioButton);
        buttons.add(notDoneRadioButton);


        //exit button listener
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });

        //save changes button listener
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(ap.getUser() != null){

                    if(ap.getUser() == Employee.searchEmployee(((String) assignedToBox.getSelectedItem()))) { // User can change his own task status
                        if (notDoneRadioButton.isSelected())
                            assignment.setStatus(new NotDone());
                        else if (inProgressRadioButton.isSelected())
                            assignment.setStatus(new InProgress());
                        else if (doneRadioButton.isSelected())
                            assignment.setStatus(new Done());
                        assignment.setAssignedTo(Employee.searchEmployee(((String) assignedToBox.getSelectedItem())));
                    }else{
                        if(!ap.getUser().isManager())
                        new popUP("Only a manager can assign tasks to other people.");
                    }


                    if(ap.getUser().isManager()){
                        assignment.setAssignedTo(Employee.searchEmployee(((String) assignedToBox.getSelectedItem())));
                        assignment.setTitle(titleField.getText());
                        assignment.setContext(contextArea.getText());
                    }}else
                    new popUP("Only a manager can change that field.");

                ap.setAssignmentList();
                Assignment.refreshData();
                dispose();


            }
        });
    }
}
