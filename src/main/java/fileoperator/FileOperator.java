package fileoperator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import task.DeadLine;
import task.Task;
import task.TaskList;
import task.ToDo;
import task.Event;

/**
 * Class FileOperator to perform changes to current storage file.
 */
public class FileOperator {

    /**
     * Appends new Task representation to the storage file.
     *
     * @param filePath filepath of storage file.
     * @param task task to be added.
     * @throws IOException if filepath is invalid.
     */
    public static void append(Path filePath, Task task) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(filePath), true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }

    // implement bug check
    /**
     * Updates the storage file after any edits to tasks already in task list.
     *
     * @param filePath filepath of storage file.
     * @param task task to be edited.
     * @throws IOException if filepath is invalid.
     */
    public static void markOperation(Path filePath, Task task) throws IOException {
        Scanner sc = new Scanner(new FileReader(String.valueOf(filePath)));
        //Overwrite mode
        FileWriter fw = new FileWriter("ip/data/temp.txt");

        int count = 0;
        while (sc.hasNext()) {
            String stringLine = sc.nextLine();
            if (count == TaskList.getTaskIndex(task)) {
                stringLine = task.toString();
            }
            count+= 1;
            fw.write(stringLine + System.lineSeparator());
        }
        sc.close();
        fw.close();

        Files.delete(filePath);
        Files.move(Paths.get("ip/data/temp.txt"), filePath);
    }

    /**
     * Reads and output the Storage File.
     *
     * @param filePath filepath of storage file.
     * @throws FileNotFoundException if storage file not Found.
     */
    public static void iterateFile(Path filePath) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("ip/data/meow.txt"));
        while (sc.hasNext()) {
            String textLine = sc.nextLine();
            System.out.println(textLine);
        }
    }

    /**
     * Updates the storage file after any deletion to tasks in task list.
     *
     * @param index task to be deleted.
     * @throws IOException if filepath is invalid.
     */
    public static void delOperation(int index) throws IOException {
        Scanner sc = new Scanner(new FileReader("ip/data/meow.txt"));
        FileWriter fw = new FileWriter("ip/data/temp.txt", true);
        int count = 0;

        while (sc.hasNext()) {
            String stringLine = sc.nextLine();
            if (count + 1 == index) {
                count+= 1;
                continue;
            }
            count+= 1;
            fw.write(stringLine + System.lineSeparator());
        }
        sc.close();
        fw.close();

        Files.delete(Paths.get("ip/data/meow.txt"));
        Files.move(Paths.get("ip/data/temp.txt"), Paths.get("ip/data/meow.txt"));
    }

}
