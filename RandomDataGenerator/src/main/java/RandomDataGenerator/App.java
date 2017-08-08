package RandomDataGenerator;

import org.fluttercode.datafactory.impl.DataFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        DataFactory df = new DataFactory();

        Path p = Paths.get("c:/temp/test1.csv");
        List<String> ls = new ArrayList<String>();
        for (int i = 0; i < 30000000; i++) {
            String businessEntity = df.getRandomText(4);
            int branchNumber = df.getNumberBetween(0,10000000);
            int accountNumber = df.getNumberBetween(0,10000000);
            String name = df.getName();
            String address = df.getAddress();
            String emailAddress = df.getEmailAddress();
            String securityNumber = df.getRandomChars(8);
            int settlementQuantity = df.getNumberBetween(0,10000000);
            int tradeQuantity = df.getNumberBetween(0,10000000);
            String fieldSeparator = ",";
            String s = businessEntity + fieldSeparator
                        + branchNumber + fieldSeparator
                        + accountNumber + fieldSeparator
                        + name + fieldSeparator
                        + address + fieldSeparator
                        + emailAddress + fieldSeparator
                        + securityNumber + fieldSeparator
                        + settlementQuantity + fieldSeparator
                        + tradeQuantity ;

            ls.add(s);
            if ( (i% 10000000) == 0) {
                Files.write(p,ls, StandardOpenOption.CREATE,StandardOpenOption.APPEND );
                ls = new ArrayList<String>();
            }
        }
        Files.write(p,ls, StandardOpenOption.CREATE,StandardOpenOption.APPEND );
    }
}
