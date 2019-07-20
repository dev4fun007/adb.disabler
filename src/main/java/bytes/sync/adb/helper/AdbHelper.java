package bytes.sync.adb.helper;

import bytes.sync.adb.model.Package;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bytes.sync.adb.ui.ProgressUpdater;


public class AdbHelper {

    private static ProgressUpdater progressUpdater;

    public static void init(ProgressUpdater progressUpdater) {
        AdbHelper.progressUpdater = progressUpdater;
    }

    static void startAdbServer() throws IOException, InterruptedException {
        progressUpdater.appendProgressUpdate("Starting adb server....");
        AdbExecutioner.executeAdbCommand(AdbCommands.START_ADB);
//        progressUpdater.appendProgressUpdate("Started adb server.");
    }

    public static void killAdbServer() throws IOException, InterruptedException {
        progressUpdater.appendProgressUpdate("Killing adb server....");
        AdbExecutioner.executeAdbCommand(AdbCommands.KILL_ADB);
//        progressUpdater.appendProgressUpdate("Killed adb server.");
    }

    static String listConnectedDevices() throws IOException, InterruptedException {
        String connectedDevice = AdbExecutioner.executeAdbCommand(AdbCommands.LIST_DEVICES);

        if(connectedDevice.length() > 0) {
            connectedDevice = connectedDevice.split("\n")[2];
            int indexModel = connectedDevice.indexOf("model:") + 6;
            int indexDevice = connectedDevice.indexOf("device:");
            String deviceName = connectedDevice.substring(indexModel, indexDevice);
            String deviceSerial = connectedDevice.substring(0, 12);
            progressUpdater.showDeviceInfo("Device - " + deviceSerial + " - " + deviceName);
        }
        return connectedDevice;
    }

    public static List<Package> getAllDisabledPackages() throws IOException, InterruptedException {
        List<Package> disabledPackages = new ArrayList<>();
        String packages = AdbExecutioner.executeAdbCommand(AdbCommands.LIST_DISABLED_PACKAGES);
        disabledPackages = getPackages(packages);
        return disabledPackages;
    }

    public static List<Package> getAllPackages() throws IOException, InterruptedException {
        List<Package> packagesAll = new ArrayList<>();
        String packages = AdbExecutioner.executeAdbCommand(AdbCommands.LIST_PACKAGES);
        packagesAll = getPackages(packages);
        return packagesAll;
    }

    private static List<Package> getPackages(String response) {
        List<Package> disabledPackages = new ArrayList<>();
        String[] packagesArray = response.split("\\n");
        for(String pkg : packagesArray) {
            if(pkg.length() == 0)
                continue;
            pkg = pkg.substring(pkg.indexOf(":")+1);
            Package disabledPackage = new Package();
            disabledPackage.setDisabled(true);
            disabledPackage.setName(pkg);
            disabledPackages.add(disabledPackage);
        }
        return disabledPackages;
    }

    public static String disablePackage(String pkg) throws IOException, InterruptedException {
        String response = "";
        response = modifyPackageState(pkg, AdbCommands.disablePackage);
        return response;
    }

    public static String enablePackage(String pkg) throws IOException, InterruptedException {
        String response = "";
        modifyPackageState(pkg, AdbCommands.enablePackage);
        return response;
    }

    public static String uninstallPackage(String pkg) throws IOException, InterruptedException {
        String response = "";
        modifyPackageState(pkg, AdbCommands.uninstallPackage);
        return response;
    }

    private static String modifyPackageState(String pkg, String[] cmd) throws IOException, InterruptedException {
        cmd[cmd.length-1] = pkg;
        return AdbExecutioner.executeAdbCommand(cmd);
    }

    public static String rebootDevice() throws IOException, InterruptedException {
        String response = "";
        response = AdbExecutioner.executeAdbCommand(AdbCommands.REBOOT_DEVICE);
        return response;
    }


    public static void enableReverseCommunication() throws IOException, InterruptedException {
        progressUpdater.appendProgressUpdate("Enabling adb reverse communication....");
        AdbExecutioner.executeAdbCommand(AdbCommands.ENABLE_REVERSE_PORT_CONFIG);
        progressUpdater.appendProgressUpdate("Started - Do not close - Proceed in app.");
    }

    public static void disableReverseCommunication() throws IOException, InterruptedException {
        progressUpdater.appendProgressUpdate("Disabling adb reverse communication....");
        AdbExecutioner.executeAdbCommand(AdbCommands.DISABLE_REVERSE_PORT_CONFIG);
//        progressUpdater.appendProgressUpdate("Disabled adb reverse communication....");
    }


    public static void waitForDevice() throws IOException, InterruptedException {
        progressUpdater.appendProgressUpdate("Waiting for device....");
        AdbExecutioner.executeAdbCommand(AdbCommands.WAIT_FOR_DEVICE);
//        progressUpdater.appendProgressUpdate("Device Found....");
    }


    public static void disableRunInBackground(String pkgId) throws IOException, InterruptedException {
        String[] cmd = AdbCommands.disableRunInBackground;
        cmd[5] = pkgId;
        AdbExecutioner.executeAdbCommand(cmd);
    }

    public static void enableRunInBackground(String pkgId) throws IOException, InterruptedException {
        String[] cmd = AdbCommands.enableRunInBackground;
        cmd[5] = pkgId;
        AdbExecutioner.executeAdbCommand(cmd);
    }

    public static void disableWakeLock(String pkgId) throws IOException, InterruptedException {
        String[] cmd = AdbCommands.disableWakeLock;
        cmd[5] = pkgId;
        AdbExecutioner.executeAdbCommand(cmd);
    }

    public static void enableWakeLock(String pkgId) throws IOException, InterruptedException {
        String[] cmd = AdbCommands.enableWakeLock;
        cmd[5] = pkgId;
        AdbExecutioner.executeAdbCommand(cmd);
    }

}
