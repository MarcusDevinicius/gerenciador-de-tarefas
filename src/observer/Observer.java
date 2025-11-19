package observer;

import model.Task;

public interface Observer {
    void update(Task task, String mensagem);
}
