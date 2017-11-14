package kafka;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.kafka.clients.producer.*;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class KafkaTest {


    private static void exec(final int th) throws InterruptedException {

        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(th);
        for (int i=0; i<th; i++) {
            Future future = executorService.submit(new Runnable() {
                public void run() {
                    String s = "";
                    for (int j=0; j<10000/th; j++) s=s+"0";
                    System.out.println(" th= "+th + " s = "+s);
                }
            });
        }
        executorService.shutdown();
        int ct = 0;
        while (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
            if ((ct += 100)%1000==0) System.out.println("exec yet ..");
        }
        long timeSpent = System.currentTimeMillis() - start;
        System.out.println(" timeSpent " + timeSpent + " ms ------------------------");
    }

/*
    public static void main(String[] args) throws InterruptedException {

        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.143:9092");
        props.put("acks", "all");
        props.put("retries", 1);
        props.put("batch.size", 16);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for(int i = 0; i < 1; i++)
            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));

        producer.close();


        for (int i=1; i<101; i++) exec(i);
}*/

    private static final String NAME_NODE = "hdfs://localhost:8020";//nameNomeHost = localhost if you use hadoop in local mode
    private static final Logger logger = Logger.getLogger("kafka.KafkaTest");

    public static void main(String[] args) throws URISyntaxException, IOException {

        String hdfsuri = NAME_NODE;
        String path="/user/hdfs/example/hdfs/";
        String fileName="hello.csv";
        String fileContent="hello;world";

        // ====== Init HDFS File System Object
        Configuration conf = new Configuration();
        // Set FileSystem URI
        conf.set("fs.defaultFS", hdfsuri);
        // Because of Maven
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        // Set HADOOP user
        System.setProperty("HADOOP_USER_NAME", "hdfs");
        System.setProperty("hadoop.home.dir", "/");
        //Get the filesystem - HDFS
        FileSystem fs = FileSystem.get(URI.create(hdfsuri), conf);

        //==== Create folder if not exists
        Path workingDir=fs.getWorkingDirectory();
        Path newFolderPath= new Path(path);
        if(!fs.exists(newFolderPath)) {
            // Create new Directory
            fs.mkdirs(newFolderPath);
            logger.info("Path "+path+" created.");
        }

        //==== Write file
        logger.info("Begin Write file into hdfs");
        //Create a path
        Path hdfswritepath = new Path(newFolderPath + "/" + fileName);
        //Init output stream
        FSDataOutputStream outputStream=fs.create(hdfswritepath);
        //Cassical output stream usage
        outputStream.writeBytes(fileContent);
        outputStream.close();
        logger.info("End Write file into hdfs");

        //==== Read file
        logger.info("Read file into hdfs");
        //Create a path
        Path hdfsreadpath = new Path(newFolderPath + "/" + fileName);
        //Init input stream
        FSDataInputStream inputStream = fs.open(hdfsreadpath);
        //Classical input stream usage
        String out= IOUtils.toString(inputStream, "UTF-8");
        logger.info(out);
        inputStream.close();
        fs.close();

    }



}
