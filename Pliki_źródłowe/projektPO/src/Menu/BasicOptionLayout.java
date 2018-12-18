package Menu;

import Main.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BasicOptionLayout extends JPanel {

    /**
     * Screen- stores actual JFrame frame
     */
    private JFrame Screen;
    protected JPanel contentPanel,northPanel,southPanel,westPanel,eastPanel;
    protected JPanel panelForButton;
    protected JButton backButton;

    protected JLabel lText;

    /**
     * Constructor which creates basic elements(basic schema) and draw it on the JFrame Screen
     * Basic schema is used to create other options, because the basic elements will be the same.
     */
    public BasicOptionLayout()
    {
        this.Screen= Game.getScreen();
        //Removing old panel
        Screen.getContentPane().removeAll();
        Screen.getContentPane().invalidate();
        Screen.getContentPane().validate();


        Screen.setLayout(new BorderLayout());
        northPanel=new JPanel();
        southPanel=new JPanel();
        westPanel=new JPanel();
        eastPanel=new JPanel();
        contentPanel=new JPanel();

        panelForButton=new JPanel();//panel which is in content panel


        northPanel.setLayout(new GridLayout(1,3));
        southPanel.setLayout(new GridLayout(1,3));
        westPanel.setLayout(new GridLayout(7,1));
        eastPanel.setLayout(new GridLayout(7,1));
        contentPanel.setLayout(new GridLayout(2,1));

        panelForButton.setLayout(new GridLayout(4,1));


        lText=new JLabel("",SwingConstants.CENTER);
        backButton=new JButton("Back");

        //Modeling panelForButton
        panelForButton.add(Box.createRigidArea(new Dimension(1,0)));
        panelForButton.add(Box.createRigidArea(new Dimension(1,0)));
        panelForButton.add(Box.createRigidArea(new Dimension(1,0)));
        panelForButton.add(backButton);


        contentPanel.add(lText);
        contentPanel.add(panelForButton);



        northPanel.add(Box.createRigidArea(new Dimension(0,100)));
        southPanel.add(Box.createRigidArea(new Dimension(0,100)));
        eastPanel.add(Box.createRigidArea(new Dimension(Screen.getWidth()/2-250,0)));
        westPanel.add(Box.createRigidArea(new Dimension(Screen.getWidth()/2-250,0)));

        Screen.add(northPanel,BorderLayout.NORTH);
        Screen.add(southPanel, BorderLayout.SOUTH);
        Screen.add(westPanel,BorderLayout.WEST);
        Screen.add(eastPanel,BorderLayout.EAST);
        Screen.add(contentPanel,BorderLayout.CENTER);



        ListenForButton lForButton=new ListenForButton();
        backButton.addActionListener(lForButton);

    }

    /**
     * Method which is responsible for handling buttons events, which will occur.
     * Method implements ActionListener interface to those handle events.
     */
    private class ListenForButton extends JFrame implements ActionListener
    {

        /**
         * Method is responsible for handling button event.
         * @param e action event which occurred.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==backButton)
            {
                new Menu();
                Screen.setVisible(true);

            }
        }
    }

}
