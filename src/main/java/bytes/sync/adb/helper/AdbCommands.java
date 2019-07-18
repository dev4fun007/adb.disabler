package bytes.sync.adb.helper;

class AdbCommands {

    static final String[] START_ADB = new String[]{"adb/adb.exe", "start-server"};
    static final String[] KILL_ADB = new String[]{"adb/adb.exe", "kill-server"};
    static final String[] LIST_DEVICES = new String[]{"adb/adb.exe", "devices"};
    static final String[] LIST_DISABLED_PACKAGES = new String[]{"adb/adb.exe", "shell", "pm", "list", "packages", "-d"};
    static final String[] ENABLE_REVERSE_PORT_CONFIG = new String[] {"adb/adb.exe", "reverse", "tcp:8888", "tcp:8888"};
    static final String[] DISABLE_REVERSE_PORT_CONFIG = new String[] {"adb/adb.exe", "reverse", "--remove-all"};
    static final String[] WAIT_FOR_DEVICE = new String[] {"adb/adb.exe", "wait-for-device"};

    static final String[] REBOOT_DEVICE = new String[] {"adb/adb.exe", "reboot"};

    static String[] disablePackage = new String[]{"adb/adb.exe", "shell", "pm", "disable-user", "<pkg-id>"};
    static String[] enablePackage = new String[]{"adb/adb.exe", "shell", "pm", "enable", "<pkg-id>"};
    static String[] uninstallPackage = new String[]{"adb/adb.exe", "shell", "pm", "uninstall", "<pkg-id>"};
}
