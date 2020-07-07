package system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveEmployee extends JFrame{
    private JPanel MainRemovePanel;
    private JTextField employeeId;
    private JButton cancelButton;
    private JButton confirmButton;
    private JLabel Message;

    public RemoveEmployee(app ap){

        //Creating Frame
        this.add(MainRemovePanel);
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);



        // confirm button listener
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        if (!employeeId.getText().isEmpty() ) {
                            Employee employee = Employee.searchEmployee(Integer.parseInt(employeeId.getText()));
                            if(employee.getName() == "no employee"){
                                Message.setText("Can't find any employee by this ID.");
                                Message.setVisible(true);
                            }else{
                                Employee.removeEmployee(employee);
                                ap.refreshEmployeeBox();
                                dispose();
                            }
                        }
                }catch(Exception ee){
                        Message.setText("Illegal input");
                        Message.setVisible(true);
                }


            }
        });

        //cancel button listener
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
