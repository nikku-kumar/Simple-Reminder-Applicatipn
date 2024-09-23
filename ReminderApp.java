import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderApp {
    private Timer timer;

    private JComboBox<String> dayDropdown;
    private JComboBox<String> activityDropdown;
    private JComboBox<String> hourDropdown;
    private JComboBox<String> minuteDropdown;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Daily Reminder App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        ReminderApp app = new ReminderApp();
        app.createUI(frame);

        frame.setVisible(true);
    }

    private void createUI(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel dayLabel = new JLabel("Day of the Week:");
        dayLabel.setBounds(30, 20, 120, 30);
        panel.add(dayLabel);

        dayDropdown = new JComboBox<>(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});
        dayDropdown.setBounds(150, 20, 120, 30);
        panel.add(dayDropdown);

        JLabel timeLabel = new JLabel("Set Time (HH:MM):");
        timeLabel.setBounds(30, 60, 120, 30);
        panel.add(timeLabel);

        hourDropdown = new JComboBox<>();
        for (int i = 0; i < 24; i++) {
            hourDropdown.addItem(String.format("%02d", i));
        }
        hourDropdown.setBounds(150, 60, 50, 30);
        panel.add(hourDropdown);

        minuteDropdown = new JComboBox<>();
        for (int i = 0; i < 60; i++) {
            minuteDropdown.addItem(String.format("%02d", i));
        }
        minuteDropdown.setBounds(210, 60, 50, 30);
        panel.add(minuteDropdown);

        JLabel activityLabel = new JLabel("Activity:");
        activityLabel.setBounds(30, 100, 120, 30);
        panel.add(activityLabel);

        activityDropdown = new JComboBox<>(new String[]{"Wake up", "Go to gym", "Breakfast", "Meetings", "Lunch", "Quick nap", "Go to library", "Dinner", "Go to sleep"});
        activityDropdown.setBounds(150, 100, 120, 30);
        panel.add(activityDropdown);

        JButton setReminderButton = new JButton("Set Reminder");
        setReminderButton.setBounds(100, 150, 150, 40);
        panel.add(setReminderButton);

        setReminderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReminder();
            }
        });

        frame.add(panel);
    }

    private void setReminder() {
        String day = (String) dayDropdown.getSelectedItem();
        String hour = (String) hourDropdown.getSelectedItem();
        String minute = (String) minuteDropdown.getSelectedItem();
        String activity = (String) activityDropdown.getSelectedItem();

        long delay = calculateDelay(day, Integer.parseInt(hour), Integer.parseInt(minute));

        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();
        timer.schedule(new ReminderTask(activity), delay);

        JOptionPane.showMessageDialog(null, "Reminder set for " + activity + " on " + day + " at " + hour + ":" + minute);
    }

    private long calculateDelay(String day, int hour, int minute) {
        // Get current time
        Calendar current = Calendar.getInstance();
        long currentTimeMillis = current.getTimeInMillis();

        // Set target time
        Calendar target = Calendar.getInstance();
        target.set(Calendar.HOUR_OF_DAY, hour);
        target.set(Calendar.MINUTE, minute);
        target.set(Calendar.SECOND, 0);
        target.set(Calendar.MILLISECOND, 0);

        // Set the correct day of the week
        int targetDayOfWeek = getDayOfWeek(day);
        int currentDayOfWeek = current.get(Calendar.DAY_OF_WEEK);

        // Adjust for future days if needed
        if (targetDayOfWeek < currentDayOfWeek || (targetDayOfWeek == currentDayOfWeek && target.getTimeInMillis() <= currentTimeMillis)) {
            target.add(Calendar.DATE, (targetDayOfWeek - currentDayOfWeek + 7) % 7);
        }

        return target.getTimeInMillis() - currentTimeMillis;
    }

    private int getDayOfWeek(String day) {
        switch (day) {
            case "Monday": return Calendar.MONDAY;
            case "Tuesday": return Calendar.TUESDAY;
            case "Wednesday": return Calendar.WEDNESDAY;
            case "Thursday": return Calendar.THURSDAY;
            case "Friday": return Calendar.FRIDAY;
            case "Saturday": return Calendar.SATURDAY;
            case "Sunday": return Calendar.SUNDAY;
            default: return Calendar.MONDAY;
        }
    }

    class ReminderTask extends TimerTask {
        private String activity;

        ReminderTask(String activity) {
            this.activity = activity;
        }

        @Override
        public void run() {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "Reminder: Time to " + activity + "!");
                java.awt.Toolkit.getDefaultToolkit().beep();
            });
        }
    }
}

