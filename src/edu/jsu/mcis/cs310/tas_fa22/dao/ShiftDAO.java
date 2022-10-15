package edu.jsu.mcis.cs310.tas_fa22.dao;
import java.util.HashMap;
import edu.jsu.mcis.cs310.tas_fa22.*;
import java.sql.*;

public class ShiftDAO {
    private static final String QUERY_FIND = "SELECT * FROM shift WHERE id = ?";

    private final DAOFactory daoFactory;
    private HashMap<String,String> ShitMap = new HashMap<String,String>();

    public ShiftDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Shift find(int id) {
        Shift shift = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            Connection conn = daoFactory.getConnection();

            if(conn.isValid(0)){
                ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, id);

                boolean hasResults = ps.execute();

                if(hasResults){
                    rs = ps.getResultSet();

                    while(rs.next()){
                        ShitMap.put("id", rs.getString("id"));
                        ShitMap.put("description",rs.getString("description"));
                        ShitMap.put("shiftStart",rs.getString("shiftstart"));
                        ShitMap.put("shiftStop",rs.getString("shiftstop"));
                        ShitMap.put("roundInterval",rs.getString("roundinterval"));
                        ShitMap.put("gracePeriod",rs.getString("graceperiod"));
                        ShitMap.put("dockPenalty",rs.getString("dockpenalty"));
                        ShitMap.put("lunchStart",rs.getString("lunchstart"));
                        ShitMap.put("lunchStop",rs.getString("lunchstop"));
                        ShitMap.put("lunchThreshold",rs.getString("lunchthreshold"));
                        shift = new Shift(ShitMap);

                    }
                }
            }
        }catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }

        return shift;

    }

    public Shift find(Badge badge){
        Shift shift = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            Connection conn = daoFactory.getConnection();

            if(conn.isValid(0)){
                ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, Integer.parseInt(badge.getId()));

                boolean hasResults = ps.execute();

                if(hasResults){
                    rs = ps.getResultSet();

                    while(rs.next()){
                        ShitMap.put("id", rs.getString("id"));
                        ShitMap.put("description",rs.getString("description"));
                        ShitMap.put("shiftStart",rs.getString("shiftstart"));
                        ShitMap.put("shiftStop",rs.getString("shiftstop"));
                        ShitMap.put("roundInterval",rs.getString("roundinterval"));
                        ShitMap.put("gracePeriod",rs.getString("graceperiod"));
                        ShitMap.put("dockPenalty",rs.getString("dockpenalty"));
                        ShitMap.put("lunchStart",rs.getString("lunchstart"));
                        ShitMap.put("lunchStop",rs.getString("lunchstop"));
                        ShitMap.put("lunchThreshold",rs.getString("lunchthreshold"));
                        shift = new Shift(ShitMap);

                    }
                }
            }
        }catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }

        return shift;
    }

}
