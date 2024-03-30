package lab5;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandReader {
    private Map<String, Command> commands = new HashMap<>();

    public CommandReader(Repository repository) throws Exception{

        commands.put("view", new ViewCommand(repository));
        commands.put("report", new ReportCommand(repository));
        commands.put("export", new ExportCommand(repository));
    }

    public void readAndExecute() {
        System.out.println("Available commands: " + commands.keySet()+ " exit");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String commandName = parts[0];
            if(commandName.equals("exit")){
                break;
            }
            String[] args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, parts.length - 1);
            Command command = commands.get(commandName);
            if (command == null) {
                System.out.println("Invalid command: " + commandName);
                continue;
            }
            try {
                command.execute(args);
            } catch (Exception e) {
                System.out.println("Failed to execute command: " + e.getMessage());
            }
        }
    }
}
