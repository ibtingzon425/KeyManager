package keymanager.service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.*;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import keymanager.dao.CommandDaoBethenImpl;
import keymanager.dao.CommandDaoPiratteImpl;
import keymanager.dao.CommandFailedException;

/**
 * @author Isabelle Tingzon
 */
public class AWSAPI {
    
    private String ACCESSKEYID;
    private String SECRETKEY;
    
    private AmazonS3 s3client;
    private CommandDaoBethenImpl cmd; 
    private CommandDaoPiratteImpl com;
        
    public AWSAPI (String id, String key){
        this.ACCESSKEYID = id;
        this.SECRETKEY = key;
        AWSCredentials credentials = new BasicAWSCredentials(ACCESSKEYID, SECRETKEY);  
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTPS);
        clientConfig.setProxyHost("proxy8.upd.edu.ph");
        clientConfig.setProxyPort(8080);      
        s3client = new AmazonS3Client(credentials); 
        cmd = new CommandDaoBethenImpl();
        com = new CommandDaoPiratteImpl();
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
    
    public void download(String bucketname, String filename, String dest) throws FileNotFoundException, IOException, CommandFailedException, SSLClientErrorException, NoSuchAlgorithmException{
        System.out.println("Downloading " + filename + "...");
        
        S3Object s3object = s3client.getObject(new GetObjectRequest(bucketname, filename));
        System.out.println("Content-Type: "  + 	s3object.getObjectMetadata().getContentType());
        InputStream reader = new BufferedInputStream(s3object.getObjectContent());
        File filedest = new File(dest + "/" + filename);      
        OutputStream writer = new BufferedOutputStream(new FileOutputStream(filedest));

        int read = -1;
        while ( ( read = reader.read() ) != -1 ) {
            writer.write(read);
        }

        writer.flush();
        writer.close();
        reader.close();
    }
        
    public void upload(String filename, String policy, String bucketname) throws IOException, CommandFailedException{                
        this.encrypt(filename, policy);
        File uploadFileABE = new File(filename + ".cpabe");
        File uploadFileAES = new File(filename + ".cpaes");
        if(uploadFileABE.exists() && uploadFileAES.exists()) {
            System.out.println("Uploading " + filename + "...");
            s3client.putObject(bucketname, uploadFileABE.getName(), uploadFileABE);
            s3client.putObject(bucketname, uploadFileAES.getName(), uploadFileAES);
        }
        System.out.println(uploadFileABE.getName() + " upload success.");
    }
    
    public void encrypt(String filename, String policy) throws CommandFailedException{
        //cmd.encrypt(System.getProperty("user.dir") + "/pub_key", filename, policy);
        com.encrypt(System.getProperty("user.dir") + "/pk", filename, policy);
    }
    
    public void decrypt(String filename, String dest) throws CommandFailedException{
        //cmd.decrypt(System.getProperty("user.dir") + "/pub_key", "issas_key", "", file.getAbsolutePath());
        com.decrypt(System.getProperty("user.dir") + "/pk", System.getProperty("user.dir") + "/aa", System.getProperty("user.dir") + "/aalambda_k", dest + "/" + filename + ".proxy");
    }
    
    
}