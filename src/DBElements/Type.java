package DBElements;

import java.sql.*;
import java.util.Scanner;

public class Type {
    Scanner scan = new Scanner(System.in);
    String name;
    long typeID;

    public String getName() {
        System.out.println("Podaj nazwe:");
        return name = scan.nextLine();
    }

    public long getTypeID() {
        System.out.println("Podaj ID:");
        return typeID = scan.nextLong();
    }

    public void insertType(Connection conn) throws SQLException {

        String query = " insert into Type (TypeName)"
                + " values (?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, getName());

        preparedStmt.execute();
    }
    public void deleteTypeByID(Connection conn) throws SQLException {

        typeID=getTypeID();
        String query = "DELETE FROM Type WHERE ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong (1, typeID);

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
        System.out.println("========");
        System.out.println("||TYPE||");
        System.out.println("========");
        System.out.println("| ID  |       NAME         |");

        while (rs.next()){
            long id = rs.getLong("idType");
            String name = rs.getString("TypeName");
            System.out.format("|%1$-5s|%2$-12s|\n", id, name);
        }
    }

    public void addTypes(Connection conn) throws SQLException {
        String query = " insert into Type (TypeName)"
                + " values ('ACTION')";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();

        query = " insert into Type (TypeName)"
                + " values ('COMEDY')";
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();

        query = " insert into Type (TypeName)"
                + " values ('HORROR')";
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();

        query = " insert into Type (TypeName)"
                + " values ('THRILLER')";
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();

        query = " insert into Type (TypeName)"
                + " values ('DRAMA')";
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();

        query = " insert into Type (TypeName)"
                + " values ('FANTASY')";
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.execute();
    }
    public void updateType(Connection conn) throws SQLException{

        String query = "UPDATE Type SET name = ? WHERE typeID = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, getName());
        preparedStmt.setLong(2, getTypeID());

        preparedStmt.execute();
    }
}
