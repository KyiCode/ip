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
                String[] deadLineDetails = text.split(" \\|\\| Deadline: ");
                if (deadLineDetails.length != 2) {
                    throw new NullDateException();
                }
                Task task = new DeadLineTask(deadLineDetails[0], deadLineDetails[1], 1);
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

    public static void add(Task task) {
        list.add(task);
    }


    public static Task markDone(int taskIndex) throws InvalidMarkingException, IOException {
        if (taskIndex > list.size()) throw new InvalidMarkingException();
        Task task = list.get(taskIndex - 1);
        task.setDone();
        System.out.println("Nice! I've marked task index " + taskIndex + " as done:");
        System.out.println(task.toString());
        return task;
    }

    public static Task markUndone(int taskIndex) throws InvalidMarkingException, IOException {
        if (taskIndex > list.size()) throw new InvalidMarkingException();
        Task task = list.get(taskIndex - 1);
        task.setNotDone();
        System.out.println("I've marked task index " + taskIndex + " as  undone:");
        System.out.println(task.toString());
        return task;
    }

    public static void delete(int taskIndex) throws InvalidMarkingException, IOException {
        if (taskIndex > list.size()) throw new InvalidMarkingException();
        Task task = list.get(taskIndex - 1);
        list.remove(taskIndex - 1);
        System.out.println("I've eaten this task:");
        System.out.println(task.toString());
        System.out.println(TaskList.getListSize() + " tasks in list.");
        FileOperator.delOperation(taskIndex);
    }

    public static int getListSize() {
        return list.size();
    }

    public static void getTaskList() {
        int count = 1;
        System.out.println("Tasks in List: ");

        if (list.isEmpty()) System.out.println("Nothing to see here...");

        for (Task x : list) {
            System.out.println(count + ". " + x.toString());
            count++;
        }
    }

    public static int getTaskIndex(Task task) {
        return list.indexOf(task);
    }



}
