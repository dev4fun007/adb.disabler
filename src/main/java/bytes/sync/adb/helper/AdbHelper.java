package bytes.sync.adb.helper;


import java.util.List;

public class AdbHelper {

    public static void startAdbServer() {
        AdbExecutioner.executeAdbCommand(AdbCommands.START_ADB);
    }

    public static void killAdbServer() {
        AdbExecutioner.executeAdbCommand(AdbCommands.KILL_ADB);
    }

    public static void listConnectedDevices() {
        AdbExecutioner.executeAdbCommand(AdbCommands.LIST_DEVICES);
    }

    public static List<Package> getAllDisabledPackages() {
        AdbExecutioner.executeAdbCommand(AdbCommands.LIST_DISABLED_PACKAGES);
        return null;
    }

    public static void disablePackage(String pkg) {
        modifyPackageState(pkg, AdbCommands.disablePackage);
    }

    public static void enablePackage(String pkg) {
        modifyPackageState(pkg, AdbCommands.enablePackage);
    }

    public static void uninstallPackage(String pkg) {
        modifyPackageState(pkg, AdbCommands.uninstallPackage);
    }

    private static void modifyPackageState(String pkg, String[] cmd) {
        cmd[cmd.length-1] = pkg;
        AdbExecutioner.executeAdbCommand(cmd);
    }

    public static void rebootDevice() {
        AdbExecutioner.executeAdbCommand(AdbCommands.REBOOT_DEVICE);
    }

    public static void enableReverseCommunication() {
        AdbExecutioner.executeAdbCommand(AdbCommands.ENABLE_REVERSE_PORT_CONFIG);
    }

    public static void disableReverseCommunication() {
        AdbExecutioner.executeAdbCommand(AdbCommands.DISABLE_REVERSE_PORT_CONFIG);
    }

    public static void waitForDevice() {
        AdbExecutioner.executeAdbCommand(AdbCommands.WAIT_FOR_DEVICE);
    }
}
