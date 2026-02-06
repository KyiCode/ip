package task;

import exceptions.InvalidMarkingException;

import java.util.ArrayList;

/**
 * TaskList class to represent a list of tasks.
 */
public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * Add a task to task list.
     *
     * @param task Task to be added.
     */
    public static void add(Task task) {
        list.add(task);
    }

    /**
     * Mark the task at given index as done.
     *
     * @param taskIndex Index of task in task list.
     * @return Task The Task that had been marked.
     * @throws InvalidMarkingException If marking operation on invalid task index.
     */
    public static Task markDone(int taskIndex) throws InvalidMarkingException {
        if (taskIndex > list.size()) {
            throw new InvalidMarkingException();
        }
        Task task = list.get(taskIndex - 1);
        task.setDone();
        return task;
    }

    /**
     * Mark the task of index in the task list as not done.
     *
     * @param taskIndex Index of task in task list.
     * @return Task The Task that is marked.
     * @throws InvalidMarkingException If marking operation on invalid task index.
     */
    public static Task markUndone(int taskIndex) throws InvalidMarkingException {
        if (taskIndex > list.size()) {
            throw new InvalidMarkingException();
        }
        Task task = list.get(taskIndex - 1);
        task.setNotDone();
        return task;
    }

    /**
     * Delete the task of index in the task list.
     *
     * @param taskIndex Index of task in task list.
     * @return Task The Task that was deleted.
     * @throws InvalidMarkingException If delete operation on invalid task index.
     */
    public static Task delete(int taskIndex) throws InvalidMarkingException {
        if (taskIndex > list.size()) {
            throw new InvalidMarkingException();
        }
        Task task = list.get(taskIndex - 1);
        list.remove(taskIndex - 1);
        return task;

    }

    /**
     * Find and return tasks that contain the given keyword.
     *
     * @param keyWord String of keyword to search for.
     * @return String Representation of relevant tasks.
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

    /**
     * Returns the size of the task list.
     *
     * @return Integer size.
     */
    public static int getListSize() {
        return list.size();
    }

    /**
     * Outputs the string representation of all contents in the list.
     */
    public static void getTaskList() {
        int count = 1;
        System.out.println("Tasks in List: ");

        if (list.isEmpty()) {
            System.out.println("Nothing to see here...");
        }

        for (Task x : list) {
            System.out.println(count + ". " + x.toString());
            count++;
        }
    }

    /**
     * Returns the index of the specified task in the list.
     *
     * @param task Task to be queried.
     * @return Integer index of task.
     */
    public static int getTaskIndex(Task task) {
        return list.indexOf(task);
    }

    /**
     * Reset task list, For testing purposes only.
     */
    public static void reset() {
        list = new ArrayList<>();
    }

}
