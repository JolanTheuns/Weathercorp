package sample;

import java.sql.*;
import java.util.ArrayList; //importing the libraries necessary to perform the Java Database Connectivity

public class weatherDAO {


    private static weatherDAO onlyInstance = null; //initializing the CityDAO as the only instance in regard to the Singleton principle (i.e. that there only should be one instance of a class, and that instance can be called upon by a method .getInstance if set to static)
    private static Connection connection = null; // initializing the Connection in the same way


    private weatherDAO (DBmanager db )
    { //precondition dbExists
        if ((connection = db.getConnection()) == null )
        {
            Controller.printFailure();
        }

    } //connect to Jeroen's database


//---------------------------------METHOD FOR GETTING ACCESS TO THE ONLY INSTANCE OF THE WEATHERDAO CLASS----------------------------------------------------------
    public static synchronized weatherDAO getInstance (DBmanager db )
    {
        if (onlyInstance == null)
        {
            onlyInstance = new weatherDAO(db);
        }
        return onlyInstance;
    }

//---------------------------------METHOD FOR ACTUALLY FETCHING THE INFORMATION FROM THE DATABASE, DEPENDING ON WHICH BUTTON IS PRESSED----------------------------
    public static ArrayList<Integer> getInformation(boolean nowOr7Days, int controlVariable)
    {
        String columnName = "";
        ArrayList<Integer> weatherInfo = new ArrayList<Integer>();

        if (nowOr7Days == true) //showing the most updated information
        {

            if (controlVariable == 1) // this means TEMPERATURE
            {
                columnName = "temperature";
                PreparedStatement preparedStatement = null;
                try
                {
                    preparedStatement = connection.prepareStatement("SELECT ? FROM weather ORDER BY  "); //query to be ran on the database
                    preparedStatement.setString(1, columnName + "%");//the missing part of the query (denoted with the question mark), so the column heading, in this case the temperature
                    ResultSet resultSet = preparedStatement.executeQuery(); //executing the query in the database

                    while(resultSet.next())
                    {
                        weatherInfo.add(resultSet.getInt(columnName)); //this shows in red because we are trying to add a String into the ArrayList instead of an Integer. I need to figure out how to pass a particular column to the get method, so that the integers from THAT column will be passed to it.
                    }
                }
                catch (SQLException se)
                {
                    se.printStackTrace();
                }
                finally
                {
                    try
                    {
                        if (preparedStatement != null)
                        {
                            preparedStatement.close();
                        }
                    }
                    catch (SQLException se)
                    {
                        se.printStackTrace();
                    }
                }
                return weatherInfo;
            }

//------------------------------------------------------------------------------------------------

            if (controlVariable == 2) //this means PRESSURE
            {
                columnName = "pressure";
                PreparedStatement preparedStatement2 = null;
                try
                {
                    preparedStatement2 = connection.prepareStatement("SELECT ? FROM weather ORDER BY  ");
                    preparedStatement2.setString(1, columnName+"%");
                    ResultSet resultSet = preparedStatement2.executeQuery();

                    while(resultSet.next())
                    {
                        weatherInfo.add(resultSet.getInt(columnName)); //hard to make this, because in the previous assignment, we were dealing with column names(i.e. STRINGS). Here, we are dealing with INTEGERS. The column names are still (obviously) STRINGS.
                    }
                }
                catch (SQLException se)
                {
                    se.printStackTrace();
                }
                finally
                {
                    try
                    {
                        if (preparedStatement2 != null)
                        {
                            preparedStatement2.close();
                        }
                    }
                    catch (SQLException se)
                    {
                        se.printStackTrace();
                    }
                }
                return weatherInfo;
            }

//-------------------------------------------------------------------------------------------------

            if (controlVariable == 3) //this means HUMIDITY
            {
                columnName = "humidity";
                PreparedStatement preparedStatement3 = null;
                try
                {
                    preparedStatement3 = connection.prepareStatement("SELECT ? FROM weather ORDER BY  ");
                    preparedStatement3.setString(1, columnName+"%");
                    ResultSet resultSet = preparedStatement3.executeQuery();

                    while(resultSet.next())
                    {
                        weatherInfo.add(resultSet.getInt(columnName));
                    }
                }
                catch (SQLException se)
                {
                    se.printStackTrace();
                }
                finally
                {
                    try
                    {
                        if (preparedStatement3 != null)
                        {
                            preparedStatement3.close();
                        }
                    }
                    catch (SQLException se)
                    {
                        se.printStackTrace();
                    }
                }
                return weatherInfo;
            }



//--------------------------------------------------------------------------------------------------

            if (controlVariable == 4) //this means LUMINOSITY
            {
                columnName = "luminosity";
                PreparedStatement preparedStatement4 = null;
                try
                {
                    preparedStatement4 = connection.prepareStatement("SELECT ? FROM weather ORDER BY  ");
                    preparedStatement4.setString(1, columnName+"%");
                    ResultSet resultSet = preparedStatement4.executeQuery();

                    while(resultSet.next())
                    {
                        weatherInfo.add(resultSet.getInt(columnName));
                    }
                }
                catch (SQLException se)
                {
                    se.printStackTrace();
                }
                finally
                {
                    try
                    {
                        if (preparedStatement4 != null)
                        {
                            preparedStatement4.close();
                        }
                    }
                    catch (SQLException se)
                    {
                        se.printStackTrace();
                    }
                }
                return weatherInfo;
            }
        }
//--------------------------------------------------------------------------------------------------


        if (nowOr7Days == false) //showing the information from the last 7 days
        {

            if (controlVariable == 1) // this means TEMPERATURE
            {
                columnName = "temperature";
                PreparedStatement preparedStatement = null;
                try
                {
                    preparedStatement = connection.prepareStatement("SELECT ROUND(AVG(?)), DATE(timedate) FROM weather WHERE DATE(timedate) >= (DATE(NOW())- INTERVAL 7 DAY) GROUP BY ROUND(DATE(timedate));"); //query to be ran on the database
                    preparedStatement.setString(1,columnName+"%"); //the missing part of the query (denoted with the question mark), so the column heading, in this case the temperature
                    ResultSet resultSet = preparedStatement.executeQuery(); //executing the query in the database

                    while(resultSet.next())
                    {
                        weatherInfo.add(resultSet.getInt(columnName));
                    }
                }
                catch (SQLException se)
                {
                    se.printStackTrace();
                }
                finally
                {
                    try
                    {
                        if (preparedStatement != null)
                        {
                            preparedStatement.close();
                        }
                    }
                    catch (SQLException se)
                    {
                        se.printStackTrace();
                    }
                }
                return weatherInfo;
            }

//------------------------------------------------------------------------------------------------

            if (controlVariable == 2) //this means PRESSURE
            {
                columnName = "pressure";
                PreparedStatement preparedStatement2 = null;
                try
                {
                    preparedStatement2 = connection.prepareStatement("SELECT ROUND(AVG(?)), DATE(timedate) FROM weather WHERE DATE(timedate) >= (DATE(NOW())- INTERVAL 7 DAY) GROUP BY ROUND(DATE(timedate));");
                    preparedStatement2.setString(0,columnName+"%");
                    ResultSet resultSet = preparedStatement2.executeQuery();

                    while(resultSet.next())
                    {
                        weatherInfo.add(resultSet.getInt(columnName));
                    }
                }
                catch (SQLException se)
                {
                    se.printStackTrace();
                }
                finally
                {
                    try
                    {
                        if (preparedStatement2 != null)
                        {
                            preparedStatement2.close();
                        }
                    }
                    catch(SQLException se)
                    {
                        se.printStackTrace();
                    }
                }
                return weatherInfo;
            }

//-------------------------------------------------------------------------------------------------

            if (controlVariable == 3) //this means HUMIDITY
            {
                columnName = "humidity";
                PreparedStatement preparedStatement3 = null;
                try
                {
                    preparedStatement3 = connection.prepareStatement("SELECT ROUND(AVG(?)), DATE(timedate) FROM weather WHERE DATE(timedate) >= (DATE(NOW())- INTERVAL 7 DAY) GROUP BY ROUND(DATE(timedate));");
                    preparedStatement3.setString(0,columnName+"%");
                    ResultSet resultSet = preparedStatement3.executeQuery();

                    while(resultSet.next())
                    {
                        weatherInfo.add(resultSet.getInt(columnName));
                    }
                }
                catch (SQLException se)
                {
                    se.printStackTrace();
                }
                finally
                {
                    try
                    {
                        if (preparedStatement3 != null)
                        {
                            preparedStatement3.close();
                        }
                    }
                    catch(SQLException se)
                    {
                        se.printStackTrace();
                    }
                }
                return weatherInfo;
            }



//--------------------------------------------------------------------------------------------------

            if (controlVariable == 4) //this means LUMINOSITY
            {
                columnName = "luminosity";
                PreparedStatement preparedStatement4 = null;
                try
                {
                    preparedStatement4 = connection.prepareStatement("SELECT ROUND(AVG(?)), DATE(timedate) FROM weather WHERE DATE(timedate) >= (DATE(NOW())- INTERVAL 7 DAY) GROUP BY ROUND(DATE(timedate));");
                    preparedStatement4.setString(0,columnName+"%");
                    ResultSet resultSet = preparedStatement4.executeQuery();

                    while(resultSet.next())
                    {
                        weatherInfo.add(resultSet.getInt(columnName));
                    }
                }
                catch (SQLException se)
                {
                    se.printStackTrace();
                }
                finally
                {
                    try
                    {
                        if (preparedStatement4 != null)
                        {
                            preparedStatement4.close();
                        }
                    }
                    catch(SQLException se)
                    {
                        se.printStackTrace();
                    }
                }
                return weatherInfo;
            }
        }
        return weatherInfo; //this part of the code is unreachable, but there needs to be a return statement
    }


}
