package edu.jsu.mcis.cs310.tas_fa22;
import java.time.*;
import  java.util.*;
import java.time.format.DateTimeFormatter;

public class Punch {
    private int id, terminalid, eventTypeId;
    private String badgeId;
    private LocalDateTime originaltimestamp, adjustedTimeStamp;
    private EventType punchtype;



    private PunchAdjustmentType adjustmentType;
    private Badge badge;

    public Punch(int terminalId, Badge badge, EventType punchtype) {
        this.terminalid = terminalId;
        this.badge = badge;
        this.badgeId = badge.getId();
        this.punchtype = EventType.valueOf(punchtype.name());
        this.eventTypeId = this.punchtype.ordinal();
        this.originaltimestamp = LocalDateTime.now();
    }

    public Punch(int id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchtype){
        this.id = id;
        this.badge = badge;
        this.terminalid = terminalid;
        this.badgeId = badge.getId();
        this.punchtype = EventType.valueOf(punchtype.name());
        this.eventTypeId = this.punchtype.ordinal();
        this.originaltimestamp = originaltimestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTerminalid() {
        return terminalid;
    }

    public Badge getBadge() {
        return badge;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public LocalDateTime getOriginaltimestamp() {
        return originaltimestamp;
    }

    public LocalDateTime getAdjustedTimeStamp() {
        return adjustedTimeStamp;
    }

    public EventType getPunchtype() {
        return punchtype;
    }

    public PunchAdjustmentType getAdjustmentType() {
        return adjustmentType;
    }

    public String printOriginal(){
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        sb.append("#")
                .append(this.badgeId)
                .append(" ")
                .append(this.punchtype)
                .append(": ")
                .append(this.originaltimestamp.format(formatter).toUpperCase());

        return sb.toString();
    }
}
