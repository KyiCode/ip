package fileoperator;

import exceptions.InvalidCommandException;
import exceptions.InvalidEventFormatException;
import exceptions.NullDateException;
import meow.Parser;
import task.DeadLine;
import task.Event;
import task.Task;
import task.ToDo;
import task.TaskList;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Storage class to perform tasks on Storage file.
 */
public class Storage {

    /**
     * Loads and initialise task strings from storage task list.
     *
     * @param filePath file path of storage file.
     * @param brain current processing instance.
     * @throws IOException if filePath of storage file is invalid.
     * @throws InvalidCommandException if file is corrupted and contains task representations of invalid format.
     */
    public static void load(Path filePath, Parser brain) throws IOException, InvalidCommandException {
        Scanner sc = new Scanner(new FileReader(String.valueOf(filePath)));
        while (sc.hasNext()) {
            String text = sc.nextLine();
            Task task = null;
            if (text.startsWith("[T]")) {
                boolean isDone = text.startsWith("[T][X]");
                task = loadToDo(text, isDone);
            } else if (text.startsWith("[D]")) {
                boolean isDone = text.startsWith("[D][X]");
                task = loadDeadLine(text, isDone);
            } else if (text.startsWith("[E]")) {
                boolean isDone = text.startsWith("[E][X]");
                task = loadEvent(text, isDone);
            } else {
                throw new InvalidCommandException("File may be corrupted, check File!");
            }

            assert TaskList.contain(task) : "task not in task list or task not updated";
            assert Storage.inFile(filePath, task) : "task not in storage file";

        }
        sc.close();
    }

    public static Task loadToDo(String taskDetails, boolean isDone) throws InvalidCommandException, IOException {
        taskDetails = taskDetails.split("] ")[1];
        return new ToDo(taskDetails, isDone);
    }

    public static Task loadDeadLine(String taskDetails, boolean isDone) throws InvalidCommandException, IOException {
        taskDetails = taskDetails.split("] ")[1];
        String[] deadLineDetails = taskDetails.split(" \\|\\| Deadline: ");
        if (deadLineDetails.length != 2) {
            throw new NullDateException();
        }
        return new DeadLine(deadLineDetails[0], deadLineDetails[1], isDone);
    }


    public static Task loadEvent (String taskDetails, boolean isDone) throws InvalidCommandException, IOException {
        taskDetails = taskDetails.split("] ")[1];
        String[] eventDetails = taskDetails.split(" \\|\\| From: ");
        if (eventDetails.length != 2) {
            throw new InvalidEventFormatException();
        }

        String[] eventDateTimeDetails = eventDetails[1].split(" To: ");
        if (eventDateTimeDetails.length != 2) {
            throw new InvalidEventFormatException();
        }
        return new Event(eventDetails[0], eventDateTimeDetails[0], eventDateTimeDetails[1], isDone);
    }

    /**
     * Finds the Task in storage file and return a boolean as result.
     *
     * @param filePath path of storage file.
     * @param task task to look for.
     * @return True if task exists in Storage file.
     * @throws IOException if file cannot be found.
     */
    public static boolean inFile(Path filePath, Task task) throws IOException {
        Scanner sc = new Scanner(new FileReader(String.valueOf(filePath)));
        boolean result = false;
        while (sc.hasNext()) {
            String text = sc.nextLine();
            if (text.contains(task.toString())) {
                result = true;
                break;
            }
        }
        sc.close();
        return result;
    }
}
