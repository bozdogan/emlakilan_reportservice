package org.bozdgn.reportservice;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class ReportserviceApplicationTests {

    @Test
    void itDividesBy10() {
        double result = 17.40 / 10;
        double expected = 1.74;

        assertThat(result).isEqualTo(expected);
    }

}
