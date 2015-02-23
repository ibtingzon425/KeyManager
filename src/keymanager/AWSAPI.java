package keymanager;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.*;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import keymanager.dao.CommandDaoBethenImpl;
import keymanager.dao.CommandFailedException;

/**
 * @author Isabelle Tingzon
 */
public class AWSAPI {
    
    private AmazonS3 s3client;
    private CommandDaoBethenImpl cmd; 
    
    private String ACCESSKEYID;
    private String SECRETKEY;
    
    public AWSAPI (String id, String key){
        //AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider(), clientConfig);      
        this.ACCESSKEYID = id;
        this.SECRETKEY = key;
        AWSCredentials credentials = new BasicAWSCredentials
            (ACCESSKEYID, SECRETKEY);  
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTPS);
        clientConfig.setProxyHost("proxy8.upd.edu.ph");
        clientConfig.setProxyPort(8080);
        s3client = new AmazonS3Client(credentials,clientConfig); 
        cmd = new CommandDaoBethenImpl();
    }
    
    public List<String> getBucketList(){
        List<String> bucketList = new ArrayList<String>();
        for (Bucket bucket : s3client.listBuckets()) {
            bucketList.add(bucket.getName());
        }
        return bucketList;
    }
    
    public List<String> listFiles(String bucketname){
        List<String> fileList = new ArrayList<String>();
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
        .withBucketName(bucketname);
        ObjectListing objectListing;

        do {
            objectListing = s3client.listObjects(listObjectsRequest);
            for (S3ObjectSummary objectSummary : 
                objectListing.getObjectSummaries()) {
                fileList.add(objectSummary.getKey());
            }
            listObjectsRequest.setMarker(objectListing.getNextMarker());
        } while (objectListing.isTruncated());
        return fileList;
    }
    
    public void downloadFile(String bucketname, String filename, String dest) throws FileNotFoundException, IOException, CommandFailedException{
        System.out.println("Downloading " + filename + "...");
        S3Object s3object = s3client.getObject(new GetObjectRequest(bucketname, filename));
        System.out.println("Content-Type: "  + 	s3object.getObjectMetadata().getContentType());
        InputStream reader = new BufferedInputStream(s3object.getObjectContent());
        File file = new File(dest + "/" + filename);      
        OutputStream writer = new BufferedOutputStream(new FileOutputStream(file));

        int read = -1;
        while ( ( read = reader.read() ) != -1 ) {
            writer.write(read);
        }

        writer.flush();
        writer.close();
        reader.close();
        cmd.decrypt(System.getProperty("user.dir") + "/pub_key", "issas_key", "", file.getAbsolutePath());
        
    }
    
    public void uploadFile(String filename, String policy, String bucketname){               
        try{  
            cmd.encrypt(System.getProperty("user.dir") + "/pub_key", filename, policy);
            File uploadFile = new File(filename + ".cpabe");
            if(uploadFile.exists()) {
                System.out.println("Uploading " + filename + "...");
                s3client.putObject(bucketname, uploadFile.getName(), uploadFile);
                System.out.println(uploadFile.getName() + " upload success.");
            }
        } catch(Exception ex){ ex.printStackTrace(); }
    }
}
