package state;

import model.Task;
import state.ConcluidaState;


public class EmAndamentoState implements TaskState {

    public void start(Task task){
        System.out.println("Tarefa já está em andamento.");
    }

    public void complete(Task task){
        task.setState(new ConcluidaState());
        task.notifyObservers("Tarefa concluída!");
    }

    public String getName(){
        return "Em andamento";
    }
}
