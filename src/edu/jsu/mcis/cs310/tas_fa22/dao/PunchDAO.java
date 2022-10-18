package edu.jsu.mcis.cs310.tas_fa22.dao;
import edu.jsu.mcis.cs310.tas_fa22.*;
import java.sql.*;
import java.util.ArrayList;
import java.time.*;

public class PunchDAO {
    private static final String QUERY_FIND = "SELECT * FROM event WHERE id = ?";
    private static final String QUERY_INSERT = "INSERT INTO event (terminalid, badgeid, timestamp, eventtypeid) values (?, ?, ?, ?)";
    private static final String QUERY_LIST = "select  * from event where badgeid = ? and DATE(timestamp) = ?";
    private final DAOFactory daoFactory;

    public PunchDAO(DAOFactory daoFactory){this.daoFactory = daoFactory;}

    public Punch find(int id){
        Punch punch = null;
        EventType eventType = null;
        Badge badge = null;
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
                        punch = new Punch(rs.getInt("id"), rs.getInt("terminalid"), getBadge(rs.getString("badgeid")), rs.getTimestamp("timestamp").toLocalDateTime(), getEventType(rs.getInt("eventtypeid")));
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

            return punch;

        }
    }

    public int create(Punch punch){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        int punchId = 0;
        try{
            Connection conn = daoFactory.getConnection();
            if(conn.isValid(0)){
                ps = conn.prepareStatement(QUERY_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, punch.getTerminalid());
                ps.setString(2, punch.getBadgeId());
                ps.setTimestamp(3, Timestamp.valueOf(punch.getOriginaltimestamp()));
                ps.setInt(4, punch.getEventTypeId());
                result = ps.executeUpdate();

                if(result == 1){
                    rs = ps.getGeneratedKeys();
                    if(rs.next()){
                        punchId = rs.getInt(1);
                    }
                }

            }
        }catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {
            return punchId;
        }
    }

    public ArrayList<Punch> list(Badge badge, LocalDate date){
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Punch> list = new ArrayList<Punch>();

        try{
            Connection conn = daoFactory.getConnection();
            if(conn.isValid(0)){
                ps = conn.prepareStatement(QUERY_LIST);
                ps.setString(1, badge.getId());
                ps.setDate(2, Date.valueOf(date));

                boolean hasResults = ps.execute();
                if(hasResults){
                    rs = ps.getResultSet();

                    while(rs.next()){
                        Punch punch = new Punch(rs.getInt("id"), rs.getInt("terminalid"), getBadge(rs.getString("badgeid")), rs.getTimestamp("timestamp").toLocalDateTime(), getEventType(rs.getInt("eventtypeid")));
                        list.add(punch);
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

            return list;
        }

    }

    private Badge getBadge(String id){
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
        Badge badge = badgeDAO.find(id);

        return badge;
    }

    private EventType getEventType(int eventType){
        EventType event = EventType.values()[eventType];

        return event;
    }
}
