package firest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * @author FancyKing
 */
public class Check {
    public static void main(String[] args) {
        try {
            String fileName = "fileName";
            Configuration configuration = new Configuration();
            configuration.set(
                    "fs.defaultFS",
                    "hdfs://127.0.0.1:9000"
            );
            configuration.set(
                    "fs.hdfs.impl",
                    "org.apache.hadoop.hdfs.DistributedFileSystem"
            );
            FileSystem fileSystem = FileSystem.get(configuration);

            if (fileSystem.exists(new Path(fileName))) {
                System.out.println("The file " + fileName + " is exist");
            } else {
                System.out.println("The file " + fileName + " is missing");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
