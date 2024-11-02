import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandLineParserTest {
  private CommandLineParser CLP;
  private String[] args;

  @BeforeEach
  void setUp() {
    CLP = new CommandLineParser();
    String input = "--letter --letter-template letter-template.txt --email --email-template email-template.txt --output-dir storage --csv-file inputCSV.csv";
    args = input.split(" ");
  }

  @Test
  void parse() {
    Command command = CLP.parse(args);
    Command command1 = CLP.parse(args);
    assertEquals(command, command1);
  }

  @Test
  void testException() {

    Command exception1 = CLP.parse(new String[]{});
    assertFalse(exception1.isValid());

    String input = "--email --email-template ";
    String[] args2 = input.split(" ");
    Command exception2 = CLP.parse(args2);
    assertFalse(exception2.isValid());

    input = "--letter --letter-template";
    String[] args3 = input.split(" ");
    exception2 = CLP.parse(args3);
    assertFalse(exception2.isValid());

    input = "--letter --letter-template --email";
    String[] args4 = input.split(" ");
    exception2 = CLP.parse(args4);
    assertFalse(exception2.isValid());

    input = "--csv-file";
    String[] args5 = input.split(" ");
    exception2 = CLP.parse(args5);
    assertFalse(exception2.isValid());

    input = "--output-dir";
    String[] args6 = input.split(" ");
    exception2 = CLP.parse(args6);
    assertFalse(exception2.isValid());

    input = "qwer";
    String[] args7 = input.split(" ");
    exception2 = CLP.parse(args7);
    assertFalse(exception2.isValid());

    input = "--email --output-dir storage --csv-file inputCSV.csv";
    String[] args8 = input.split(" ");
    exception2 = CLP.parse(args8);
    assertFalse(exception2.isValid());

    input = "--letter --output-dir storage --csv-file inputCSV.csv";
    String[] args9 = input.split(" ");
    exception2 = CLP.parse(args9);
    assertFalse(exception2.isValid());

    input = "--letter --letter-template letter-template.txt --csv-file inputCSV.csv";
    String[] args10 = input.split(" ");
    exception2 = CLP.parse(args10);
    assertFalse(exception2.isValid());

    input = "--letter --letter-template letter-template.txt --output-dir output ";
    String[] args11 = input.split(" ");
    exception2 = CLP.parse(args11);
    assertFalse(exception2.isValid());
  }
}