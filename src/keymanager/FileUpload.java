package keymanager;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.*;
import com.amazonaws.auth.profile.*;
import com.amazonaws.services.cloudsearchdomain.model.Bucket;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * @author Isabelle Tingzon
 */
public class FileUpload {
    
    private static final String SUFFIX = "/";
    
    public FileUpload(){}
    
    public void uploadFile(String filename){
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTPS);
        clientConfig.setProxyHost("proxy8.upd.edu.ph");
        clientConfig.setProxyPort(8080);
        
        try{ 
            AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider(), clientConfig);      
            //AWSCredentials credentials = new BasicAWSCredentials("AKIAIDBFDXXR7KADZVVQ", "Aic/9zYXXE5uspp38w+9yFKKo3JlRhA6dydlpTWP");
            //AmazonS3 s3client = new AmazonS3Client(credentials, clientConfig);
            
            System.out.println("Client instance created.");
            String bucketname = "cs199-testbucket";
            s3client.putObject(bucketname, filename, new File(filename));
            
            System.out.println("Upload success.");
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void createFolder(String bucketName, String folderName, AmazonS3 client) {
        // create meta-data for your folder and set content-length to 0
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        
        // create empty content
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        
        // create a PutObjectRequest passing the folder name suffixed by /
        PutObjectRequest putObjectRequest = new PutObjectRequest
            (bucketName, folderName + SUFFIX, emptyContent, metadata);
        
        // send request to S3 to create folder
        client.putObject(putObjectRequest);
    }
}
