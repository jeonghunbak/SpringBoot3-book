import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JunitTest {

    @DisplayName("a + b = c")
    @Test
    public void junitTest(){
        int a = 10;
        int b = 20;
        int sum = a + b;

        Assertions.assertEquals(a + b, sum);
    }
}
