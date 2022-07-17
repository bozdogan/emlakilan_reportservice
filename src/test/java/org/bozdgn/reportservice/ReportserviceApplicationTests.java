package org.bozdgn.reportservice;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class ReportserviceApplicationTests {

    @Test
    void itDividesBy10() {
        int result = 1740 / 10;
        int expected = 17;

        assertThat(result).isEqualTo(expected);
    }

}
