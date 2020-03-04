package hadoop.first;

import hadoop.fastio.FastReader;
import hadoop.fastio.FastWriter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * @author FancyKing
 */
public class Check {

    static Configuration configuration = new Configuration();
    static FastWriter fastWriter = new FastWriter(System.out);
    static FastReader fastReader = new FastReader(System.in);

    public static void main(String[] args) throws IOException {

        configuration.set(
                "fs.defaultFS",
                "hdfs://127.0.0.1:9000"
        );
        configuration.set(
                "fs.hdfs.impl",
                "org.apache.hadoop.hdfs.DistributedFileSystem"
        );

        String[] extraInput;
        fastWriter.println("make the var");
        if (args.length == 0) {
            fastWriter.println("Please input" );
            extraInput = fastReader.nextLine().split("," );
        } else {
            extraInput = args;
        }
        fastWriter.println("go to sub function");
        isExist(extraInput);
    }

    public static boolean isExist(String[] fileNames) throws IOException {

        FileSystem fileSystem = FileSystem.get(configuration);
        boolean flagAllExist = true;

        for (String fileName : fileNames) {
            if (fileSystem.exists(new Path(fileName))) {
                fastWriter.println(fileName + " is do exist" );
            } else {
                flagAllExist = false;
                fastWriter.println(fileName + " is not exist" );
            }
        }
        return flagAllExist;
    }
}
