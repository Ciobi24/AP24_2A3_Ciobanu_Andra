package lab5;

import lombok.AllArgsConstructor;

import java.awt.Desktop;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ViewCommand implements Command {
    Repository repository;
    ViewCommand(Repository repository) throws Exception {
            this.repository = new Repository(repository.getDirectory());
    }
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("Invalid number of arguments for view command");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(repository.getDirectory());
        sb.append("/");
        sb.append(args[0]);
        Path path = Paths.get(sb.toString());
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("File does not exist: " + sb.toString());
        }else{
            Desktop.getDesktop().open(path.toFile());
        }
    }
}