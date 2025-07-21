import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerDiscovery implements Runnable {

    private int port;

    public ServerDiscovery(int port){
        this.port = port;
    }

    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(port);
            while(true){
                byte[] messageBuffer = new byte[1024];
                DatagramPacket messagePacket = new DatagramPacket(messageBuffer, messageBuffer.length);
                socket.receive(messagePacket);
                String message = new String(messagePacket.getData(), 0, messagePacket.getLength());

                if(message.startsWith("CCS DISCOVER")){
                    Log.log("Discovery message received: " + message);

                    String response = "CCS FOUND";
                    byte[] responseBytes = response.getBytes();
                    DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length);
                    responsePacket.setAddress(messagePacket.getAddress());
                    responsePacket.setPort(messagePacket.getPort());
                    socket.send(responsePacket);
                    Log.log("Discovery response sent: " + response);
                }
            }
        } catch (SocketException e) {
            Log.log("Program failed on server discovery" + e.getMessage());
        } catch (IOException e) {
            Log.log("Program failed on server discovery" + e.getMessage());
        }
    }
}