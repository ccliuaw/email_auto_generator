import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TemplateTest {
  private Template template;
  private Template template2;
  private String TEMPLATE_PATH = "email-template.txt";
  private String FAKE_PATH = "fake.txt";
  private String OUTPUT_PATH = "test_output";
  private String CSV_PATH = "insurance-company-members.csv";
  private List<List<String>> list;
  private CSVParser csvParser;

  @BeforeEach
  void setUp() throws IOException {
    template = new Template();
    template2 = new Template();
    csvParser = new CSVParser();
    list = csvParser.parse(CSV_PATH);
    template.processTemplate(TEMPLATE_PATH, list, OUTPUT_PATH);
    template2.processTemplate(TEMPLATE_PATH, list, OUTPUT_PATH);
  }

  @Test
  void processTemplate() throws IOException {
    assertEquals(template.getTemplate(), template2.getTemplate());
    // test fake file path
    Throwable exception = assertThrows(IOException.class, () -> template2.processTemplate(FAKE_PATH, list, OUTPUT_PATH));
    assertEquals(exception.getMessage(), "fake.txt");
    // test wrong output path
    Throwable exception2 = assertThrows(IOException.class, () -> template2.processTemplate(TEMPLATE_PATH, list, "inputCSV.csv"));
    assertEquals(exception2.getMessage(), "inputCSV.csv/data_501.txt: Not a directory");
  }

  @Test
  void testToString() {
    assertEquals(template.toString(), template2.toString());
  }

  @Test
  void testEquals() {
    assertEquals(template, template);
    assertEquals(template, template2);
    assertNotEquals(template, null);
    assertNotEquals(template, list);
  }

  @Test
  void testHashCode() {
    assertEquals(template.hashCode(), template2.hashCode());
  }
}