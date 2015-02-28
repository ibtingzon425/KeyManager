package keymanager.service;

import java.io.*;
import javax.net.ssl.*;

/**
 * @author Isabelle Tingzon
 * @author Angelu Kaye Tiu
 */
public class SSLProxyClient{
    
    private String HOST;
    private String PUBKEY;
    private String USERNAME;
    private String PWD;
    private int PORT;
        
    public SSLProxyClient(String username, String host, int port, String pubkey, String password){
        USERNAME = username;
        HOST = host;
        PORT = port;
        String dir = System.getProperty("user.dir");
        PUBKEY = dir + "/SSLkeys/public.jks";
        PWD = "password";
        //PUBKEY = pubkey;
        //PWD = password;
    }
    
    //TODO find a way to send multiple files without having to close/open multiple client sockets.         
    public void generateKeys() throws SSLClientErrorException {
         try {
            //Initialize SSL Properties
            System.setProperty("javax.net.ssl.trustStore", PUBKEY);
            System.setProperty("javax.net.ssl.keyStorePassword", PWD);
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            
            SSLSocket socket = (SSLSocket) factory.createSocket(HOST, PORT);
            socket.startHandshake();
            DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
            DataInputStream  streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream())); 
            
            File newFile = new File("a.txt.cpabe");
             
            //Client sends message
            String message = "re-encrypt";
            streamOut.writeUTF(message);
            streamOut.flush();
            
            //Client sends username
            String username = USERNAME;
            streamOut.writeUTF(username);
            streamOut.flush();
            
            //Client sends message "<filename>"
            String filename = newFile.getName();
            streamOut.writeUTF(filename);
            streamOut.flush();
            
            //Client sends message "<filesize>"            
            String filesize = "" + (int)newFile.length();
            streamOut.writeUTF(filesize);
            streamOut.flush();
            
            //Client sends file to encrypt
            this.sendFile(socket, newFile);
                        
            //Client receives file size of proxy file
            String line = streamIn.readUTF();
            int proxy_filesize = Integer.parseInt(line);
            System.out.println(proxy_filesize);
            
            //Client receives file name of proxy file
            String proxy_filename = streamIn.readUTF();
            System.out.println(proxy_filename);
            
            //Client receives file and closes socket
            this.getFile(socket, proxy_filename, proxy_filesize);
            socket.close();
            
            //Client opens new connection in able to retrive lambda_k
            socket = (SSLSocket) factory.createSocket(HOST, PORT);
            socket.startHandshake();
            streamOut = new DataOutputStream(socket.getOutputStream());
            streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream())); 

            //Client sends message to retrive lambda-k
            message = "lambda-k";
            streamOut.writeUTF(message);
            streamOut.flush();
            
            //Client sends username
            streamOut.writeUTF(username);
            streamOut.flush();
            
            //Client sends message "<filename>"
            streamOut.writeUTF(filename);
            streamOut.flush();
            
            //Client receives lambda-k file size
            line = streamIn.readUTF();
            int lambda_filesize = Integer.parseInt(line);
            System.out.println(lambda_filesize);
            
            //Client receives lambda-k file name
            String lambda_filename = streamIn.readUTF();
            System.out.println(lambda_filename);
            
            this.getFile(socket, lambda_filename, lambda_filesize);
            socket.close();
            
         } catch (IOException | NumberFormatException e) {
            throw new SSLClientErrorException();
         }
    }
    
    private void sendFile(SSLSocket socket, File myFile) throws FileNotFoundException, IOException {     
        FileInputStream fis = new FileInputStream(myFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[(int)myFile.length()];

        int count;
        while ((count = bis.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.flush();
        //out.close();
        //fis.close();
        //bis.close();
    }
   
    private void getFile(SSLSocket socket, String get_filename, int fileSize) throws IOException{
        BufferedInputStream get = new BufferedInputStream(socket.getInputStream());
        PrintWriter put = new PrintWriter(socket.getOutputStream(),true);
        
        // receive file
        byte [] mybytearray  = new byte [fileSize];
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream(get_filename);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int bytesRead = is.read(mybytearray,0,mybytearray.length);
        int current = bytesRead;

        do {
           bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
           if(bytesRead >= 0) current += bytesRead;
        } while(current < fileSize);

        bos.write(mybytearray, 0 , current);
        bos.flush();
        System.out.println("File " + get_filename
            + " downloaded (" + current + " bytes read)");
    } 
}
