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
                //Device found - now enable reverse proxy config
                AdbInitializer.enableAdbReverseProxy();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private static boolean initAdb() throws IllegalStateException {
        String initStatement = AdbExecutioner.executeAdbCommand(AdbCommands.START_ADB);
        if(initStatement.length() > 0) {
            //Something printed - adb might be facing error while starting server
            throw new IllegalStateException();
        } else {
            return true;
        }
    }

    private static String waitForDevice() {
        String deviceInfo = AdbExecutioner.executeAdbCommand(AdbCommands.WAIT_FOR_DEVICE);
        if(deviceInfo.contains("device")) {
            //Device connected, returning the device info
            return deviceInfo;
        } else {
            return "No device found";
        }
    }

    private static void enableAdbReverseProxy() {
        AdbExecutioner.executeAdbCommand(AdbCommands.ENABLE_REVERSE_PORT_CONFIG);
    }

    private static void postStatus() {

    }
}
