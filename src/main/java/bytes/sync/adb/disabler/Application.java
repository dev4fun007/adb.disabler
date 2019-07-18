package bytes.sync.adb.disabler;

import bytes.sync.adb.helper.AdbInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		new Thread(new AdbInitializer()).start();
	}
}
