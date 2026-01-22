import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Meow {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        String intro =
                "____________________________________________________________\n"
                + "Hello! I'm Meow\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        String outro =
                "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(intro);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String text = sc.nextLine();
            if (text.equals("bye")) {
                System.out.println(outro);
                break;
            }

            if (text.equals("list")) {
                int count = 1;
                for (String x : list) {
                    System.out.println(count + ". " + x);
                    count++;
                }
                continue;
            }
            list.add(text);
            System.out.println("added: " + text);
        }

    }
    class Task {

    }
}
