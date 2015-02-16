package keymanager;

import java.io.*;
import javax.net.ssl.*;

/**
 * @author Isabelle Tingzon
 * @author Angelu Kaye Tiu
 */
public class SSLClient{
    
    protected String HOST;
    protected String PUBKEY;
    protected String USERNAME;
    protected String PWD;
    protected int PORT;
    
    private DataInputStream  console   = null;
    private DataOutputStream streamOut = null;
    private DataInputStream streamIn =  null;
    private SSLSocketFactory factory;
    
    public SSLClient(String username, String host, int port, String pubkey, String password){
        String dir = System.getProperty("user.dir");
        USERNAME = username;
        HOST = host;
        PORT = port;
        //PUBKEY = pubkey;
        PUBKEY = dir + "/SSLkeys/public.jks";
        //PWD = password;
        PWD = "password";
    }
    
    //TODO find a way to send multiple files without having to close/open multiple client sockets.         
    public boolean generateKeys() throws Exception {
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
            System.out.println(filesize);
            
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
            System.out.println(filesize);
        
        //Client recieves public_key and closes socket
            getFile(socket, writeLine, Integer.parseInt(filesize));
            socket.close();
            
        //ATTRIBUTES/SECRET_KEY
            socket = (SSLSocket) factory.createSocket(HOST, PORT);
            socket.startHandshake();
            streamOut = new DataOutputStream(socket.getOutputStream());
            streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        
        //Client sends message "attributes", requesting for attributes
            writeLine = "attributes";
            streamOut.writeUTF(writeLine);
            streamOut.flush();
        
        //Client send Username
            writeLine = USERNAME;
            streamOut.writeUTF(writeLine);
            streamOut.flush();
            
        //Client sends message indicating to delete the keys
            /*writeLine = "delete";
            streamOut.writeUTF(writeLine);
            streamOut.flush();*/
            
            return true;
         } catch (Exception e) {
            e.printStackTrace();
         }
         return false;
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
    
    //This piece of code won't work for Binary Files :P
    /*  int bytes = 0;
        String filename = get_filename;
        put.println(filename);
        File file = new File(filename);
        OutputStream  fos = new FileOutputStream(new File(file.toString()));
        byte byte_array[] = new byte[1024];
        int totalbytes = 0;

        while(totalbytes <= fileSize){ 
            fos.write(byte_array, 0, bytes);
            bytes = get.read(byte_array, 0, 1024);
            totalbytes += bytes;
        } 
        fos.flush();
        System.out.println("File sucessfully received!");
        fos.close();*/
    
    private static void printSocketInfo(SSLSocket s) {
        System.out.println("   Socket class: " + s.getClass());
        System.out.println("   Remote address = " + s.getInetAddress().toString());
        System.out.println("   Remote port = " + s.getPort());
        System.out.println("   Local socket address = " + s.getLocalSocketAddress().toString());
        System.out.println("   Local address = " + s.getLocalAddress().toString());
        System.out.println("   Local port = " + s.getLocalPort());
        System.out.println("   Need client authentication = " + s.getNeedClientAuth());
        SSLSession ss = s.getSession();
        System.out.println("   Protocol = "+ss.getProtocol());
   }
}
