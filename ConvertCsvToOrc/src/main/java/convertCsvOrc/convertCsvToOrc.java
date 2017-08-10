package convertCsvOrc;

import org.apache.crunch.types.orc.OrcUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.orc.OrcFile;
import org.apache.hadoop.hive.ql.io.orc.OrcStruct;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.hive.ql.io.orc.Writer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class convertCsvToOrc {

	public static void main(String[] args) throws IOException {
		// download winutils.exe from hadoop git, create a hadoop folder
		// and create a bin within that then place winutils.exe in there.
		// Replace second argument with your location of that folder.
		System.setProperty("hadoop.home.dir", "C:/Hadoop");

		convertCsvToOrc test = new convertCsvToOrc();

		// configure the input and output path, include the file name in output
		test.createOrcFile("C:/test/test1.csv", "C:/test/test1.csv.orc");
		System.out.println("Orc file creation successful.");
	}

	private void createOrcFile(String input, String output) throws IOException {
		// configure schema
		// The schema of your CSV file needs to be given here
		// If your file is of schema a, b, c and all are string then the schema would be as below
        // struct<a:string, b:string, c:string>
		String typeStr = "struct<businessEntity:string,branchNumber:int,accountNumber:int,name:string,address:string,emailAddress:string,securityNumber:string,settlementQuantity:int,tradeQuantity:int>";
		TypeInfo typeInfo = TypeInfoUtils.getTypeInfoFromTypeString(typeStr);
		ObjectInspector inspector = OrcStruct.createObjectInspector(typeInfo);

		Path outputPath = new Path(output);
		Configuration conf = new Configuration();
		Writer writer = OrcFile.createWriter(outputPath,
				OrcFile.writerOptions(conf).inspector(inspector).stripeSize(100000).bufferSize(10000));
		File fin = new File(input);
		FileInputStream fis = new FileInputStream(fin);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		String line;
		while ((line = br.readLine()) != null) {
			// enter the char(s) that your file is separated by.
            // The program can be improved by using openCSV library, for simplicity sake we are not using it here.
			String[] inputTokens = line.split(",");

			// configure to match the schema
            OrcStruct orcLine = OrcUtils.createOrcStruct(typeInfo
                    , new Text(inputTokens[0])
                    , new IntWritable(Integer.parseInt(inputTokens[1]))
                    , new IntWritable(Integer.parseInt(inputTokens[2]))
                    , new Text(inputTokens[3])
                    , new Text(inputTokens[4])
                    , new Text(inputTokens[5])
                    , new Text(inputTokens[6])
                    , new IntWritable(Integer.parseInt(inputTokens[7]))
                    , new IntWritable(Integer.parseInt(inputTokens[8])));
			writer.addRow(orcLine);
		}
		writer.close();
		br.close();
	}
}
