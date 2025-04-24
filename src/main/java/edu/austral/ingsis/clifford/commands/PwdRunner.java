package edu.austral.ingsis.clifford.commands;

import java.nio.file.FileSystemException;
import java.util.Optional;

public class PwdRunner implements CommandRunner {
    @Override
    public boolean supports(String command) {
        return command.trim().startsWith("pwd");
    }

    @Override
    public Optional<Command> parse(String[] input) throws FileSystemException {
        return Optional.of(new Pwd());
    }
}
