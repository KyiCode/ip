import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {
    public static void load(Path filePath, Parser brain) throws IOException, InvalidCommandException {
        Scanner sc = new Scanner(new FileReader(String.valueOf(filePath)));
        while (sc.hasNext()) {
            String text = sc.nextLine();

            if (text.startsWith("[T]")) {
                text = text.split("] ")[1];
                Task task = new ToDo(text, 1);
            }
            if (text.startsWith("[D]")) {
                text = text.split("] ")[1];
                String[] deadLineDetails = text.split(" \\|\\| Deadline: ");
                if (deadLineDetails.length != 2) {
                    throw new NullDateException();
                }
                Task task = new DeadLine(deadLineDetails[0], deadLineDetails[1], 1);
            }
            if (text.startsWith("[E]")) {
                text = text.split("] ")[1];
                String[] eventDetails = text.split(" \\|\\| From: ");
                if (eventDetails.length != 2) {
                    throw new InvalidEventFormatException();
                }
                String[] eventDateTimeDetails = eventDetails[1].split(" To: ");
                if (eventDateTimeDetails.length != 2) {
                    throw new InvalidEventFormatException();
                }
                Task task = new Event(eventDetails[0], eventDateTimeDetails[0], eventDateTimeDetails[1], 1);
            }
        }
        sc.close();
    }
}
