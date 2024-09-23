# Simple-Reminder-Applicatipn
# Daily Reminder Application

A simple Java desktop application that helps users set reminders for daily activities like waking up, going to the gym, eating, and more. The application uses Java Swing for the UI and Java `Timer` for scheduling the reminders.

## Features

- **Day Selection**: Choose the day of the week for the reminder.
- **Time Selection**: Set the hour and minute for the reminder (24-hour format).
- **Activity Selection**: Select an activity from the predefined list, including:
  - Wake up
  - Go to gym
  - Breakfast
  - Meetings
  - Lunch
  - Quick nap
  - Go to library
  - Dinner
  - Go to sleep
- **Reminder Notifications**: A pop-up notification and a beep sound alert the user when it's time for the activity.

## Technologies Used

- **Java Swing**: For creating the graphical user interface (GUI).
- **Java Timer/TimerTask**: For setting reminders and triggering notifications.
- **JOptionPane**: For displaying pop-up notifications.
- **Toolkit (System Beep)**: For playing a sound (chime) when the reminder is triggered.

## Installation

### Prerequisites

1. Java Development Kit (JDK) 8 or later
2. An IDE like **IntelliJ IDEA** or **Eclipse**

### Steps to Run the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/nikku-kumar/daily-reminder-app.git
