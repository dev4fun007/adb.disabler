package bytes.sync.adb.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class AdbExecutioner {

    static String executeAdbCommand(String[] command) throws IOException, InterruptedException {
        StringBuilder lines = new StringBuilder();
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);
        Process p = null;
        p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            lines.append("\n").append(line);
        }
        p.waitFor();
        return lines.toString();
    }
}