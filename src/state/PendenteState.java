package state;

import model.Task;
import state.EmAndamentoState;


public class PendenteState implements TaskState {
    @Override
    public void start(Task task){
        task.setState(new EmAndamentoState());
        task.notifyObservers("Tarefa Iniciada!");
    }
    @Override
    public void complete(Task tarefa){
        System.out.println("Não é possível concluir uma tarefa que ainda não foi iniciada!");
    }
    @Override
    public String getName(){
        return "Pendente";
    }
}
