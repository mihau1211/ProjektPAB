package DBElements;

import java.sql.*;
import java.util.Scanner;

public class Type {
    Scanner scan = new Scanner(System.in);
    String name;
    long typeID;

    public String getName() {
        return name = scan.nextLine();
    }

    public long getTypeID() {
        return typeID = scan.nextLong();
    }

    public void insertType(Connection conn) throws SQLException {

        String query = " insert into Type (TypeName)"
                + " values (?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, getName());

        preparedStmt.execute();
    }
    public void deleteTypeByID(Connection conn, long idType) throws SQLException {

        String query = "DELETE FROM Type WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, idType);

        preparedStmt.execute();
    }
    public void deleteTypeByName(Connection conn, String TypeName) throws SQLException{
        String query = "DELETE FROM Type WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, TypeName);

        preparedStmt.execute();
    }
    public void selectType(Connection conn) throws SQLException {

        String query = "SELECT * FROM Type";

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()){
            long id = rs.getLong("idType");
            String name = rs.getString("TypeName");
            System.out.format("%s, %s \n", id, name);
        }
    }
}
