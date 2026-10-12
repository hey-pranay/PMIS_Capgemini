/*

Problem Statement
A media player processes different types of media. Each media item has a title and a type. The player
must display a message describing how each item is played.
Complete the function playMedia that takes a media type and a title and returns the appropriate
playback message.
Function Description
Implement playMedia(String mediaType, String title).
Rules:
• If mediaType is "Song", return Playing song: {title}.
• If mediaType is "Video", return Playing video: {title}.
• If mediaType is "podcast", return Playing podcast: {title}.
• For any other media type, return Unknown media type.
Media type matching is case-sensitive.
Input Format
The first line contains the media type.
The second line contains the title.
Constraints
The title contains between 1 and 100 characters.
The media type is a single word.
Output Format
Print the message returned by playmedia.
Sample Input
Song
Imagine
Sample Output
Playing song: Imagine

*/

package problems;

import java.util.Scanner;

public class third {

    static String playMedia(String mediaType, String title) {

        if (mediaType.equals("Song")) {
            return "Playing song: " + title;
        } else if (mediaType.equals("Video")) {
            return "Playing video: " + title;
        } else if (mediaType.equals("podcast")) {
            return "Playing podcast: " + title;
        } else {
            return "Unknown media type";
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String mediaType = sc.nextLine();
        String title = sc.nextLine();

        System.out.println(playMedia(mediaType, title));

        sc.close();
    }
}
