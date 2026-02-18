package fileoperator;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import exceptions.InvalidCommandException;
import exceptions.InvalidEventFormatException;
import exceptions.NullDateException;
import helper.StringHelper;
import meow.Parser;
import task.DeadLine;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

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
            StringHelper stringHelper = new StringHelper(sc.nextLine());
            boolean isDone = stringHelper.checkLoadedTaskStatus();

            Task task = loadTask(stringHelper, isDone);

            assert TaskList.contain(task) : "task not in task list or task not updated";
            assert Storage.inFile(filePath, task) : "task not in storage file";
        }
        sc.close();
    }

    /**
     * To handle the logic of separating and calling different Task constructors.
     *
     * @param stringHelper String Helper of StorageFile string line.
     * @param isDone Completion status of task.
     * @return the Task instance created.
     * @throws InvalidCommandException if string representation of task is invalid.
     * @throws IOException if ToDo task is invalid.
     */
    public static Task loadTask(StringHelper stringHelper, boolean isDone) throws InvalidCommandException, IOException {
        String taskDetails = stringHelper.getLoadedTaskDetails();
        if (stringHelper.isLoadedTodo()) {
            return loadToDo(taskDetails, isDone);
        } else if (stringHelper.isLoadedDeadline()) {
            return loadDeadLine(taskDetails, isDone);
        } else if (stringHelper.isLoadedEvent()) {
            return loadEvent(taskDetails, isDone);
        } else {
            throw new InvalidCommandException("File may be corrupted, check File!");
        }
    }

    /**
     * handler to load a Todo Task.
     *
     * @param taskDetails task description.
     * @param isDone completion status of task.
     * @return a Todo task instance.
     * @throws InvalidCommandException if string representation of task is invalid.
     * @throws IOException if ToDo task is invalid.
     */
    public static Task loadToDo(String taskDetails, boolean isDone) throws InvalidCommandException, IOException {
        return new ToDo(taskDetails, isDone);
    }

    /**
     * handler to load deadline.
     *
     * @param taskDetails task description.
     * @param isDone completion status of task.
     * @return a Deadline instance.
     * @throws InvalidCommandException if string representation of task is invalid.
     * @throws IOException if deadline is invalid.
     */
    public static Task loadDeadLine(String taskDetails, boolean isDone) throws InvalidCommandException, IOException {
        String[] deadLineDetails = taskDetails.split(" \\|\\| Deadline: ");
        if (deadLineDetails.length != 2) {
            throw new NullDateException();
        }
        return new DeadLine(deadLineDetails[0], deadLineDetails[1], isDone);
    }

    /**
     * handler to load Event.
     *
     * @param taskDetails task description.
     * @param isDone completion status of task.
     * @return an Event instance.
     * @throws InvalidCommandException if string representation of task is invalid.
     * @throws IOException if event is invalid.
     */
    public static Task loadEvent(String taskDetails, boolean isDone) throws InvalidCommandException, IOException {
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
        boolean hasResult = false;
        while (sc.hasNext()) {
            String text = sc.nextLine();
            if (text.contains(task.toString())) {
                hasResult = true;
                break;
            }
        }
        sc.close();
        return hasResult;
    }
}
