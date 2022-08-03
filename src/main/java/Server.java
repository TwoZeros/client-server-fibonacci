import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final int SERVER_PORT = 23144;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(23144);
        System.out.println("Server is running");
        Socket socket = serverSocket.accept();

        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
        ) {
            String line = in.readLine();
            if (line.equals("end")) return;
            if (line.equals("0")) {
                out.println("value must be > 0");
                return;
            }
            int value = Integer.parseInt(line);
            out.println("Fibonacci: " + (getFibonacci(value).get(value - 1)));

        }
    }

    public static List<BigDecimal> getFibonacci(int n) {
        List<BigDecimal> list = new ArrayList<>();
        if (n == 1) {
            list.add(new BigDecimal(0));
            return list;
        }
        list.add(new BigDecimal(0));
        list.add(new BigDecimal(1));
        for (int i = 2; i < n; ++i) {
            list.add(list.get(i - 1).add(list.get(i - 2)));
        }
        return list;
    }
}
