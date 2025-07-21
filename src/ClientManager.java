import java.io.*;
import java.net.Socket;

public class ClientManager implements Runnable {

    private Socket socket;
    private Stats stats;

    public ClientManager(Socket socket, Stats stats) {
        this.socket = socket;
        this.stats = stats;
    }

    @Override
    public void run() {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = reader.readLine();
            while(message != null) {
                Log.log("Message received: " + message);
                String response = calculateResponse(message.split(" "));
                writer.write(response + "\n");
                writer.flush();
                Log.log("Response sent: " + response);
                message = reader.readLine();
            }
        } catch (IOException e) {
            Log.log("Program failed on managing clients." + e.getMessage());
        }
    }

    private String calculateResponse(String[] message) {;
        if(message.length != 3) {
            stats.incrementErrorOperations();
            return "ERROR";
        }

        int arg1, arg2;
        try {
            arg1 = Integer.parseInt(message[1]);
            arg2 = Integer.parseInt(message[2]);
        } catch(NumberFormatException e) {
            stats.incrementErrorOperations();
            return "ERROR";
        }
        if(arg2 == 0) {
            stats.incrementErrorOperations();
            return "ERROR";
        }

        int result;

        String operation = message[0];
        switch(operation) {
            case "ADD":
                result = arg1 + arg2;
                stats.incrementAddOperations();
                break;
            case "SUB":
                result = arg1 - arg2;
                stats.incrementSubOperations();
                break;
            case "MUL":
                result = arg1 * arg2;
                stats.incrementMulOperations();
                break;
            case "DIV":
                result = arg1 / arg2;
                stats.incrementDivOperations();
                break;
            default:
                stats.incrementErrorOperations();
                return "ERROR";
        }
        stats.incrementSumOfResults(result);
        return String.valueOf(result);
    }
}
