package task;

import exceptions.InvalidMarkingException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TaskList class to represent the task list.
 */
public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * Add task to task list.
     *
     * @param task Task to be added.
     */
    public static void add(Task task) {
        list.add(task);
    }

    /**
     * Mark the task of index in the task list as done.
     *
     * @param taskIndex index of task in task list.
     * @return Task that is marked
     * @throws InvalidMarkingException if marking operation on invalid task index.
     */
    public static Task markDone(int taskIndex) throws InvalidMarkingException {
        if (taskIndex > list.size()) throw new InvalidMarkingException();
        Task task = list.get(taskIndex - 1);
        task.setDone();
        return task;
    }

    /**
     * Mark the task of index in the task list as not done.
     *
     * @param taskIndex index of task in task list.
     * @return Task that is marked
     * @throws InvalidMarkingException if marking operation on invalid task index.
     */
    public static Task markUndone(int taskIndex) throws InvalidMarkingException {
        if (taskIndex > list.size()) throw new InvalidMarkingException();
        Task task = list.get(taskIndex - 1);
        task.setNotDone();
        return task;
    }

    /**
     * Delete the task of index in the task list as not done.
     *
     * @param taskIndex index of task in task list.
     * @return Task that is deleted.
     * @throws InvalidMarkingException if delete operation on invalid task index.
     */
    public static Task delete(int taskIndex) throws InvalidMarkingException {
        if (taskIndex > list.size()) throw new InvalidMarkingException();
        Task task = list.get(taskIndex - 1);
        list.remove(taskIndex - 1);
        return task;

    }


    /**
     * Find and returns the tasks in task list that are relevant to input keyword.
     *
     * @param keyWord string of keyword to be matched to tasks.
     * @return String representation of relevant tasks.
     */
    public static String find(String keyWord) {
        StringBuilder output = new StringBuilder("No Task Found");
        boolean isFound = false;
        int count = 1;

        for (Task task : list) {
            String taskDetail = task.getTaskName();
            if (taskDetail.contains(keyWord)) {
                if (!isFound) {
                    output = new StringBuilder(String.valueOf(count) + task.toString() + "\n");
                    isFound = true;
                    continue;
                }
                output.append(String.valueOf(count)).append(task.toString()).append("\n");
            }
        }
        return output.toString();
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
