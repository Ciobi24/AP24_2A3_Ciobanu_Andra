package lab5;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class ExportCommand implements Command {
    private Repository repository;

    public ExportCommand(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("Invalid number of arguments for export command");
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(args[0]), repository);
    }
}