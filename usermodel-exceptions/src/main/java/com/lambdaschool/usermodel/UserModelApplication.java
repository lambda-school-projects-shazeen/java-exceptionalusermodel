package com.lambdaschool.usermodel;

import com.lambdaschool.usermodel.models.CountryData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class to start the application.
 */
@EnableJpaAuditing
@SpringBootApplication
public class UserModelApplication
{
    /**
     * Reads from a filename, which is hard coded in the method. The file should be formatted as<br>
     * name=value<br>
     * name=value<br>
     * ...<br>
     * To read in environment variables.
     */
    public static void readFromEnvFile()
    {
        /**
         * A hash map to hold the environment variables. This is just for demonstration purposes.
         * If we wanted to use the values in the rest of the application, we store them in some public class.
         */
        Map<String, String> envMap = new HashMap<>();

        String pathname = "usermodel1.env";
        File myFile = new File(pathname);

        // if we cannot open or read from the file an exception is thrown and caught by the try..catch construct
        // Note will tell our catch to catch all exceptions and just say the file is not found.
        // finally we print what was put into the HashMap
        try
        {
            Scanner inputFile = new Scanner(myFile);
            while (inputFile.hasNextLine())
            {
                String myText = inputFile.nextLine();
                String[] mySplitText = myText.split("=");
                envMap.put(mySplitText[0], mySplitText[1]);
            }
        } catch (Exception ex)
        {
            System.out.println("File: " + pathname + " not found! Environment Values Not Initialized!!!");
        } finally
        {
            System.out.println(envMap.toString());
        }
    }

    /**
     * Main method to start the application.
     *
     * @param args Not used in this application.
     */
    public static void main(String[] args)
    {
        // just to see how try..catch works!
        readFromEnvFile();

        // now run the real application!
        SpringApplication.run(UserModelApplication.class,
                              args);
    }
}
