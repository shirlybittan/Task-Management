package system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class popUP extends JFrame{
    private JPanel popPanel;
    private JLabel Message;
    private JButton exitButton;

    public popUP(){
        //Creating Frame
        this.add(popPanel);
        this.setSize(500,350);
        this.setLocationRelativeTo(null);
        this.setVisible(true);




    }

    public popUP(String message){
        //Creating Frame
        this.add(popPanel);
        this.setSize(500,350);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        Message.setText(message);



        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();


            }
        });

    }
}
