package io.inab.atdev.payroll;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private Application application;

	@Test
	void contextLoads() {
		assertThat(this.application).isNotNull();
	}

}
