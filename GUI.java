import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import static javax.swing.BoxLayout.X_AXIS;

public class GUI extends JFrame {
    GetDate getDateObject = new GetDate();

    GUI() {
        Image icon = Toolkit.getDefaultToolkit().getImage(".\\src\\Calendar.jpg");
        super.setIconImage(icon);
        super.setTitle("Calendar");
        super.setSize(1414, 737);
        super.setLayout(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);

        //Create 7 panels, one for each day of the week
        for (int i = 1; i <= 7; i++) {
            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.PAGE_AXIS));
            dayPanel.setBounds((i - 1) * 200, 0, 200, 700);
            dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            createAndAddComponentsToDayPanel(dayPanel, i);
            super.add(dayPanel);
            super.setVisible(true);
            super.repaint();
        }
    }

    public void createAndAddComponentsToDayPanel(JPanel dayPanel, int day) {
        JLabel date = new JLabel(getDateObject.thisWeeksDates(day).toString());
        date.setAlignmentX(CENTER_ALIGNMENT);

        JLabel nameOfTheDay = new JLabel(getDateObject.weekDays[day - 1]);
        nameOfTheDay.setAlignmentX(CENTER_ALIGNMENT);
        markCurrentDay(day, date, nameOfTheDay, dayPanel);

        JTextArea inputAreaForEvent = new JTextArea("Add event...");
        inputAreaForEvent.setLineWrap(true);
        inputAreaForEvent.setMaximumSize(new Dimension(200, 80));
        mouseListenerForTextArea(inputAreaForEvent);
        resetTextAreas(inputAreaForEvent);

        JButton addButton = new JButton("Add");
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        addButton.setMaximumSize(new Dimension(120, 33));
        addButtonListener(addButton, dayPanel, inputAreaForEvent);

        //Adds and creates some space between the components in the dayPanels
        dayPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dayPanel.add(date);
        dayPanel.add(nameOfTheDay);
        dayPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dayPanel.add(inputAreaForEvent);
        dayPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dayPanel.add(addButton);
        dayPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private void addButtonListener(JButton addButton, JPanel dayPanel, JTextArea inputAreaForEvent) {
        ActionListener bListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Makes sure that the textfield has a valid input
                if (inputAreaForEvent.getText().isEmpty() || inputAreaForEvent.getText().equals("Add event...")
                        || inputAreaForEvent.getText().equals("Please add an event before \npressing \"Add\"")) {
                    inputAreaForEvent.setText("Please add an event before \npressing \"Add\"");
                    //Creates a new event if the input is ok
                } else {
                    JPanel panelForAddedEventAndRemoveButton = new JPanel();
                    panelForAddedEventAndRemoveButton.setLayout(new BoxLayout(panelForAddedEventAndRemoveButton, X_AXIS));
                    panelForAddedEventAndRemoveButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                    panelForAddedEventAndRemoveButton.setBackground(Color.WHITE);

                    JTextArea addedEvent = new JTextArea("  " + inputAreaForEvent.getText());
                    addedEvent.setMaximumSize(new Dimension(200, 60));
                    addedEvent.setLineWrap(true);
                    addedEvent.setEditable(false);

                    JButton removeButton = new JButton("X");
                    removeButton.setForeground(Color.WHITE);
                    removeButton.setBackground(Color.RED);

                    panelForAddedEventAndRemoveButton.add(addedEvent);
                    panelForAddedEventAndRemoveButton.add(removeButton);
                    dayPanel.add(panelForAddedEventAndRemoveButton);
                    inputAreaForEvent.setText("Add event...");

                    removeButtonListener(panelForAddedEventAndRemoveButton, removeButton, dayPanel);
                }
                setVisible(true);
            }
        };
        addButton.addActionListener(bListener);
    }

    //Adds an option to remove unwanted events
    private void removeButtonListener(JPanel panelForAddedEventAndRemoveButton, JButton removeButton, JPanel dayPanel) {
        ActionListener removeButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dayPanel.remove(panelForAddedEventAndRemoveButton);
                repaint();
                setVisible(true);
            }
        };
        removeButton.addActionListener(removeButtonListener);
    }

    //Makes the text in the textarea disappear when the user is about to write something
    public void mouseListenerForTextArea(JTextArea addEvent) {
        addEvent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (addEvent.getText().equals("Add event...") || addEvent.getText().equals("Please add an event before \npressing \"Add\"")) {
                    addEvent.setText("");
                }
            }
        });
    }

    public void markCurrentDay(int day, JLabel date, JLabel nameOfTheDay, JPanel dayPanel) {
        if ((getDateObject.thisWeeksDates(day)).getDayOfYear() == LocalDate.now().getDayOfYear()) {
            date.setForeground(Color.WHITE);
            nameOfTheDay.setForeground(Color.WHITE);
            dayPanel.setBackground(Color.LIGHT_GRAY);
        }
    }

    //Makes the "Add event..." text come back if the user clicks outside the textarea
    public void resetTextAreas(JTextArea addEvent) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (addEvent.getText().isEmpty()) {
                    addEvent.setText("Add event...");
                }
            }
        });
    }
}
