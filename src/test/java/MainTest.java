import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Disabled
    @Test
    @Timeout(value = 22)
    void isMainWorksLess22Seconds() throws Exception {
        Main.main(null);
    }

}
