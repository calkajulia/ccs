import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CCS {
    public static void main(String[] args) {

        if(!validateArgs(args)) {
            System.err.println("Usage: java -jar CCS.jar <port>");
            System.err.println("Where <port> is TCP/UDP port number");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        Thread serverDiscoveryThread = new Thread(new ServerDiscovery(port));
        serverDiscoveryThread.start();

        Stats stats = new Stats();
        Thread statsThread = new Thread(new StatsReporter(stats));
        statsThread.start();

        try(ServerSocket serverSocket = new ServerSocket(port)) {
            ExecutorService clients = Executors.newCachedThreadPool();

            Log.log("TCP server socket started on port " + port + ".");

            while(true) {
                Socket socket = serverSocket.accept();
                clients.submit(new ClientManager(socket, stats));
                stats.incrementConnectedClients();
            }
        } catch (IOException e) {
            Log.log("Error starting TCP server: " + e.getMessage());
            System.exit(1);
        }
    }

    private static boolean validateArgs(String[] args) {
        if(args.length != 1) {
            return false;
        }

        try {
            int port = Integer.parseInt(args[0]);
            if(port < 1 || port > 65535) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}