import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class ToDo extends Task {
    public ToDo(String taskName) throws InvalidCommandException, IOException {
        super(taskName);
        System.out.println("Added: " + this.toString());
        System.out.println(TaskList.getListSize() + " tasks in list.");
    }

    public ToDo(String taskName, int mode) throws InvalidCommandException, IOException {
        super(taskName);
        TaskList.add(this);
    }
}


class Task {
    private String taskName;
    private boolean done = false;

    public Task (String taskName) throws NullTaskDescriptionException, IOException {
        this.taskName = taskName;
        if (this.taskName == null) {
            throw new NullTaskDescriptionException();
        }
    }

    public void setDone() {
        this.done = true;
    }

    public void setNotDone() {
        this.done = false;
    }

//
//    public static Task markDone(int taskIndex) throws InvalidMarkingException, IOException {
//        if (taskIndex > list.size()) throw new InvalidMarkingException();
//        Task task = list.get(taskIndex - 1);
//        task.done = true;
//        System.out.println("Nice! I've marked this task as done:");
//        System.out.println(task.toString());
//        return task;
//    }
//
//    public static Task markUndone(int taskIndex) throws InvalidMarkingException, IOException {
//        if (taskIndex > list.size()) throw new InvalidMarkingException();
//        Task task = list.get(taskIndex - 1);
//        task.done = false;
//        System.out.println("I've marked this task as not done yet:");
//        System.out.println(task.toString());
//        return task;
//    }
//
//    public static void delete(int taskIndex) throws InvalidMarkingException, IOException {
//        if (taskIndex > list.size()) throw new InvalidMarkingException();
//        Task task = list.get(taskIndex - 1);
//        list.remove(taskIndex - 1);
//        System.out.println("I've eaten this task:");
//        System.out.println(task.toString());
//        System.out.println(Task.getListSize() + " tasks in list.");
//        FileOperator.delOperation(taskIndex);
//    }
//
//    public static int getListSize() {
//        return list.size();
//    }
//
//    public static void getTaskList() {
//        int count = 1;
//        System.out.println("Tasks in List: ");
//
//        if (list.isEmpty()) System.out.println("Nothing to see here...");
//
//        for (Task x : list) {
//            System.out.println(count + ". " + x.toString());
//            count++;
//        }
//    }
//
    public boolean status() {
        return this.done;
    }
//
    public String getTaskName() {
        return this.taskName;
    }
//
//    public int getTaskIndex() {
//        return list.indexOf(this);
//    }

    @Override
    public String toString() {
        String doneStatus = "[T][ ] ";
        if (this.done) doneStatus = "[T][X] ";
        return doneStatus + this.taskName;
    }
}