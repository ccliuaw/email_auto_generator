import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CSVParserTest {
  private CSVParser csvParser;
  private CSVParser csvParser2;
  private String CSV_PATH = "insurance-company-members.csv";
  private String FAKE_PATH = "no.csv";
  List<List<String>> list;
  List<List<String>> list2;
  @BeforeEach
  void setUp() throws IOException {
    csvParser = new CSVParser();
    csvParser2 = new CSVParser();
    list = csvParser.parse(CSV_PATH);
    list2 = csvParser2.parse(CSV_PATH);
  }

  @Test
  void testGetData(){
    assertEquals(csvParser.getData(), csvParser2.getData());
    assertEquals(list, csvParser.getData());
  }

  @Test
  void parse() throws IOException {
    assertEquals(list.size(), 501);
    assertEquals(list.get(1).size(), 12);
    assertEquals(list, list2);
    Throwable exception = assertThrows(IOException.class, () -> csvParser2.parse(FAKE_PATH));
    assertEquals(exception.getMessage(), "no.csv (No such file or directory)");
  }

  @Test
  void testToString(){
    assertEquals(csvParser.toString(), csvParser2.toString());
  }

  @Test
  void testEquals() {
    assertEquals(csvParser, csvParser2);
    assertEquals(csvParser, csvParser);
    assertNotEquals(csvParser, null);
    assertNotEquals(csvParser, list);
  }

  @Test
  void testHashCode() {
    assertEquals(csvParser.hashCode(), csvParser2.hashCode());
  }
}