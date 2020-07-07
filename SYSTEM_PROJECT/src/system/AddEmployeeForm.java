package system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeeForm extends JFrame {
    private JPanel MainAddPanel;
    private JCheckBox checkBox1;
    private JButton cancelButton;
    private JButton saveButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    public AddEmployeeForm(app ap){

        //Creating Frame
        this.add(MainAddPanel);
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // Cancel button Listener
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Save Button Listener
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Employee tempEmployee = new Employee(Integer.parseInt(textField2.getText()),textField1.getText(),Integer.parseInt(textField3.getText()),checkBox1.isSelected());
                if(Employee.searchEmployee(tempEmployee.getId()).getId() != 0)
                    new popUP("This user is already in the system.");
                else {
                    Employee.addEmployee(tempEmployee);
                    ap.refreshEmployeeBox();
                    dispose();
                }

            }
        });
    }
}
