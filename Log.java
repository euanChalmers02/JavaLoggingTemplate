package uk.ac.ed.inf.Logging;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * Class of the format of each log within the logger. Stores key information like LogType enum , msg , current time of log
 */
public class Log {

    @JsonProperty("Type")
    public LogType type;
    @JsonProperty("Msg")
    public String msg;

    @JsonProperty("Time")
    public String time;

    /**
     * Simple constructor using the parameters from the logAction method.
     * @param type -  LogType enum
     * @param msg -  String message detailing the error or a runtime of the system
     */
    public Log(LogType type,String msg){
        this.type = type;
        this.msg = msg;
        this.time = String.valueOf(LocalDateTime.now());
    }

    /**
     * Overridden here to turn my file into a more parsable format.
     * @return String - of all the log info stored
     */
    @Override
    public String toString() {
        return type+" > "+msg+" > "+time+">"+ System.lineSeparator();
    }
}
