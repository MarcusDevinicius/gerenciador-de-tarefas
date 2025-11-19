package state;

import model.Task;

public interface TaskState {
    public void start(Task task);
    public void complete(Task task);
    String getName();
    }
