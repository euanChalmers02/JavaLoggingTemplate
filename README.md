# JavaLoggingTemplate
This is a java logging template for logging in java and writing to a json file.

This will require small changes to work within your project but advatages of the package is that it is conatined within the 3 classes and can be writen to and endpoint or as a json file. 

NOTE: I would advise wrapping this in a package with a relevent name.



<strong>Logging Structure:</strong>
Logging was done using my logging package, with logger being the main driver class that holds all logs and the method, including toFile(). This was created as its own package compared with printing to the console because logging is more scalable and it allows me to compare results from different algorithm strategies and be more scalable to more days and a bigger codebase. Additionally, by logging to a file, I can parse the outputs in my python script (1.7.4) to compare results. Logging catches the main points of the program along with runtime and analytics like the number of orders delivered that day. A point of further development would be to convert these log strings into JSON objects like the output files, allowing them to be written back to an endpoint like how they could be done for output files. Furthermore, this would allow for querying logs like cloud logging services such as AWS CloudWatch or Azure Monitor.

---

<strong>How to setup</strong>

Step 1:
Add all the files in repo into a package within java project. Making sure to change the revelevent imports vs the imports i have used. 

Step 2:
Update all logTypes that are revelent to your program.

Step 3:
Add the logs throughout your system using the following setup:

<code>Logging log = Logging.getInstance();
      log.logAction(LogType.GeneralAction, "This is your customisable error message");
</code>
