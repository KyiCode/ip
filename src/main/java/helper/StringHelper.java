package helper;

import exceptions.InvalidEventFormatException;


/**
 * StringHelper Class to process input Strings, then return relevant information.
 */
public class StringHelper {
    String string;

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
     * @return true if command is valid.
     */
    public boolean isValidCommandFormat() {
        return string.split(" ").length > 1;
    }

    /**
     * Check if String has Task Details.
     *
     * @return true if input contains task details.
     */
    public boolean hasTaskDescription() {
        return string.split(this.getCommand()).length == 2;
    }

    /**
     * Check if Event input is in valid format.
     *
     * @return true if Input is in correct format.
     */
    public boolean isValidEventCommandFormat() {
        boolean a = string.split(this.getCommand())[1].split(" /from ").length == 2;
        boolean b = string.split(this.getCommand())[1].split(" /to ").length == 2;
        return a && b;
    }

}
