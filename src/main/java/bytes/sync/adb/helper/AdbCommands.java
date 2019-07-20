package bytes.sync.adb.helper;

class AdbCommands {

    /*********************** ADB Actions ************************/
    static final String[] START_ADB = new String[]{"adb/adb.exe", "start-server"};
    static final String[] KILL_ADB = new String[]{"adb/adb.exe", "kill-server"};
    static final String[] LIST_DEVICES = new String[]{"adb/adb.exe", "devices", "-l"};
    static final String[] LIST_DISABLED_PACKAGES = new String[]{"adb/adb.exe", "shell", "pm", "list", "packages", "-d"};
    static final String[] LIST_PACKAGES = new String[]{"adb/adb.exe", "shell", "pm", "list", "packages"};
    static final String[] ENABLE_REVERSE_PORT_CONFIG = new String[] {"adb/adb.exe", "reverse", "tcp:8888", "tcp:8888"};
    static final String[] DISABLE_REVERSE_PORT_CONFIG = new String[] {"adb/adb.exe", "reverse", "--remove-all"};
    static final String[] WAIT_FOR_DEVICE = new String[] {"adb/adb.exe", "wait-for-device"};



    /*********************** Package Actions ************************/
    static String[] disableRunInBackground = new String[] {"adb/adb.exe", "shell", "cmd", "appops", "set", "<pkg-id>", "RUN_IN_BACKGROUND", "ignore"};
    static String[] enableRunInBackground = new String[] {"adb/adb.exe", "shell", "cmd", "appops", "set", "<pkg-id>", "RUN_IN_BACKGROUND", "allow"};

    static String[] disableWakeLock = new String[] {"adb/adb.exe", "shell", "cmd", "appops", "set", "<pkg-id>", "WAKE_LOCK", "ignore"};
    static String[] enableWakeLock = new String[] {"adb/adb.exe", "shell", "cmd", "appops", "set", "<pkg-id>", "WAKE_LOCK", "allow"};

    static String[] disablePackage = new String[]{"adb/adb.exe", "shell", "pm", "disable-user", "<pkg-id>"};
    static String[] enablePackage = new String[]{"adb/adb.exe", "shell", "pm", "enable", "<pkg-id>"};
    static String[] uninstallPackage = new String[]{"adb/adb.exe", "shell", "pm", "uninstall", "<pkg-id>"};



    /************************ Device Actions ************************/
    static final String[] REBOOT_DEVICE = new String[] {"adb/adb.exe", "reboot"};

}
