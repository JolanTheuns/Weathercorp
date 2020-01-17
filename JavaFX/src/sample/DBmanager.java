package sample;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBmanager {
    private static DBmanager onlyInstance = null;
    private static Connection connection = null;



    private DBmanager()
    {
        if(!dbExists()) //if the database doesn't exist, we say there was a failure
        {
            Controller.printFailure2();
        }
    }




    public static synchronized DBmanager getInstance() // a method to initialize return the only instance of the DBmanager class. That way, we can call this method in other classes and get the instance there.
    {
        if (onlyInstance == null) //if the onlyInstance is null, we initialize it
        {
            onlyInstance = new DBmanager(); //initialize the instance
        }
        return onlyInstance;
    }




    private Boolean dbExists() // a method to check whether the DBmanager object exists. It is called from the
    {
        Boolean exists = false;
        Statement statement = null;

        try
        {
            if (connection == null)
            {
                //call make connection
            }
            statement = connection.createStatement(); //creates a statement object. On that statement we will call the executeQuery() method, to run the query
            statement.executeQuery("USE weather"); //tells the program to use the weather database
        }

        catch (SQLException se) //catch an exception if it is impossible to establish a connection or run the query
        {
            se.printStackTrace();
        }

        finally
        {
            try
            {
                if(statement != null) //if the statement is not null, close the statement and the database exists
                {
                    statement.close();
                    exists = true;
                }
            }

            catch (SQLException se) //otherwise if the statement is null, catch an exception
            {
                se.printStackTrace();
                statement = null;
            }
        }
        return exists; //returns the boolean exists, whether true or not.
    }





    public void makeConection() // a method to make the connection to the database
    {
        FileInputStream input = null;
        try
        {
            Properties props = new Properties();
            input = new FileInputStream("src/database.properties"); //getting a connection to the database.properties file
            props.load(input);
            input.close();

            String db_url = props.getProperty("jdbc.db_url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            //maybe we need something more here in order to connect to the database? Like the parameters?


            System.out.println(db_url);
            System.out.println(username);
            System.out.println(password);

            connection = DriverManager.getConnection(db_url, username, password);
        }

        catch(SQLException se)
        {
            System.out.println("Connection error...");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (input != null)
                {
                    input.close();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return;
            }
        }
    }



























































    public Connection getConnection() {
        return connection;
    }
    //class to be written
}
