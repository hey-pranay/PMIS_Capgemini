/*

Problem statement - 2
    Create a Java console application called MeetingTimeAssistant that allows a user to enter
    a meeting's date and time, calculate when it will end, and display the meeting time in a
    different time zone.

========== MEETING TIME ASSISTANT ==========

Enter meeting date (yyyy-MM-dd): 2026-11-10
Enter meeting time (HH:mm): 14:30
Enter meeting duration in minutes: 90

Choose an option:
1. Display meeting date and time
2. Display meeting end time
3. Display meeting time in another zone
4. Check whether the meeting is in the future
5. Exit

Enter choice: 2

Meeting starts at: 10 Nov 2026, 02:30 PM
Meeting ends at:   10 Nov 2026, 04:00 PM

*/

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Problem_2 {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("dd MMM yyyy");

        DateTimeFormatter timeFormatter =
                DateTimeFormatter.ofPattern("hh:mm a");

        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

        System.out.println("========== MEETING TIME ASSISTANT ==========");

        // Take meeting details from the user
        System.out.print("Enter meeting date (yyyy-MM-dd): ");
        LocalDate meetingDate = LocalDate.parse(sc.nextLine());

        System.out.print("Enter meeting time (HH:mm): ");
        LocalTime meetingTime = LocalTime.parse(sc.nextLine());

        System.out.print("Enter meeting duration in minutes: ");
        long durationMinutes = Long.parseLong(sc.nextLine());

        // Combine date and time
        LocalDateTime meetingStart =
                LocalDateTime.of(meetingDate, meetingTime);

        // Calculate meeting end time
        LocalDateTime meetingEnd =
                meetingStart.plusMinutes(durationMinutes);

        int choice;

        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Display meeting date and time");
            System.out.println("2. Display meeting end time");
            System.out.println("3. Display meeting time in another zone");
            System.out.println("4. Check whether the meeting is in the future");
            System.out.println("5. Exit");

            System.out.print("\nEnter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    System.out.println("\nMeeting date: "
                            + meetingDate.format(dateFormatter));

                    System.out.println("Meeting time: "
                            + meetingTime.format(timeFormatter));
                    break;

                case 2:
                    System.out.println("\nMeeting starts at: "
                            + meetingStart.format(dateTimeFormatter));

                    System.out.println("Meeting ends at:   "
                            + meetingEnd.format(dateTimeFormatter));
                    break;

                case 3:
                    System.out.print(
                            "Enter target time zone (e.g. Asia/Tokyo): ");

                    String zoneName = sc.nextLine();

                    try {
                        ZoneId targetZone = ZoneId.of(zoneName);

                        // Assume the meeting's original zone is India
                        ZoneId sourceZone = ZoneId.of("Asia/Kolkata");

                        ZonedDateTime meetingInSourceZone =
                                meetingStart.atZone(sourceZone);

                        ZonedDateTime meetingInTargetZone =
                                meetingInSourceZone.withZoneSameInstant(
                                        targetZone);

                        System.out.println("Meeting in "
                                + sourceZone + ": "
                                + meetingInSourceZone.format(
                                dateTimeFormatter));

                        System.out.println("Meeting in "
                                + targetZone + ": "
                                + meetingInTargetZone.format(
                                dateTimeFormatter));

                    } catch (Exception e) {
                        System.out.println(
                                "Invalid time zone. Please try again.");
                    }
                    break;

                case 4:
                    if (meetingStart.isAfter(LocalDateTime.now())) {
                        System.out.println(
                                "The meeting is in the future.");
                    } else {
                        System.out.println(
                                "The meeting is not in the future.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting MeetingTimeAssistant. Goodbye!");
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please select 1 to 5.");
            }

        } while (choice != 5);

        sc.close();
    }


}