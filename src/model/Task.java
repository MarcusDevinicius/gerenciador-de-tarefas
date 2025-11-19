package model;

import observer.Observer;
import state.PendenteState;
import state.TaskState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Task implements Serializable {
    private String name;
    private TaskState state;
    private List<Observer> observers;
    private List<Task> subtasks;

    public Task(String name){
        this.name = name;
        this.state = new PendenteState();
        this.observers = new ArrayList<>();
        this.subtasks = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public String getStateName(){
        return state.getName();
    }

    public void setState(TaskState state){
        this.state = state;
    }

    public void start(){
        state.start(this);
    }
    public void complete(){
        state.complete(this);
    }

    public void addSubtasks(Task subtask){
        subtasks.add(subtask);
    }

    public void listSubtasks(){
        if(subtasks.isEmpty()){
            System.out.println("Nenhuma subtarefa.");
            return;
        }

        for(int i = 0; i < subtasks.size(); i++){
            System.out.println(i + " - " + subtasks.get(i));
        }
    }

    public void startSubtask(int index){
        if(index < 0 || index >= subtasks.size()){
            System.out.println("Índice inválido de subtarefa.");
            return;
        }
        subtasks.get(index).start();
        System.out.println("Subtarefa em andamento!");
    }

    public void completeSubtask(int index){
        if(index < 0 || index >= subtasks.size()){
            System.out.println("Índice inválido de subtarefa.");
            return;
        }
        subtasks.get(index).complete();
        System.out.println("Subtarefa concluída!");
    }

    public List<Task> getSubtasks(){
        return subtasks;
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }
    public void notifyObservers(String mensagem) {
        for (Observer o : observers) {
            o.update(this, mensagem);
        }
    }

        public String toString(){
            return name + " (" + getStateName() + ")";
        }
    }
