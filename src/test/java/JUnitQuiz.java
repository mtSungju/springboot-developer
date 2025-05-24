import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JUnitQuiz {

    @Test
    @DisplayName("퀴즈1")
    public void junitTeest(){
        String name1 = "홍길동";
        String name2 = "홍길동";
        String name3 = "홍길은";

        //1 모든 변수가 null이 아닌지 확인
        //2 name1과 name2가 같은지 확인
        //3 name1과 name3이 다른지 홛인

        assertThat(name1).isNotNull();
        assertThat(name2).isNotNull();
        assertThat(name3).isNotNull();

        assertThat(name1).isEqualTo(name2);
        assertThat(name1).isNotEqualTo(name3);
    }

    @Test
    @DisplayName("퀴즈2")
    public void test2(){
        int num1 = 15;
        int num2 = 0;
        int num3 = -5;

        //num1은 양수인지 확인
        assertThat(num1).isPositive();

        //num2는 0인지 확인
        assertThat(num2).isEqualTo(0); // isZero()

        //num3이 음수인지 확인
        assertThat(num3).isNegative();

        //num1이 num2보다 튼지 확인
        assertThat(num1).isGreaterThan(num2);

        //num3은 num2보다 작은지 확인
        assertThat(num3).isLessThan(num2);
    }



}
