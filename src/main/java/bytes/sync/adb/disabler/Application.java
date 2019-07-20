package bytes.sync.adb.disabler;

import bytes.sync.adb.helper.AdbHelper;
import bytes.sync.adb.helper.AdbInitializer;
import bytes.sync.adb.ui.ProgressUpdater;
import bytes.sync.adb.ui.swing.ProgressFrame;
import com.sun.tools.javac.comp.Flow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Application.class, args);

		//Spring application - by default headless mode activated
		System.setProperty("java.awt.headless", "false");
		ProgressUpdater progressUpdater = new ProgressFrame(configurableApplicationContext);
		progressUpdater.init();

		//Progress update support in AdbHelper utility class
		AdbHelper.init(progressUpdater);

		//Start adb init process
		new Thread(new AdbInitializer()).start();
	}
}
