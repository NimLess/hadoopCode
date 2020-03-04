package hadoop.first;

import hadoop.fastio.FastReader;
import hadoop.fastio.FastWriter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * @author FancyKing
 */
public class Write {

    static FastReader fastReader = new FastReader(System.in);
    static FastWriter fastWriter = new FastWriter(System.out);

    public static void main(String[] args) throws IOException {


        fastWriter.print("The file name you want to create:" );
        String fileName = fastReader.nextLine();
        fastWriter.println("please type strings you want to write" );
        String typeIn = fastReader.nextLine();
        byte[] typeInByte = typeIn.getBytes();
        fastWriter.print("you are writing the following data to file " + fileName +
                "and status is " );

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
        FSDataOutputStream fsDataOutputStream = fileSystem.create(
                new Path(fileName)
        );
        fsDataOutputStream.write(typeInByte, 0, typeInByte.length);

        String[] checkList = fileName.split("");
        if (Check.isExist(checkList)) {
            fastWriter.println("Success");
        } else {
            fastWriter.println("Failed");
        }

        fastReader.close();
        fastWriter.close();

    }
}
