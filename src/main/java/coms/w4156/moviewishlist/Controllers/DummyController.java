package coms.w4156.moviewishlist.Controllers;

import coms.w4156.moviewishlist.Services.DummyService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@Getter @Setter
public class DummyController {
    /**
     * Dummy service.
     */
    private DummyService ds = new DummyService();

    /**
     * Returns a test message.
     * @return a test message.
     */
    @GetMapping("/test")
    String test() {
        return "All good!";
    }

    /**
     * Returns a random number.
     * @return A random number.
     */
    @GetMapping("/rand")
    String rand() {
        Random r = new Random();

        return Double.toString(r.nextDouble());
    }

    /**
     * Returns a specific number specified by the dummy service.
     * @return Specific number.
     */
    @GetMapping("/num")
    String five() {
        return ds.getNum();
    }


}
