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
    
    private final String ACCESSKEYID;
    private final String SECRETKEY;
    private final String USERID;
    private final int MODE;
    
    private AmazonS3 s3client;
    private CommandDaoBethenImpl cmd; 
    private CommandDaoPiratteImpl com;
    
    public SSLProxyClient proxyClient;
        
    public AWSAPI (String userId, String accessId, String secretkey, int mode){ 
        this.ACCESSKEYID = accessId;
        this.SECRETKEY = secretkey;
        this.USERID = userId;
        this.MODE = mode;
        
        AWSCredentials credentials = new BasicAWSCredentials(ACCESSKEYID, SECRETKEY);  
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTPS);
        clientConfig.setProxyHost("proxy8.upd.edu.ph");
        clientConfig.setProxyPort(8080); 
        
        s3client = new AmazonS3Client(credentials); 
        cmd = new CommandDaoBethenImpl();
        com = new CommandDaoPiratteImpl();;
    }
    
    public void setProxy(String PROXYHOST, int PROXYPORT, String PUBKEY, String PWD){
        proxyClient = new SSLProxyClient(PROXYHOST, PROXYPORT, PUBKEY, PWD);
    }
    
    public void upload(String filename, String policy, String bucketname) throws IOException, CommandFailedException{                
        String pubkey_loc = System.getProperty("user.dir") + "/pub_key";
        
        if (this.MODE == 0){
            com.encrypt(pubkey_loc, filename, policy);
            File uploadFileABE = new File(filename + ".cpabe");
            File uploadFileAES = new File(filename + ".cpaes");

            if(uploadFileABE.exists() && uploadFileAES.exists()) {
                System.out.println("Uploading " + filename + "...");
                s3client.putObject(bucketname, uploadFileABE.getName(), uploadFileABE);
                s3client.putObject(bucketname, uploadFileAES.getName(), uploadFileAES);
                System.out.println(uploadFileABE.getName() + " upload success.");
                com.remove(uploadFileABE.getAbsolutePath());
                com.remove(uploadFileAES.getAbsolutePath());
            }
        }
        else{
            cmd.encrypt(pubkey_loc, filename, policy);
            File uploadFileABE = new File(filename + ".cpabe");
            if(uploadFileABE.exists()){
                System.out.println("Uploading " + filename + "...");
                s3client.putObject(bucketname, uploadFileABE.getName(), uploadFileABE);
                System.out.println(uploadFileABE.getName() + " upload success.");
                cmd.remove(uploadFileABE.getAbsolutePath());
            }                
        }       
    }
    
    public void revoke(String filename, String[] revoked_users) throws SSLClientErrorException, NoSuchAlgorithmException, IOException{
        if (MODE == 0){
            proxyClient.proxyRevoke(filename, revoked_users);
            System.out.println("Revoked users");
        }
    }
           
    public void download(String filename, String bucketname, String dest) throws CommandFailedException, IOException, FileNotFoundException, SSLClientErrorException, NoSuchAlgorithmException{
        String pubkey_loc = System.getProperty("user.dir") + "/pub_key";
        String secretkey_loc = System.getProperty("user.dir") + "/" + this.USERID;
        String destfile = dest + "/" +filename;
                
        if (MODE == 0){
            String lambda_k_loc = System.getProperty("user.dir") + "/" + this.USERID + "lambda_k";
            String destfileproxy = dest + "/" + filename + ".proxy";
                        
            String filenameABE = filename;
            this.fetch(bucketname, filenameABE, dest);
            String filenameAES = filenameABE;
            filenameAES = filenameAES.replaceAll(".cpabe", ".cpaes");
            this.fetch(bucketname, filenameAES, dest);
            
            System.out.println("Preparing to decrypt...");
            this.decrypt(destfile, lambda_k_loc, pubkey_loc, secretkey_loc, destfileproxy);
            com.remove(destfile);
        }
        else{
            String filenameABE = filename;
            this.fetch(bucketname, filenameABE, dest);
            cmd.decrypt(pubkey_loc, secretkey_loc, "", destfile);
        }
    }
    
    private void decrypt(String destfile, String lambda_k_loc, String pubkey_loc, String secretkey_loc, String  destfileproxy) throws SSLClientErrorException, NoSuchAlgorithmException, CommandFailedException{
        proxyClient.proxyReEncrypt(USERID, destfile);
        com.decrypt(pubkey_loc, secretkey_loc, lambda_k_loc, destfileproxy);
    }
    
    private void fetch(String bucketname, String filename, String dest) throws FileNotFoundException, IOException, CommandFailedException, SSLClientErrorException, NoSuchAlgorithmException{
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
    
    public List<String> getBucketList(){
        try{
        List<String> bucketList = new ArrayList<String>();
        for (Bucket bucket : s3client.listBuckets()) {
            bucketList.add(bucket.getName());
        }
        return bucketList;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
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
}