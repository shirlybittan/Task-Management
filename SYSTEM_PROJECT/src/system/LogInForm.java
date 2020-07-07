package system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class LogInForm extends JFrame{
    private JPanel LogInPanel;
    private JButton logInButton;
    private JButton cancelButton;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel incorrectMessage;


    public LogInForm(app ap){
        //Creating Frame
        this.add(LogInPanel);
        this.setSize(450,350);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

         incorrectMessage.setVisible(false);
        //Cancel Button Listener
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });

        //Log-In Button Listener
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!textField2.getText().isEmpty() && !textField1.getText().isEmpty()) {
                        Employee employee = Employee.searchEmployee(Integer.parseInt(textField1.getText()));
                        if (employee.getPassword() != Integer.parseInt(textField2.getText())) {
                            incorrectMessage.setText("ID or Password is incorrect");
                            incorrectMessage.setVisible(true);
                        }else {
                            app.setUser(employee);
                            ap.setAssignmentList();
                            ap.setUserNameText(employee.getName());
                            Reminder.setAlarms();
                                if(employee.isManager())
                                    ap.setStatusText("Manager");
                                else{ap.setStatusText("Employee");};
                            dispose();
                        }
                    }
                }catch (Exception ee) {
                    incorrectMessage.setText("  Illegal input");
                    incorrectMessage.setVisible(true);
                }

            }
        });
        textField2.addComponentListener(new ComponentAdapter() {
        });
    }
}
