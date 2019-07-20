package bytes.sync.adb.ui.swing;

import bytes.sync.adb.helper.AdbHelper;
import bytes.sync.adb.ui.ProgressUpdater;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static java.awt.BorderLayout.*;

public class ProgressFrame extends JFrame implements ProgressUpdater {

    private JTextArea textArea;
    private ConfigurableApplicationContext configurableApplicationContext;
    private JLabel progressLabel;
    private JLabel deviceLabel;


    public ProgressFrame(ConfigurableApplicationContext configurableApplicationContext) {
        this.configurableApplicationContext = configurableApplicationContext;
    }

    @Override
    public void init() {

        progressLabel = new JLabel();
        progressLabel.setForeground(Color.WHITE);
        deviceLabel = new JLabel();
        deviceLabel.setForeground(Color.WHITE);

/*        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setBorder(new EmptyBorder(5,5,5,5));
        textArea.setCaretColor(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);*/

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.setBorder(new EmptyBorder(5,5,5,5));
        //panel.add(scrollPane, CENTER);
        panel.add(progressLabel, PAGE_START);
        panel.add(deviceLabel, PAGE_END);

        this.add(panel);
        this.setSize(300, 100);
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                close();
            }
        });

        //Show the window
        this.setVisible(true);

    }

    @Override
    public void close() {
        System.out.println("Window closing");
        int exitCode = SpringApplication.exit(configurableApplicationContext, (ExitCodeGenerator) () -> {
            //Clean up adb resources
            try {
                AdbHelper.disableReverseCommunication();
                AdbHelper.killAdbServer();
                System.out.println("Adb server killed");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        });
        System.exit(exitCode);
    }

    @Override
    public void appendProgressUpdate(String newProgress) {
/*        this.textArea.setText(this.textArea.getText() + "\n" + newProgress);
        this.textArea.setCaretPosition(this.textArea.getText().length());*/
        this.progressLabel.setText(newProgress);
    }

    @Override
    public void showDeviceInfo(String deviceInfo) {
        this.deviceLabel.setText(deviceInfo);
    }
}
