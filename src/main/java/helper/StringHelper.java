package helper;

import exceptions.InvalidEventFormatException;


/**
 * StringHelper Class to process input Strings, then return relevant information.
 */
public class StringHelper {
    private String string;

    /**
     * Constructs a StringHelper instance, then normalising the input String.
     *
     * @param text Command Input of user.
     */
    public StringHelper(String text) {
        this.string = text.toLowerCase();
    }

    /**
     * Returns the command of the User Input.
     *
     * @return String Command segment of String
     */
    public String getCommand() {
        return this.string.split(" ")[0];
    }

    /**
     * Returns the Integer index of Task User commanded on.
     *
     * @return Integer of Task from User Input.
     */
    public int getIndex() {
        return Integer.parseInt(string.split(" ")[1]);
    }

    /**
     * Returns the string of the current StringHelper instance.
     *
     * @return String of StringHelper Instance.
     */
    public String getString() {
        return this.string;
    }


    /**
     * Retrieve Task Description of String from User Input.
     *
     * @return String Task Description.
     */
    public String getTaskDetails() {
        return string.split(this.getCommand() + " ")[1];
    }

    /**
     * Retrieve DeadLine details of DeadLine Task String.
     *
     * @return String DeadLine.
     */
    public String[] getDeadLineDetails() {
        return string.split(this.getCommand() + " ")[1].split(" /by ");
    }

    /**
     * Retrieve Event details of Event Task String.
     *
     * @return String Array of Event Start date and Event End date.
     * @throws InvalidEventFormatException if input is in wrong format.
     */
    public String[] getEventDetails() throws InvalidEventFormatException {
        String[] arr = new String[3];
        if (!this.isValidEventCommandFormat()) {
            throw new InvalidEventFormatException();
        }
        arr[0] = string.split(this.getCommand() + " ")[1].split(" /from ")[0];
        arr[1] = string.split(this.getCommand())[1].split(" /from ")[1];
        return arr;
    }

    /**
     * Check if command is Valid.
     *
     * @return true If command is valid.
     */
    public boolean isValidCommandFormat() {
        return string.split(" ").length > 1;
    }

    /**
     * Check if String has Task Details.
     *
     * @return true If input contains task details.
     */
    public boolean hasTaskDescription() {
        return string.split(this.getCommand()).length == 2;
    }

    /**
     * Check if Event input is in valid format.
     *
     * @return true If Input is in correct format.
     */
    public boolean isValidEventCommandFormat() {
        boolean isValidFromFormat = string.split(this.getCommand())[1].split(" /from ").length == 2;
        boolean isValidToFormat = string.split(this.getCommand())[1].split(" /to ").length == 2;
        return isValidFromFormat && isValidToFormat;
    }


    /**
     * Used by Storage, to check loaded Tasks' completion status.
     *
     * @return true if loaded task is completed.
     */
    public boolean checkLoadedTaskStatus() {
        boolean isCompletedToDo = string.startsWith("[t][x]");
        boolean isCompletedDeadline = string.startsWith("[d][x]");
        boolean isCompletedEvent = string.startsWith("[e][x]");
        return isCompletedToDo || isCompletedDeadline || isCompletedEvent;
    }


    /**
     * Filter String to get the Task Description.
     *
     * @return String representation of task.
     */
    public String getLoadedTaskDetails() {
        return string.split("] ")[1];
    }

    /**
     * Checks if Loaded Task String is a ToDo task.
     *
     * @return true if ToDo Task.
     */
    public boolean isLoadedTodo() {
        return string.startsWith("[t]");
    }

    /**
     * Checks if Loaded Task String is a DeadLine task.
     *
     * @return true if DeadLine Task.
     */
    public boolean isLoadedDeadline() {
        return string.startsWith("[d]");
    }

    /**
     * Checks if Loaded Task String is an Event task.
     *
     * @return true if Event Task.
     */
    public boolean isLoadedEvent() {
        return string.startsWith("[e]");
    }

}
