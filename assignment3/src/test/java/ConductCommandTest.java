import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConductCommandTest {
  private String TEMPLATE_PATH = "email-template.txt";
  private String OUTPUT_PATH = "test_output";
  private String CSV_PATH = "insurance-company-members.csv";
  private ConductCommand conductCommand;
  private Command command;

  @BeforeEach
  void setUp() throws IOException {

    command = new Command();
    command.setEmailTemplateFile(TEMPLATE_PATH);
    command.setLetterTemplateFile(TEMPLATE_PATH);
    command.setCsvFile(CSV_PATH);
    command.setGenerateEmail(true);
    command.setGenerateLetter(true);
    command.setOutputDir(OUTPUT_PATH);
    command.setValid(true);

    conductCommand = new ConductCommand();
  }

  @Test
  void doCommands() throws IOException {
    conductCommand.doCommands(command);
  }

}