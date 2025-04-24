package edu.austral.ingsis.clifford.commands;

import java.nio.file.FileSystemException;
import java.util.Optional;

public interface CommandRunner {

  boolean supports(String command);

  Optional<Command> parse(String[] input) throws FileSystemException;
}
