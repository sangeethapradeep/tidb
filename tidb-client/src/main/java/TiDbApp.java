import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Employee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TiDbApp {

  private static Statement stmt;
  private static ResultSet results;

  public static void main(String[] args) {
    String sql_select = "Select * From Employee";

    try (Connection conn = DBConnection.createNewDBconnection()) {

      stmt = conn.createStatement();
      results = stmt.executeQuery(sql_select);

      List<Employee> employees = new ArrayList<Employee>();

      while (results.next()) {

        Employee employee = new Employee();

        employee.setName(results.getString("name"));
        employee.setAge(Integer.parseInt(results.getString("age")));

        employees.add(employee);
      }

      ObjectMapper mapper = new ObjectMapper();
      String JSONOutput = mapper.writeValueAsString(employees);
      System.out.println(JSONOutput);


    } catch (SQLException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

}
