package task;

import exceptions.InvalidMarkingException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    public static void add(Task task) {
        list.add(task);
    }

    public static Task markDone(int taskIndex) throws InvalidMarkingException, IOException {
        if (taskIndex > list.size()) throw new InvalidMarkingException();
        Task task = list.get(taskIndex - 1);
        task.setDone();
        return task;
    }

    public static Task markUndone(int taskIndex) throws InvalidMarkingException, IOException {
        if (taskIndex > list.size()) throw new InvalidMarkingException();
        Task task = list.get(taskIndex - 1);
        task.setNotDone();
        return task;
    }

    public static Task delete(int taskIndex) throws InvalidMarkingException, IOException {
        if (taskIndex > list.size()) throw new InvalidMarkingException();
        Task task = list.get(taskIndex - 1);
        list.remove(taskIndex - 1);
        return task;

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
