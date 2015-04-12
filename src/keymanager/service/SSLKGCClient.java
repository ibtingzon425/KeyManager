package keymanager.service;

import java.io.*;
import javax.net.ssl.*;

/**
 * @author Isabelle Tingzon
 * @author Angelu Kaye Tiu
 */
public class SSLKGCClient{
    
    private final String HOST;
    private final String PUBKEY;
    private final String PWD;
    private final int PORT;
    
    private String USERID;
    private String ATTR;
    
    private DataInputStream  console   = null;
    private DataOutputStream streamOut = null;
    private DataInputStream streamIn =  null;
    private SSLSocketFactory factory;
    
    public SSLKGCClient(String host, int port, String pubkey, String password){
        String dir = System.getProperty("user.dir");
        HOST = host;
        PORT = port;
        PUBKEY = dir + "/SSLkeys/public.jks"; //PUBKEY = pubkey;
        PWD = "password"; //PWD = password;
    }
    
    //TODO find a way to send multiple files without having to close/open multiple client sockets.         
    public void fetchKeys(String userid, String attributes) throws SSLClientErrorException {
        USERID = userid;
        ATTR = attributes; 
        try {
            //Initialize SSL Properties
            System.setProperty("javax.net.ssl.trustStore", PUBKEY);
            System.setProperty("javax.net.ssl.keyStorePassword", PWD);
            factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            
            //MASTER_KEY
            SSLSocket socket = (SSLSocket) factory.createSocket(HOST, PORT);
            socket.startHandshake();
            streamOut = new DataOutputStream(socket.getOutputStream());
            streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));            
            
            //Client sends message "master_key"
            String writeLine = "master_key";
            streamOut.writeUTF(writeLine);
            streamOut.flush();
        
            //Client reads mk filesize
            String filesize = streamIn.readUTF();
            
            //Client recieves master_key and closes socket
            getFile(socket, writeLine, Integer.parseInt(filesize));
            socket.close();
            
            //PUBLIC_KEY
            socket = (SSLSocket) factory.createSocket(HOST, PORT);
            socket.startHandshake();
            streamOut = new DataOutputStream(socket.getOutputStream());
            streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            
            //Client sends message "public_key"
            writeLine = "pub_key";
            streamOut.writeUTF(writeLine);
            streamOut.flush();
            
            //Client reads pk filesize
            filesize = streamIn.readUTF();
        
            //Client recieves public_key and closes socket
            getFile(socket, writeLine, Integer.parseInt(filesize));
            socket.close();
            
            //ATTRIBUTES/SECRET_KEY
            socket = (SSLSocket) factory.createSocket(HOST, PORT);
            socket.startHandshake();
            streamOut = new DataOutputStream(socket.getOutputStream());
            streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        
            //Client sends message "secret_key", requesting for the secret key
            writeLine = "secret_key";
            streamOut.writeUTF(writeLine);
            streamOut.flush();
        
            //Client send Username
            writeLine = USERID;
            streamOut.writeUTF(writeLine);
            streamOut.flush();
            
            //Client sends Attributes
            writeLine = ATTR;
            streamOut.writeUTF(writeLine);
            streamOut.flush();
            
            //Client reads pk filesize
            filesize = streamIn.readUTF();
            
            getFile(socket, USERID, Integer.parseInt(filesize));
            socket.close();
            
            //Client sends message indicating to delete the key
            socket = (SSLSocket) factory.createSocket(HOST, PORT);
            socket.startHandshake();
            streamOut = new DataOutputStream(socket.getOutputStream());
            streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        
            writeLine = "remove";
            streamOut.writeUTF(writeLine);
            streamOut.flush();
            
            writeLine = USERID;
            streamOut.writeUTF(writeLine);
            streamOut.flush();
            
            socket.close();
         } catch (IOException | NumberFormatException e) {
            throw new SSLClientErrorException();
         }
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
