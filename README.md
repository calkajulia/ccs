# ccs - centralized computing system

This application implements a centralized computational server that provides arithmetic operations for multiple concurrent clients. The system uses both UDP and TCP protocols for service discovery and client communication.

## System Architecture

The application implements a **SERVER** that provides computational services:

* **SERVER (CCS)** - central computational server that provides three main functionalities:
    - Service discovery via UDP
    - Client communication via TCP
    - Statistics reporting

*Client applications are separate projects.*

## Server Functionalities

### 1. Service Discovery (UDP)
* Opens UDP socket on the specified port
* Listens for discovery messages starting with `"CCS DISCOVER"`
* Responds with `"CCS FOUND"` message to the sender
* Allows clients to locate the server on the local network

### 2. Client Communication (TCP)
* Opens TCP socket on the same port as UDP
* Accepts unlimited number of concurrent client connections
* Processes arithmetic operations and returns results
* Supports four operations: ADD, SUB, MUL, DIV

### 3. Statistics Reporting
* Collects comprehensive statistics during operation
* Reports statistics every 10 seconds to console
* Tracks both total statistics and last 10-second intervals

## Communication Protocol

### Service Discovery Protocol (UDP)
* **Client discovery message**: `"CCS DISCOVER"` (sent via UDP broadcast)
* **Server response**: `"CCS FOUND"` (sent back to client)

### Arithmetic Operations Protocol (TCP)
* **Client request format**: `"<OPERATION> <ARG1> <ARG2>"`
    - `<OPERATION>`: ADD, SUB, MUL, or DIV
    - `<ARG1>`, `<ARG2>`: integer arguments
* **Server responses**:
    - **Success**: integer result
    - **Error**: `"ERROR"` (invalid format, division by zero, invalid operation)

## Compilation and Execution

```bash
# Compilation
javac src/*.java -d .

# Create JAR file
jar cfm CCS.jar manifest.txt *.class

# Server execution
java -jar CCS.jar <port>
```

**Parameters:**
* `<port>`: UDP/TCP port number (1-65535) for server operation

## Collected Statistics

The server tracks and reports:
* **Connected clients**: Number of new client connections
* **Total operations**: Count of all processed operations
* **Operation breakdown**: Individual counts for ADD, SUB, MUL, DIV
* **Error operations**: Count of failed/invalid operations
* **Sum of results**: Total sum of all computed results

Statistics are displayed in two columns:
- **Total since startup** | **Last 10 seconds**