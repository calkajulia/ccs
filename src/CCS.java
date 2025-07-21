import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CCS {
    public static void main(String[] args) {

        if(args.length != 1) {
            Log.log("Incorrect command. Usage: java -jar CCS.jar <port>");
            return;
        }

        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            Log.log("Incorrect command. Usage: java -jar CCS.jar <port>");
            return;
        }

        Thread serverDiscoveryThread = new Thread(new ServerDiscovery(port));
        serverDiscoveryThread.start();

        Stats stats = new Stats();
        Thread statsThread = new Thread(new StatsReporter(stats));
        statsThread.start();

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            ExecutorService clients = Executors.newCachedThreadPool();

            while(true) {
                Socket socket = serverSocket.accept();
                clients.submit(new ClientManager(socket, stats));
                stats.incrementConnectedClients();
            }
        } catch (IOException e) {
            Log.log("Program failed on connecting with clients." + e.getMessage());
        }
    }
}