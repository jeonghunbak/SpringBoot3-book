import org.junit.jupiter.api.*;

public class JunitCycleTest {

    @BeforeAll
    static void beforeRun(){
        System.out.println("Before Run!");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("Before Each");
    }

    @Test
    public void test1(){
        System.out.println("test 1");
    }

    @Test
    public void test2(){
        System.out.println("test 2");
    }

    @Test
    public void test3(){
        System.out.println("test 3");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("afterAll");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("A E");
    }
}
