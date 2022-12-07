package uk.ac.ed.inf.Logging;

/**
 * Simple enum to allow me to categorize my log. Also includes a boolean value if they are high risk logs or not.
 */
public enum LogType {
    CommandLineInputError(true),
    NoOrdersPassedValidation(false),
    GeneralAction(false),
    OrderHasIncorrectDate(false),
    OrderFilteringComplete(false),
    Flightpath_Analysis(false),
    SuccessfulViabilityRun(false),
    IncorrectCallOfVisibility(true),
    LogTime(false),
    RecursiveCall(false),
    Analysis(false);

    //constructor for the high risk indicator
    public final boolean highRisk;
    LogType(boolean highRisk){this.highRisk = highRisk;}
    }
