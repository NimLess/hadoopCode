package hadoop.first;

import hadoop.fastio.FastReader;
import hadoop.fastio.FastWriter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author FancyKing
 */
public class Config {
    public static String hdfsPath = "hdfs://server:9000/user/hadoop511/";
    public static String hdfsUser = "hadoop511";
    public static Configuration configuration = new Configuration();
    public static String splitChar = ",";
    public static FastReader fastReader = new FastReader(System.in);
    public static FastWriter fastWriter = new FastWriter(System.out);

    static {
        configuration.set(
                "fs.defaultFS",
                Config.hdfsPath
        );
        configuration.set(
                "fs.hdfs.impl",
                "org.apache.hadoop.hdfs.DistributedFileSystem"
        );
        configuration.set(
                "dfs.client.block.write.replace-datanode-on-failure.policy",
                "NEVER"
        );
        configuration.set(
                "dfs.client.use.datanode.hostname",
                "true"
        );
    }

    public static FileSystem getFileSystem() throws URISyntaxException, IOException, InterruptedException {
        return FileSystem.get(
                new URI(Config.hdfsPath),
                Config.configuration,
                Config.hdfsUser
        );
    }

    @Override
    protected void finalize() throws Throwable {
        fastWriter.println("exit");
        fastWriter.close();
        super.finalize();
    }
}
