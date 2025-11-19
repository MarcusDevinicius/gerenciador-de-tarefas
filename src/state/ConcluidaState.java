package state;

import model.Task;


public class ConcluidaState implements TaskState {
    public void start(Task tarefa){
        System.out.println("Tarefa já foi concluída");
    }

    public void complete(Task task){
        System.out.println("Tarefa já está concluída.");
    }

    public String getName(){
        return "Concluída";
    }
}
