package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import exceptions.InvalidMarkingException;

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

    public static ArrayList<Task> getListCopy() {
        return new ArrayList<>(list);
    }

    /**
     * Mark the task at given index as done.
     *
     * @param taskIndex Index of task in task list.
     * @return Task The Task that had been marked.
     * @throws InvalidMarkingException If marking operation on invalid task index.
     */
    public static Task markDone(int taskIndex) throws InvalidMarkingException {
        if (!isValidIndex(taskIndex)) {
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
        if (!isValidIndex(taskIndex)) {
            throw new InvalidMarkingException();
        }
        Task task = list.get(taskIndex - 1);
        task.setNotDone();
        return task;
    }

    public static boolean contain(Task task) {
        return list.contains(task);
    }

    /**
     * Delete the task of index in the task list.
     *
     * @param taskIndex Index of task in task list.
     * @return Task The Task that was deleted.
     * @throws InvalidMarkingException If delete operation on invalid task index.
     */
    public static Task delete(int taskIndex) throws InvalidMarkingException {
        if (!isValidIndex(taskIndex)) {
            throw new InvalidMarkingException();
        }
        Task task = list.get(taskIndex - 1);
        list.remove(taskIndex - 1);
        return task;

    }

    /**
     * Checks if the index input is valid for the current task list.
     *
     * @param index Integer input.
     * @return true if index is valid.
     */
    public static boolean isValidIndex(int index) {
        return index > 0 && index <= list.size();
    }

    /**
     * Find and return tasks that contain the given keyword.
     *
     * @param keyword String of keyword to search for.
     * @return String Representation of relevant tasks.
     */
    public static String find(String keyword) {
        StringBuilder output = new StringBuilder("No Task Found");
        boolean isFound = false;
        int count = 1;

        for (Task task : list) {
            String taskDetail = task.getTaskName();
            if (taskDetail.contains(keyword)) {
                if (!isFound) {
                    output = new StringBuilder(String.valueOf(count) + ". " + task.toString() + "\n");
                    isFound = true;
                    continue;
                }
                output.append(String.valueOf(count)).append(". ").append(task.toString()).append("\n");
            }
            count+= 1;
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
    public static String getTaskList() {
        int count = 1;
        StringBuilder output = new StringBuilder("Tasks in List: \n");

        if (list.isEmpty()) {
            return "Nothing to see here...";
        }

        for (Task x : list) {
            output.append(String.valueOf(count)).append(". ").append(x.toString()).append("\n");
            count++;
        }

        return output.toString();
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


    public static ArrayList<Task> getDupes() {
        HashSet<String> set = new HashSet<>();
        ArrayList<Task> result = new ArrayList<>();
        for (Task x : list) {
            String taskString = x.toString();

            if (set.contains(taskString)) {
                result.add(x);
            }
            set.add(taskString);
        }
        return result;
    }

    /**
     * Method to remove duplicate Tasks in Task List.
     *
     * @param dupeTasks ArrayList of Duplicated Tasks.
     */
    public static boolean removeDupes(ArrayList<Task> dupeTasks) {
        if (dupeTasks.isEmpty()) {
            return false;
        }

        for (Task x : dupeTasks) {
            list.remove(x);
        }

        return true;
    }

    public static String getConflict() {
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder output = new StringBuilder("No dupes \n");
        HashSet<Integer> set = new HashSet<>();

        int count = 1;
        for (Task x : list) {
            if (map.containsKey(x.getTaskName())) {
                set.add(count);
                set.add(map.get(x.getTaskName()));
            } else {
                map.put(x.getTaskName(), count);
            }
            count += 1;
        }

        if (!set.isEmpty()) {
            output = new StringBuilder("Dupes: ");
            for (int x : set) {
                output.append(String.valueOf(x)).append(" ");
            }
            output.append("\n");
        }

        return output.toString();
    }


    /**
     * Reset task list, For testing purposes only.
     */
    public static void reset() {
        list = new ArrayList<>();
    }

}
