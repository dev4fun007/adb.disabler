package bytes.sync.adb.disabler;


import bytes.sync.adb.helper.AdbHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DisablerEndpoints {

    @GetMapping(value = "/disable({id})")
    public String disablePackage(@PathVariable("id") String packageId) {
        AdbHelper.enablePackage(packageId);
        return "Enabled";
    }

    @GetMapping(value = "/enable({id})")
    public String enablePackage(@PathVariable("id") String packageId) {
        AdbHelper.disablePackage(packageId);
        return "Disabled";
    }

    @GetMapping(value = "/uninstall({id})")
    public String uninstallPackage(@PathVariable("id") String packageId) {
        AdbHelper.uninstallPackage(packageId);
        return "Uninstalled Triggered";
    }

    @GetMapping(value = "/disabled/all")
    public List<String> getAllDisabledPackages() {
        return AdbHelper.getAllDisabledPackages();
    }


    @GetMapping(value = "/reboot")
    public String rebootDevice() {
        AdbHelper.rebootDevice();
        return "Reboot initiated";
    }


}
