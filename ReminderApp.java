import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderApp {
    // Timer object to schedule tasks
    private Timer timer;

    // Dropdown for days of the week
    private JComboBox<String> dayDropdown;
    // Dropdown for selecting activity
    private JComboBox<String> activityDropdown;
    // Time dropdowns for hour and minute
    private JComboBox<String> hourDropdown;
    private JComboBox<String> minuteDropdown;

    public static void main(String[] args) {
        // Create the main GUI window
        JFrame frame = new JFrame("Daily Reminder App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create an instance of ReminderApp
        ReminderApp app = new ReminderApp();
        app.createUI(frame);

        frame.setVisible(true);
    }

    // Method to create the user interface
    private void createUI(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Label and Dropdown for Day
        JLabel dayLabel = new JLabel("Day of the Week:");
        dayLabel.setBounds(30, 20, 120, 30);
        panel.add(dayLabel);

        dayDropdown = new JComboBox<>(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});
        dayDropdown.setBounds(150, 20, 120, 30);
        panel.add(dayDropdown);

        // Label and Dropdown for Time (Hour and Minute)
        JLabel timeLabel = new JLabel("Set Time (HH:MM):");
        timeLabel.setBounds(30, 60, 120, 30);
        panel.add(timeLabel);

        // Hour dropdown (0-23)
        hourDropdown = new JComboBox<>();
        for (int i = 0; i < 24; i++) {
            hourDropdown.addItem(String.format("%02d", i));
        }
        hourDropdown.setBounds(150, 60, 50, 30);
        panel.add(hourDropdown);

        // Minute dropdown (0-59)
        minuteDropdown = new JComboBox<>();
        for (int i = 0; i < 60; i++) {
            minuteDropdown.addItem(String.format("%02d", i));
        }
        minuteDropdown.setBounds(210, 60, 50, 30);
        panel.add(minuteDropdown);

        // Label and Dropdown for Activity
        JLabel activityLabel = new JLabel("Activity:");
        activityLabel.setBounds(30, 100, 120, 30);
        panel.add(activityLabel);

        activityDropdown = new JComboBox<>(new String[]{"Wake up", "Go to gym", "Breakfast", "Meetings", "Lunch", "Quick nap", "Go to library", "Dinner", "Go to sleep"});
        activityDropdown.setBounds(150, 100, 120, 30);
        panel.add(activityDropdown);

        // Set Reminder Button
        JButton setReminderButton = new JButton("Set Reminder");
        setReminderButton.setBounds(100, 150, 150, 40);
        panel.add(setReminderButton);

        // ActionListener to set the reminder
        setReminderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReminder();
            }
        });

        frame.add(panel);
    }

    // Method to set the reminder
    private void setReminder() {
        String day = (String) dayDropdown.getSelectedItem();
        String hour = (String) hourDropdown.getSelectedItem();
        String minute = (String) minuteDropdown.getSelectedItem();
        String activity = (String) activityDropdown.getSelectedItem();

        // Convert selected time into milliseconds for scheduling
        int selectedHour = Integer.parseInt(hour);
        int selectedMinute = Integer.parseInt(minute);
        long delay = calculateDelay(selectedHour, selectedMinute);

        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();
        timer.schedule(new ReminderTask(activity), delay);

        JOptionPane.showMessageDialog(null, "Reminder set for " + activity + " on " + day + " at " + hour + ":" + minute);
    }

    // Calculate the delay in milliseconds until the reminder should be triggered
    private long calculateDelay(int hour, int minute) {
        long currentTimeMillis = System.currentTimeMillis();
        long targetTimeMillis = (hour * 3600000) + (minute * 60000);

        // Return the delay in milliseconds
        return targetTimeMillis - currentTimeMillis;
    }

    // Inner class to define the reminder task
    class ReminderTask extends TimerTask {
        private String activity;

        ReminderTask(String activity) {
            this.activity = activity;
        }

        @Override
        public void run() {
            JOptionPane.showMessageDialog(null, "Reminder: Time to " + activity + "!");
            // You can also play a sound here using Toolkit
            java.awt.Toolkit.getDefaultToolkit().beep();
        }
    }
}
