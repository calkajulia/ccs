public class Stats {

    private int CONNECTED_CLIENTS = 0;
    private int TOTAL_OPERATIONS = 0;
    private int ADD_OPERATIONS = 0;
    private int SUB_OPERATIONS = 0;
    private int MUL_OPERATIONS = 0;
    private int DIV_OPERATIONS = 0;
    private int ERROR_OPERATIONS = 0;
    private int SUM_OF_RESULTS = 0;

    private int LAST_10_SEC_CONNECTED_CLIENTS = 0;
    private int LAST_10_SEC_TOTAL_OPERATIONS = 0;
    private int LAST_10_SEC_ADD_OPERATIONS = 0;
    private int LAST_10_SEC_SUB_OPERATIONS = 0;
    private int LAST_10_SEC_MUL_OPERATIONS = 0;
    private int LAST_10_SEC_DIV_OPERATIONS = 0;
    private int LAST_10_SEC_ERROR_OPERATIONS = 0;
    private int LAST_10_SEC_SUM_OF_RESULTS = 0;

    public synchronized void incrementConnectedClients() {
        CONNECTED_CLIENTS++;
        LAST_10_SEC_CONNECTED_CLIENTS++;
    }
    public synchronized void incrementAddOperations() {
        ADD_OPERATIONS++;
        LAST_10_SEC_ADD_OPERATIONS++;
        TOTAL_OPERATIONS++;
        LAST_10_SEC_TOTAL_OPERATIONS++;
    }
    public synchronized void incrementSubOperations() {
        SUB_OPERATIONS++;
        LAST_10_SEC_SUB_OPERATIONS++;
        TOTAL_OPERATIONS++;
        LAST_10_SEC_TOTAL_OPERATIONS++;
    }

    public synchronized void incrementMulOperations() {
        MUL_OPERATIONS++;
        LAST_10_SEC_MUL_OPERATIONS++;
        TOTAL_OPERATIONS++;
        LAST_10_SEC_TOTAL_OPERATIONS++;
    }

    public synchronized void incrementDivOperations() {
        DIV_OPERATIONS++;
        LAST_10_SEC_DIV_OPERATIONS++;
        TOTAL_OPERATIONS++;
        LAST_10_SEC_TOTAL_OPERATIONS++;
    }

    public synchronized void incrementErrorOperations() {
        ERROR_OPERATIONS++;
        LAST_10_SEC_ERROR_OPERATIONS++;
    }

    public synchronized void incrementSumOfResults(int result) {
        SUM_OF_RESULTS += result;
        LAST_10_SEC_SUM_OF_RESULTS += result;
    }

    public synchronized String getStats() {
        String stats = "--- STATS ---\n" +
                "Stats since start | Last 10s stats\n\n" +
                "Connected clients: " + CONNECTED_CLIENTS + " | " + LAST_10_SEC_CONNECTED_CLIENTS + '\n' +
                "Total operations: " + TOTAL_OPERATIONS + " | " + LAST_10_SEC_TOTAL_OPERATIONS + '\n' +
                "   ADD: " + ADD_OPERATIONS + " | " + LAST_10_SEC_ADD_OPERATIONS + '\n' +
                "   SUB: " + SUB_OPERATIONS + " | " + LAST_10_SEC_SUB_OPERATIONS + '\n' +
                "   MUL: " + MUL_OPERATIONS + " | " + LAST_10_SEC_MUL_OPERATIONS + '\n' +
                "   DIV: " + DIV_OPERATIONS + " | " + LAST_10_SEC_DIV_OPERATIONS + '\n' +
                "ERROR operations: " + ERROR_OPERATIONS + " | " + LAST_10_SEC_ERROR_OPERATIONS + '\n' +
                "Sum of results: " + SUM_OF_RESULTS + " | " + LAST_10_SEC_SUM_OF_RESULTS + '\n' +
                "-------------";

        LAST_10_SEC_CONNECTED_CLIENTS = 0;
        LAST_10_SEC_TOTAL_OPERATIONS = 0;
        LAST_10_SEC_ADD_OPERATIONS = 0;
        LAST_10_SEC_SUB_OPERATIONS = 0;
        LAST_10_SEC_MUL_OPERATIONS = 0;
        LAST_10_SEC_DIV_OPERATIONS = 0;
        LAST_10_SEC_ERROR_OPERATIONS = 0;
        LAST_10_SEC_SUM_OF_RESULTS = 0;

        return stats;
    }
}
