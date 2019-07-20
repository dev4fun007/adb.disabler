package bytes.sync.adb.disabler;

import bytes.sync.adb.model.Package;
import bytes.sync.adb.helper.AdbHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DisablerEndpoints {

    @GetMapping(value = "/isActive")
    public boolean isActive() {
        return true;
    }

    @PostMapping(value = "/package/disable({id})")
    public ResponseEntity disablePackage(@PathVariable("id") String packageId) {
        try {
            AdbHelper.disablePackage(packageId);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostMapping(value = "/package/enable({id})")
    public ResponseEntity enablePackage(@PathVariable("id") String packageId) {
        try {
            AdbHelper.enablePackage(packageId);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostMapping(value = "/package/uninstall({id})")
    public ResponseEntity uninstallPackage(@PathVariable("id") String packageId) {
        try {
            AdbHelper.uninstallPackage(packageId);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostMapping(value = "/package/disable/run_in_background({id})")
    public ResponseEntity disableRunInBackground(@PathVariable("id") String packageId) {
        try {
            AdbHelper.disableRunInBackground(packageId);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostMapping(value = "/package/enable/run_in_background({id})")
    public ResponseEntity enableRunInBackground(@PathVariable("id") String packageId) {
        try {
            AdbHelper.enableRunInBackground(packageId);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostMapping(value = "/package/disable/wake_lock({id})")
    public ResponseEntity disableWakeLock(@PathVariable("id") String packageId) {
        try {
            AdbHelper.disableWakeLock(packageId);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostMapping(value = "/package/enable/wake_lock({id})")
    public ResponseEntity enableWakeLock(@PathVariable("id") String packageId) {
        try {
            AdbHelper.enableWakeLock(packageId);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @GetMapping(value = "/package/disabled/all")
    public List<Package> getAllDisabledPackages() {
        List<Package> packages = new ArrayList<>();
        try {
            packages = AdbHelper.getAllDisabledPackages();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return packages;
    }

    @GetMapping(value = "/package/all")
    public List<Package> getAllPackages() {
        List<Package> packages = new ArrayList<>();
        try {
            packages = AdbHelper.getAllPackages();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return packages;
    }


    @PostMapping(value = "/device/reboot")
    public ResponseEntity rebootDevice() {
        try {
            AdbHelper.rebootDevice();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }


}
