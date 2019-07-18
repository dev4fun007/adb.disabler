package bytes.sync.adb.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class AdbExecutioner {

    static String executeAdbCommand(String[] command) {
        StringBuilder lines = new StringBuilder();
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);
        Process p = null;
        try {
            p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                try {
                    line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    lines.append(line);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "[EXCEPTION] " + e.getMessage();
                }
            }
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "[EXCEPTION] " + e.getMessage();
        }
        return lines.toString();
    }
}
