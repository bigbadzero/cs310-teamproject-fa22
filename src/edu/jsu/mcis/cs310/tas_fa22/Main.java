package edu.jsu.mcis.cs310.tas_fa22;

import edu.jsu.mcis.cs310.tas_fa22.dao.*;
import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import org.json.simple.*;

public class Main {

    public static void main(String[] args) {
        
        // test database connectivity; get DAOs

        DAOFactory daoFactory = new DAOFactory("tas.jdbc");
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
        ShiftDAO shiftDAO = daoFactory.getShiftDAO();
        PunchDAO punchDAO = daoFactory.getPunchDAO();

        Badge badge = badgeDAO.find("4E6E296E");
        Punch punch = new Punch(105, badge, EventType.CLOCK_IN);
        int test = punchDAO.create(punch);
        Punch punch2 = punchDAO.find(3433);
        System.err.println(punch2.printOriginal());





    }

}
