package storage;

import model.Task;
import state.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private static final String TXT_FILE = "tasks.txt";
    public static void saveTxt(List<Task> tasks) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(TXT_FILE));

            for (Task t : tasks) {
                writer.println(t.getName() + "|" + t.getStateName());

                for (Task sub : t.getSubtasks()) {
                    writer.println("- " + sub.getName() + "|" + sub.getStateName());
                }

                writer.println("---");
            }

            writer.close();
            System.out.println("Tarefas salvas em TXT!");

        } catch (Exception e) {
            System.out.println("Erro ao salvar TXT: " + e.getMessage());
        }
    }

    public static List<Task> loadTxt() {
        List<Task> tasks = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(TXT_FILE));

            String line;
            Task currentTask = null;

            while ((line = reader.readLine()) != null) {

                if (line.equals("---")) {
                    currentTask = null;
                    continue;
                }

                if (line.startsWith("- ")) { // subtarefa
                    String[] parts = line.substring(2).split("\\|");
                    Task sub = new Task(parts[0]);
                    sub.setState(createState(parts[1]));
                    if (currentTask != null) {
                        currentTask.addSubtasks(sub);
                    }
                } else { // tarefa principal
                    String[] parts = line.split("\\|");
                    currentTask = new Task(parts[0]);
                    currentTask.setState(createState(parts[1]));
                    tasks.add(currentTask);
                }
            }

            reader.close();
            System.out.println("Tarefas carregadas do TXT!");

        } catch (FileNotFoundException e) {
            System.out.println("Nenhum arquivo TXT encontrado.");
        } catch (Exception e) {
            System.out.println("Erro ao carregar TXT: " + e.getMessage());
        }

        return tasks;
    }

    private static TaskState createState(String stateName) {

        if (stateName.equals("Pendente")) {
            return new PendenteState();
        }

        if (stateName.equals("Em andamento")) {
            return new EmAndamentoState();
        }

        if (stateName.equals("Concluída")) {
            return new ConcluidaState();
        }

        return new PendenteState();
    }
}
