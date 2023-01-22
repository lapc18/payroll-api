package io.inab.atdev.payroll.core.helpers;

import io.inab.atdev.payroll.domain.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration
@SpringBootTest
public class CSVHelperTests {


    @Test
    public void parseToObj() throws Exception {
        var csv = "full_name, email, position, health_discount_amount, social_discount_amount, taxes_discount_amount, other_discount_amount, gross_salary, gross_payment, net_payment, period\n" +
                "John-Doe, devlapc18@gmail.com, Software Engineer, 580, 3500, 19000, 4000, 98000, 98000, 70920, 02-01-2023 - 31-01-2023\n" +
                "Ricky-Sandoval, lapc18@outlook.com,Software Engineer,580,3500,19000,4000,98000,98000,70920,02-01-2023 - 31-01-2023\n" +
                "Mae-Munoz, e_mail,Software Engineer,580,3500,19000,4000,98000,98000,70920,02-01-2023 - 31-01-2023\n";

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "realSlimTestShady.csv",
                MediaType.TEXT_PLAIN_VALUE,
                csv.getBytes()
        );

        var result = new LinkedList<Employee>(Objects.requireNonNull(CSVHelper.toList(file, Employee.class)));

        assertThat(file).isNotNull();
        assertThat(result).isNotNull();
        assertThat(result).doesNotContainNull();
        assertThat(result.size()).isGreaterThan(0);
        assertThat(result.size()).isEqualTo(3);
    }


    @Test
    public void parseToObjAndFailsIfHeaderDoesNotMatch() {
        var csv = "full_name, email, position, health_discount_amount, social_discount_amount, taxes_discount_amount, other_discount_amount, gross_salary, gross_payment, net_payment, period\n" +
                "John-Doe, Software Engineer, 580, 3500, 19000, 4000, 98000, 98000, 70920, 02-01-2023 - 31-01-2023\n" +
                "Ricky-Sandoval, lapc18@outlook.com,Software Engineer,580,3500,19000,4000,98000,98000,70920,02-01-2023 - 31-01-2023\n" +
                "Mae-Munoz, e_mail,Software Engineer,580,3500,19000,4000,98000,98000,70920,02-01-2023 - 31-01-2023\n";

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "realSlimTestShady.csv",
                MediaType.TEXT_PLAIN_VALUE,
                csv.getBytes()
        );

        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> CSVHelper.toList(file, Employee.class));
        assertThat(exception.getMessage()).isEqualTo("Number of data fields does not match number of headers!");
    }

}
