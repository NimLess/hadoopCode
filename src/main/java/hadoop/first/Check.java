package hadoop.first;

import hadoop.fastio.FastReader;
import hadoop.fastio.FastWriter;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author FancyKing
 */
public class Check {

    static FastWriter fastWriter = Config.fastWriter;
    static FastReader fastReader = Config.fastReader;

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        String[] extraInput;
        if (args.length == 0) {

            fastWriter.println("Please enter the name of the file you want to retrieval:");
            extraInput = fastReader.nextLine().split(Config.splitChar);
        } else {
            extraInput = args;
        }
        fastWriter.println("We are retrieving the file directory.");
        boolean flagExist = isExist(extraInput);

        fastWriter.print("File retrieval status is: ");
        if (!flagExist) {
            fastWriter.println("NO File.");
        } else {
            fastWriter.println("Have File.");
        }
    }

    public static boolean isExist(String[] fileNames) throws IOException, URISyntaxException, InterruptedException {

        FileSystem fileSystem = Config.getFileSystem();
        int flagAllExist = 0;

        for (String fileName : fileNames) {

            if (fileSystem.exists(new Path(fileName))) {
                flagAllExist += 1;
            }
        }
        return (flagAllExist == fileNames.length);
    }
}
