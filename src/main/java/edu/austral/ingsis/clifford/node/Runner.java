package edu.austral.ingsis.clifford.node;

import static java.util.Arrays.copyOfRange;

import edu.austral.ingsis.clifford.commands.*;

import java.nio.file.FileSystemException;
import java.util.List;
import java.util.Optional;

public class Runner {

//  public String runCommand(String command, FileSystem fileSystem) {
//    String[] parts = command.trim().split("\\s+");
//    if (parts.length == 0) {
//      return "";
//    }
//    String name = parts[0];
//    String[] arguments = copyOfRange(parts, 1, parts.length);
//    Command command2;
//    try {
//      switch (name) {
//        case "cd":
//          if (arguments.length != 1) {
//            return "Wrong usage! ";
//          }
//          command2 = new Cd(arguments[0]);
//          break;
//
//        case "touch":
//          if (arguments.length != 1) {
//            return "";
//          }
//          command2 = new Touch(arguments[0]);
//          break;
//
//        case "mkdir":
//          if (arguments.length != 1) {
//            return "";
//          }
//          command2 = new Mkdir(arguments[0]);
//          break;
//
//        case "ls":
//          command2 = new Ls(Optional.empty());
//          break;
//
//        case "pwd":
//          command2 = new Pwd();
//          break;
//        case "rm":
//          boolean recursive = false;
//          String fileName = null;
//          for (String arg : arguments) {
//            if (arg.equals("-recursive")) {
//              recursive = true;
//            } else {
//              fileName = arg;
//            }
//          }
//          if (fileName == null) {
//            return "Wrong usage! ";
//          }
//          command2 = new Rm(fileName, recursive);
//          break;
//
//        default:
//          return "Unknown command!";
//      }
//      return command2.execute(fileSystem);
//    } catch (FileSystemException e) {
//      return e.getMessage();
//    } catch (Exception e) {
//      return e.getMessage();
//    }
//  }

    private final List<CommandRunner> commandRunners;
    public Runner(){
        this.commandRunners = List.of(new CdRunner(), new LsRunner(), new MkdirRunner(), new PwdRunner(), new RmRunner(), new TouchRunner());
    }

    public String runCommand(String command, InMemoryFileSystem fileSystem) {
        String[] parts = splitCommand(command);
        if (parts.length == 0) {
            return "";
        }

        Optional<Command> optionalCommand = findCommandRunner(command, parts);
        return optionalCommand.map(commandToRun -> executeCommand(commandToRun, parts, fileSystem))
                .orElse("Unknown command!");
    }

    private String[] splitCommand(String command) {
        return command.trim().split("\\s+");
    }

    private Optional<Command> findCommandRunner(String command, String[] parts) {
        for (CommandRunner runner : commandRunners) {
            if (runner.supports(command)) {
                try {
                    return runner.parse(parts);
                } catch (FileSystemException e) {
                    return Optional.empty();
                }
            }
        }
        return Optional.empty();
    }

    private String executeCommand(Command command, String[] parts, InMemoryFileSystem fileSystem) {
        try {
            return command.execute(fileSystem, List.of(parts));
        } catch (FileSystemException e) {
            return e.getMessage();
        }
    }

}

