package bytes.sync.adb.ui;

public interface ProgressUpdater {

    public void init();
    public void close();
    public void appendProgressUpdate(String newProgress);
    public void showDeviceInfo(String deviceInfo);

}
