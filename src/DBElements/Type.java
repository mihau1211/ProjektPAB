package DBElements;

import java.sql.*;

public class Type {
    public void insertType(Connection conn, String TypeName) throws SQLException {

        String query = " insert into Type (TypeName)"
                + " values (?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, TypeName);

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
