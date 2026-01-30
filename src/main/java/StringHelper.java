public class StringHelper {
    String string;


    public StringHelper(String text) {
        this.string = text;
    }

    public String getCommand() {
        return this.string.split(" ")[0].toLowerCase();
    }

    public int getIndex() {
        return Integer.parseInt(string.split(" ")[1]);
    }


    public boolean isValidCommandFormat() {
        return string.split(" ").length > 1;
    }

    public boolean hasTaskDescription() {
        return string.split(this.getCommand()).length == 2;
    }

}
