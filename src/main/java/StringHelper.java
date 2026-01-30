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

    public String[] getEventDetails() {
        String[] arr = new String[3];
        arr[0] = string.split(this.getCommand())[1].split(" /from ")[0];
        arr[1] = string.split(this.getCommand())[1].split(" /from ")[0].split(" /to ")[0];
        arr[2] = string.split(this.getCommand())[1].split(" /from ")[0].split(" /to ")[1];
        return arr;
    }



    public boolean isValidCommandFormat() {
        return string.split(" ").length > 1;
    }

    public boolean hasTaskDescription() {
        return string.split(this.getCommand()).length == 2;
    }



}
