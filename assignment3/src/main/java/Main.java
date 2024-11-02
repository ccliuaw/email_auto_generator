import java.util.Scanner;

/**
 * Main class for the program
 */
public class Main {

  /**
   * The main method is the entry point for the program.
   *
   * @param args Command-line arguments passed to the program.
   */
  public static void main(String[] args) {
    try {
      // Pass the input arguments to the CommandLineParser
      CommandLineParser commandLineParser = new CommandLineParser();
      Command command = commandLineParser.parse(args);

      if (command.isValid()) {  // If the command is valid, proceed with communication service
        ConductCommand conductCommand = new ConductCommand();
        conductCommand.doCommands(command);
      }
    }catch (Exception e){
      System.out.println(e.getMessage());
    }
  }
}

// some input examples to copy:
//--email --email-template email-template.txt --output-dir storage --csv-file inputCSV.csv
//--letter --letter-template letter-template.txt --email --email-template email-template.txt --output-dir storage --csv-file inputCSV.csv
//--letter --letter-template /Users/ccliu/Desktop/5010PDP/assignment3/letter-template.txt --output-dir /Users/ccliu/Desktop/5010PDP/assignment3/storage2 --csv-file /Users/ccliu/Desktop/5010PDP/assignment3/inputCSV.csv
