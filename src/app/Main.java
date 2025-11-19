package app;

import model.Task;
import model.TaskFactory;
import observer.ConsoleObserver;
import observer.Observer;
import storage.TaskStorage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks;
        tasks = TaskStorage.loadTxt();
        Observer consoleObserver = new ConsoleObserver();

        while(true){
            System.out.println("\n--- GERENCIADOR DE TAREFAS ---");
            System.out.println("1. Adicionar tarefa simples");
            System.out.println("2. Adicionar tarefa com subtarefas");
            System.out.println("3. Listar tarefas");
            System.out.println("4. Iniciar tarefa");
            System.out.println("5. Concluir tarefa");
            System.out.println("6. Remover tarefa");
            System.out.println("7. Listar subtarefas de uma tarefa");
            System.out.println("8. Iniciar subtarefa");
            System.out.println("9. Concluir subtarefa");
            System.out.println("0. Sair");
            System.out.println("Escolha: ");
            int opcao =sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1 -> {
                    System.out.println("Nome da tarefa: ");
                    String name = sc.nextLine();
                    Task t = TaskFactory.createSimpleTask(name);
                    t.addObserver(consoleObserver);
                    tasks.add(t);
                }
                case 2 -> {
                    System.out.println("Nome da tarefa principal: ");
                    String nome = sc.nextLine();
                    System.out.println("Subtarefas (separe por vírgula): ");
                    String[] subs = sc.nextLine().split(",");
                    Task t = TaskFactory.createTaskWithSubtasks(nome, subs);
                    t.addObserver(consoleObserver);
                    tasks.add(t);
                }
                case 3 -> {
                    System.out.println("\nTarefas:");
                    if (tasks.isEmpty()) System.out.println("Nenhuma tarefa.");
                    else tasks.forEach(System.out::println);
                }
                case 4 -> {
                    System.out.println("Índice da tarefa: ");
                    int i = sc.nextInt();
                    if(i >= 0 && i < tasks.size()) tasks.get(i).start();
                }
                case 5 -> {
                    System.out.println("Índice da tarefa: ");
                    int i = sc.nextInt();
                    if(i >= 0 && i < tasks.size()) tasks.get(i).complete();
                }
                case 6 -> {
                    System.out.println("Índice da tarefa para remover: ");
                    int i = sc.nextInt();
                    if(i >= 0 && i < tasks.size()) tasks.remove(i);
                }
                case 7 -> {
                    System.out.println("Índice da tarefa: ");
                    int i = sc.nextInt();
                    if(i >= 0 && i < tasks.size()){
                        System.out.println("Subtarefas: ");
                        tasks.get(i).listSubtasks();
                    }
                }
                case 8 -> {
                    System.out.println("Índice da tarefa: ");
                    int i = sc.nextInt();
                    if(i >= 0 && i < tasks.size()){
                        System.out.println("Índice da subtarefa: ");
                        int s = sc.nextInt();
                        tasks.get(i).startSubtask(s);
                    }
                }
                case 9 -> {
                    System.out.println("Índice da tarefa: ");
                    int i = sc.nextInt();
                    if(i >= 0 && i < tasks.size()){
                        System.out.println("Índice da subtarefa: ");
                        int s = sc.nextInt();
                        tasks.get(i).completeSubtask(s);
                    }
                }
                case 0 -> {
                    TaskStorage.saveTxt(tasks);
                    System.out.println("Encerrando...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
