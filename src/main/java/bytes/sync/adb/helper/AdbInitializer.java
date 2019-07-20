package bytes.sync.adb.helper;

import org.springframework.beans.factory.annotation.Value;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AdbInitializer implements Runnable {

    @Value("${server.port}")
    private static String serverPort;

    @Override
    public void run() {
        establishAdbConnection();
    }

    private static void establishAdbConnection() {
        System.out.println("Establishing Adb Connection");
        try {
            boolean hasStarted = AdbInitializer.initAdb();
            if(hasStarted) {
                //Now wait for device connection
                String deviceInfo = AdbInitializer.waitForDevice();
                System.out.println(deviceInfo);
                //Device found - now enable reverse proxy config
                AdbInitializer.enableAdbReverseProxy();
            }
        } catch (IllegalStateException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean initAdb() throws IllegalStateException, IOException, InterruptedException {
        System.out.println("Starting adb server");
        AdbHelper.startAdbServer();
        return true;
    }

    private static String waitForDevice() throws IOException, InterruptedException {
        System.out.println("Waiting for device");
        AdbHelper.waitForDevice();

        String deviceInfo = AdbHelper.listConnectedDevices();
        if(deviceInfo.contains("device")) {
            return deviceInfo;
        } else {
            return "No device found";
        }
    }

    private static void enableAdbReverseProxy() throws IOException, InterruptedException {
        System.out.println("Enabling reverse proxy");
        AdbHelper.enableReverseCommunication();
        System.out.println("Reverse proxy enabled....Waiting for android app requests");
    }

}
