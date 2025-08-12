import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnitTest_2 {
    @Test
    @DisplayName("JUnitTest_2 테스트")
    public void junitTest(){
        String name1 = "최성주";
        String name2 = "최성주";
        String name3 = "최성재";

        //모든 변수가 null이 아닌지 확인
        //name1과 name2가 같은지 확인
        //name1과 name3이 다른지 확인

        assertThat(name1).isNotNull();
        assertThat(name2).isNotNull();
        assertThat(name3).isNotNull();

        assertThat(name1).isEqualTo(name2);

        assertThat(name1).isNotEqualTo(name3);

    }

    @Test
    @DisplayName("junitTest2")
    public void junitTest2(){
        int number1 = 15;
        int number2 = 0;
        int number3 = -5;

        //number1은 양수인지
        assertThat(number1).isPositive();
        //number2은 0인지
        assertThat(number2).isZero();
        //number3은 음수인지
        assertThat(number3).isNegative();
        //number1은 number2보다 큰지 확인
        assertThat(number1).isGreaterThan(number2);
        //number3은 number2보다 작은지 확인
        assertThat(number3).isLessThan(number2);
    }

    @Test
    public void jUnitCycleTest1(){
        System.out.println("This is first Test");
    }

    @Test
    public void jUnitCycleTest2(){
        System.out.println("This is second Test");
    }

    @AfterAll
    public static void jUnitCycleTest3(){
        System.out.println("Bye!");
    }

    @BeforeEach
    public void jUnitCycleTest4(){
        System.out.println("Hello!");
    }
}
