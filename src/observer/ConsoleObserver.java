package observer;

import model.Task;

public class ConsoleObserver implements Observer {
    public void update(Task task, String mensagem){
        System.out.println("[Notificação]" + task.getName());
    }
}
