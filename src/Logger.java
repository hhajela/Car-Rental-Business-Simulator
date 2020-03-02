import java.io.IOException;
import java.util.*;
import java.io.FileWriter;

//implementation of Singleton pattern
public class Logger
{
    private static String logfile = "output.txt";
    private static Logger inst;
    private Logger()
    {}

    public void print(String line) throws IOException
    {
        FileWriter fw = new FileWriter(logfile, true);
        fw.write(line+"\n");
        fw.close();
    }

    public void print(ArrayList<?> values) throws IOException
    {
        //utilize toString and print each element in new line
        for (Object o : values) {
            print(o.toString());
        }
    }

    public static synchronized Logger getInstance()
    {
        if (inst == null)
            inst = new Logger();

        return inst;
    }
}
