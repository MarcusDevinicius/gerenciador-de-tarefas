package model;

import model.Task;

public class TaskFactory {

    public static Task createSimpleTask(String name){
        return new Task(name);
    }

    public static Task createTaskWithSubtasks(String name, String... subtasks){
        Task main = new Task(name);
        for (String s : subtasks){
            main.addSubtasks(new Task(s));
        }
        return main;
    }
}
