package edu.austral.ingsis.clifford.commands;

import java.nio.file.FileSystemException;
import java.util.Optional;

public class RmRunner implements CommandRunner {
    @Override
    public boolean supports(String command) {
        return command.trim().startsWith("rm");
    }

    @Override
    public Optional<Command> parse(String[] input) throws FileSystemException {
        boolean recursive = isRecursive(input);
        String fileOrDirectory = extractFileOrDirectory(input);
        if(fileOrDirectory.isEmpty()) {throw new FileSystemException("Missing file or Directory for rm command");}


        return Optional.of(new Rm(fileOrDirectory, recursive));
    }

    private boolean isRecursive(String[] parts) {
        for(String part : parts) {
            if(part.equals("--recursive")) {return true;}
        }
        return false;
    }

    private String extractFileOrDirectory(String[] parts) {
        for (int i = 1; i < parts.length; i++) {
            String part = parts[i];
            if (!part.startsWith("-")) {
                return part;
            }
        }
        return "";
    }

}
