package uk.ac.ed.inf.Logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ed.inf.OrderOutcome;
import uk.ac.ed.inf.Orders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The goal of this class is to manage all methods involved in logging messages and timings for the system.
 */
public class Logging {

    private ArrayList<Log> log = new ArrayList<>();
    private ArrayList<Log> highRiskLog = new ArrayList<>();
    private static Logging logger = null;


    /**
     * Creates a new instance of the logging, by deleting all current logs
     */
    public void newInstance() {
        log = new ArrayList<>();
        highRiskLog = new ArrayList<>();
    }

    /**
     * Gets the current instance of the log
     *
     * @return - the current active logger or creates a new one if non exists (simple singleton pattern)
     */
    public static Logging getInstance() {
        if (logger == null) {
            logger = new Logging();
        }
        return logger;
    }


    /**
     * Method adds a log to the logger for each time it is called. If it is highRisk it will also add to the highrisk array.
     *
     * @param type -  the LogType enum
     * @param info - the message to be passed (or the time if a timing log)
     */
    public void logAction(LogType type, String info) {
        log.add(new Log(type, info));
        if (type.highRisk) {
            highRiskLog.add(new Log(type, info));
        }
    }


    /**
     * gets all the logs in current instance
     *
     * @return log -  all logs in current instance
     */
    public ArrayList<Log> getAllLogs() {
        return log;
    }


    /**
     * get all the high risk logs of the current instance
     *
     * @return highRiskLog  - all the highrisk logs
     */
    public ArrayList<Log> getHighriskErrors() {
        return highRiskLog;
    }


    /**
     * Method to write all the logs to a file. Useful for my testing script and to review after running a batch of operations.
     * Could further develop this and create a new write to json function.
     * @param date -  the date passed in the command line
     * @throws RuntimeException - If the file fails to run
     */
    public void LogToFile(LocalDate date) {
        ObjectMapper om = new ObjectMapper();
        System.out.println("-- serializing --");

        try {
            om.writerWithDefaultPrettyPrinter().writeValue(Paths.get("Logs-" + date + ".json").toFile(), log);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method calculates the number of delivered orders vs undelivered orders. This is useful to be logged as allows my script {@link} to full the percentage for multiple dates and create a graph.
     *
     * @param orders - all the orders from selected date
     * @return result -  as a percentage of orders satisfied
     */
    public static double avgOrdersSatisfied(List<Orders> orders) {
        double yes = 0;
        double no = 0;
        for (Orders selected_day_order : orders) {
            if (selected_day_order.getOutcome() == OrderOutcome.Delivered) {
                yes = yes + 1;
            } else if (selected_day_order.getOutcome() == OrderOutcome.ValidButNotDelivered) {
                no = no + 1;
            }
        }
        System.out.println("yes " + yes);
        System.out.println("no " + no);

        double result;
        //catches divide by zero error
        if (no != 0) {
            result = ((yes / (yes + no)) * 100);
        } else {
            result = 100;
        }
        return result;
    }

}
