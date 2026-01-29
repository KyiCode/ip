import com.sun.source.util.TaskEvent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private static ArrayList<Task> list;

    public static void load(Path filePath, Brain brain) throws IOException, InvalidCommandException {
        list = new ArrayList<>();
        Scanner sc = new Scanner(new FileReader(String.valueOf(filePath)));
        while (sc.hasNext()) {
            String text = sc.nextLine();

            if (text.startsWith("[T]")) {
                text = text.split("] ")[1];
                Task task = new ToDo(text, 1);
            }
            if (text.startsWith("[D]")) {
                text = text.split("] ")[1];
                String[] textArr = text.split(" \\(by: ");
                String taskText = textArr[0];
                String deadline = textArr[1].split("\\)")[0];
                Task task = new Deadline(taskText, deadline, 1);
            }
            if (text.startsWith("[E]")) {
                text = text.split("] ")[1];
            }


        }

    }





}
