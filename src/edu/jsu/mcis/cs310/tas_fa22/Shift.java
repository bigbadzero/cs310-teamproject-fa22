package edu.jsu.mcis.cs310.tas_fa22;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import java.util.HashMap;
import java.time.*;

public class Shift {
    private  int Id, RoundInterval, GracePeriod, DockPenalty, LunchThreshold;
    private String Description;
    private  LocalTime ShiftStart, ShiftStop, LunchStart, LunchStop;

    public Shift(HashMap<String,String> map) {
        this.Id = Integer.parseInt(map.get("id"));
        this.Description = map.get("description");
        this.ShiftStart = LocalTime.parse(map.get("shiftStart"));
        this.ShiftStop = LocalTime.parse(map.get("shiftStop"));
        this.RoundInterval = Integer.parseInt(map.get("roundInterval"));
        this.GracePeriod = Integer.parseInt(map.get("gracePeriod"));
        this.DockPenalty = Integer.parseInt(map.get("dockPenalty"));
        this.LunchStart = LocalTime.parse(map.get("lunchStart"));
        this.LunchStop = LocalTime.parse(map.get("lunchStop"));
        this.LunchThreshold = Integer.parseInt(map.get("lunchThreshold"));
        }

    public String getDescription() {
        return Description;
    }
    }

