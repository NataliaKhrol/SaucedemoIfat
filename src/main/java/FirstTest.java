import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class FirstTest {
    //КРАТНО 3, ВОЗВРАЩАТЬ "T"
    //КРАТНО 5, ВОЗВРАЩАТЬ "M"
    //КРАТНО 3 И 5, ВОЗВРАЩАТЬ "ТИМ"
    //ВОЗВРАЩАТЬ "FAIL"

    public String trialCode(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "TИM";
        } else if (number % 5 == 0) {
            return "M";
        } else if (number % 3 == 0) {
            return "T";
        } else return "FAIL";
    }

    @Test
    public void checkNumber() {
        String actualResult = trialCode(3);
        assertEquals(actualResult, "T");
    }

    @Test
    public void checkNumber2() {
        String actualResult = trialCode(25);
        assertEquals(actualResult, "M");
    }

    @Test
    public void checkNumber3() {
        String actualResult = trialCode(15);
        assertEquals(actualResult, "TИM");
    }

    @Test
    public void checkNumber4() {
        String actualResult = trialCode(17);
        assertEquals(actualResult, "FAIL");
    }
}
