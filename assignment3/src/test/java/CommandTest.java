import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandTest {
  private Command command;
  private Command command2;
  @BeforeEach
  void setUp() {
    command = new Command();
    command2 = new Command();
  }

  @Test
  void setGenerateEmail() {
    command.setGenerateEmail(true);
    assertTrue(command.isGenerateEmail());
  }

  @Test
  void setGenerateLetter() {
    command.setGenerateLetter(true);
    assertTrue(command.isGenerateLetter());
  }

  @Test
  void setEmailTemplateFile() {
    command.setEmailTemplateFile("test.txt");
    assertTrue(command.getEmailTemplateFile().equals("test.txt"));
  }

  @Test
  void setLetterTemplateFile() {
    command.setLetterTemplateFile("test.txt");
    assertTrue(command.getLetterTemplateFile().equals("test.txt"));
  }

  @Test
  void setCsvFile() {
    command.setCsvFile("test.csv");
    assertTrue(command.getCsvFile().equals("test.csv"));
  }

  @Test
  void setOutputDir() {
    command.setOutputDir("test");
    assertTrue(command.getOutputDir().equals("test"));
  }

  @Test
  void testToString() {
    assertEquals(command.toString(), command2.toString());
    command.setGenerateEmail(true);
    assertNotEquals(command.toString(), command2.toString());
  }

  @Test
  void testEquals() {
    assertEquals(command, command);
    assertEquals(command, command2);
    assertNotEquals(command, null);
    assertNotEquals(command, new Object());
    command.setGenerateEmail(true);
    assertNotEquals(command2, command);
  }

  @Test
  void testHashCode() {
    assertEquals(command.hashCode(), command2.hashCode());
  }
}