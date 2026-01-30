package helper;

import exceptions.InvalidEventFormatException;

public class StringHelper {
    String string;

    public StringHelper(String text) {
        this.string = text.toLowerCase();
    }

    public String getCommand() {
        return this.string.split(" ")[0];
    }

    public int getIndex() {
        return Integer.parseInt(string.split(" ")[1]);
    }

    public String getTaskDetails() {
        return string.split(this.getCommand())[1];
    }

    public String[] getDeadLineDetails() {
        return string.split(this.getCommand())[1].split(" /by ");
    }

    public String[] getEventDetails() throws InvalidEventFormatException {
        String[] arr = new String[3];
        if (!this.isValidEventCommandFormat()) {
            throw new InvalidEventFormatException();
        }
        arr[0] = string.split(this.getCommand())[1].split(" /from ")[0];
        arr[1] = string.split(this.getCommand())[1].split(" /from ")[1];
        return arr;
    }

    public boolean isValidCommandFormat() {
        return string.split(" ").length > 1;
    }

    public boolean hasTaskDescription() {
        return string.split(this.getCommand()).length == 2;
    }

    public boolean isValidEventCommandFormat() {
        boolean a = string.split(this.getCommand())[1].split(" /from ").length == 2;
        boolean b = string.split(this.getCommand())[1].split(" /to ").length == 2;
        return a && b;
    }

}
