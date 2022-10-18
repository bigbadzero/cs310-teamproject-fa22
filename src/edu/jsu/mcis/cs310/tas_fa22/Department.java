package edu.jsu.mcis.cs310.tas_fa22;

public class Department {
    private int id, terminalid;
    private String description;

    public Department(int id, String description, int terminalid) {
        this.id = id;
        this.terminalid = terminalid;
        this.description = description;
    }

    @Override
    public String toString(){
        String message = java.text.MessageFormat.format("#{0} ({1}), Terminal ID: {2}",this.id, this.description, this.terminalid);
        return message;
    }
}
