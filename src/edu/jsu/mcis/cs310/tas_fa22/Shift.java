package edu.jsu.mcis.cs310.tas_fa22;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import java.util.HashMap;
import java.time.*;

public class Shift {
    private  int Id, RoundInterval, GracePeriod, DockPenalty, LunchThreshold ;
    private String Description;
    private  LocalTime ShiftStart, ShiftStop, LunchStart, LunchStop;
    private int LunchDuration, ShiftDuration;
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
        this.LunchDuration =(int)Duration.between(this.LunchStart, this.LunchStop).toMinutes();
        this.ShiftDuration = (int)Duration.between(this.ShiftStart, this.ShiftStop).toMinutes();
        }

    public int getId() {
        return Id;
    }

    public int getRoundInterval() {
        return RoundInterval;
    }

    public int getGracePeriod() {
        return GracePeriod;
    }

    public int getDockPenalty() {
        return DockPenalty;
    }

    public int getLunchThreshold() {
        return LunchThreshold;
    }

    public LocalTime getShiftStart() {
        return ShiftStart;
    }

    public LocalTime getShiftStop() {
        return ShiftStop;
    }

    public LocalTime getLunchStart() {
        return LunchStart;
    }

    public LocalTime getLunchStop() {
        return LunchStop;
    }

    public int getLunchDuration() {
        return LunchDuration;
    }

    public int getShiftDuration() {
        return ShiftDuration;
    }

    public String getDescription() {
        return Description;
    }

    @Override
    public String toString(){
        String results = getDescription()
                + ": " + getShiftStart().toString()
                + " - "
                + getShiftStop().toString()
                + "; Lunch:"
                + getLunchStart()
                + " - " + getLunchStop()
                + " (" + getLunchDuration()
                + " minutes)";
        return results;
    }

    }

