package repository;

import domain.Flight;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository extends BaseConnection{


    public List<Flight> getAllFlights(String src,String des){
        List<Flight> flightList = new ArrayList<>();

        try{
         String query = generateQuery(src,des);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Flight fl = new Flight();
                fl.populate(rs);
                flightList.add(fl);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flightList;
    }

    private String generateQuery(String src, String des) {
        String query = "select * from flight";
        if(!src.equalsIgnoreCase("src") && !des.equalsIgnoreCase("des")){
            query = query + " where flight_source = '"+src+"' and flight_destination='"+des+"'";
        }else if(!src.equalsIgnoreCase("src")){
            query=query + " where flight_source = '"+src+"'";
        }else if (!des.equalsIgnoreCase("des")){
            query = query +" where flight_destination = '"+des+"'";
        }

        return query;


    }

    public static void main(String[] args) {
        FlightRepository fl = new FlightRepository();
        System.out.println(fl.generateQuery("ISB","KHI"));
    }
}
