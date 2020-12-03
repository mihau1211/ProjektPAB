package DBElements;

import java.nio.channels.ScatteringByteChannel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Type {
    Scanner scan = new Scanner(System.in);
    String name;
    long typeID;

    public String getName() {
        return name;
    }

    public long getTypeID() {
        return typeID;
    }

    public Type(){

    }

    public Type(long typeID, String name) {
        this.name = name;
        this.typeID = typeID;
    }

    @Override
    public String toString(){
        return typeID + " / " + name;
    }

    public ArrayList<Type> getTypes(Connection conn){
        ArrayList<Type> types = new ArrayList<Type>();

        String query = "SELECT * FROM Type";

        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Type type = new Type(rs.getLong("idType"),rs.getString("TypeName"));
                types.add(type);
            }
            return types;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void insertType(Connection conn, String name) throws SQLException {

        String query = " insert into Type (TypeName)"
                + " values (?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, name);

        preparedStmt.execute();
    }
    public void deleteTypeByID(Connection conn, long typeID) throws SQLException {

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
    public void updateType(Connection conn, String name, long id) throws SQLException{

        String query = "UPDATE Type SET TypeName = ? WHERE idType = ?;";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, name);
        preparedStmt.setLong(2, id);

        preparedStmt.execute();
    }
}
