package ftc8390.vv;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by jmgu3 on 11/7/2016.
 */
public class AutonFileHandler {

    // all data here
    public Integer waitTime;
    public Integer driveDistanceClose;
    public Integer driveDistanceFar;
    public Double driveSpeed;
    // variables used during the configuration process
    private String configFileName = "AutonInfo.txt";

    private void initializeValues() {
        waitTime = 0;
        driveDistanceClose = 9000;
        driveDistanceFar = 14564;
        driveSpeed = .35;
    }

    public void readDataFromFile(Context context) {
        // setup initial configuration parameters here
        initializeValues();

        // read configuration data from file
        try {
            InputStream inputStream = context.openFileInput(configFileName);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                // read data here
                waitTime = Integer.valueOf(bufferedReader.readLine());
                driveDistanceClose = Integer.valueOf(bufferedReader.readLine());
                driveDistanceFar = Integer.valueOf(bufferedReader.readLine());
                driveSpeed = Double.valueOf(bufferedReader.readLine());
                inputStream.close();
            }
        } catch (Exception e) {
            // values here, for first time or in case there's a problem reading.
            initializeValues();
        }

    }

    public boolean writeDataToFile(Context context) {
        // may want to write configuration parameters to a file here if they are needed for teleop too!
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(configFileName, Context.MODE_PRIVATE));

            // write data here, as a string on its own line. "\n" puts a new line at the end of the write, like hitting "enter"

            outputStreamWriter.write(Integer.toString(waitTime) + "\n");
            outputStreamWriter.write(Integer.toString(driveDistanceClose) + "\n");
            outputStreamWriter.write(Integer.toString(driveDistanceFar) + "\n");
            outputStreamWriter.write(Double.toString(driveSpeed) + "\n");
            outputStreamWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

}