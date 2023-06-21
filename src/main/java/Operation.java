import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Operation {
    public static void main(String[] args) {
        List<Data> dataList = getData();
        System.out.println("id       | first_name                                | last_name");
        System.out.println("---------+-------------------------------------+--------------+-----");
        for (Data data : dataList) {
            System.out.printf("%-9d| %-36s| %-13s| %n", data.getId(), data.getFirstName(), data.getLastName());
        }
    }

    private final static String URL = "jdbc:postgresql://localhost:4567/postgres";
    private final static String USER_NAME = "MotornaHanna";
    private final static String USER_PASSWORD = "privetkakdela33";
    private final static String QUERY_SELECT_ALL = "select * from customers";
    private final static String QUERY_INSERT = "insert into customers (?,?,?,?,?)";
    private final static String QUERY_UPDATE = "update operation set first_name=?, last_name=? where id=?";
    private final static String QUERY_DELETE = "delete from operation where id=?";

  //  @org.jetbrains.annotations.NotNull
    public static List<Data> getData() throws RuntimeException {
        List<Data> dataList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            int rowCount = 0;
            while (resultSet.next()) {
                Data data = new Data(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_name"));
                dataList.add(data);
                rowCount++;
            }
            System.out.println("Total rows: " + rowCount);
        } catch (SQLException exception) {
           throw new RuntimeException(exception);
        }
        return dataList;
    }

    static void insertToDB(int id, String firstName, String lastName) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Check connection " + "URL [%s]"
                    + " name [%s]" + " pass [%s]", URL, USER_NAME, USER_PASSWORD));

        }
    }

    public static void updateDataDB(int id, String firstName, int lastName) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
            preparedStatement.setString(1, firstName);
            preparedStatement.setInt(2, lastName);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Check connection" + "URL [%s]"
                    + " name [%s]" + " pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
    }

    public static void deleteFromDB(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string " + "URL [%s]"
                    + " name [%s]" + " pass [%s]", URL, USER_NAME, USER_PASSWORD));

        }
    }
}


