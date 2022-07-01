package ru.diasoft.micro.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.micro.domain.SmsVerificationEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsVerificationRepositoryTest {
	
	@Autowired
	private SmsVerificationRepository repository;
	
	@Test
	public void smsVerificationCreateTest() {

		String guid = UUID.randomUUID().toString();
		String secretCode = "0007";
		String status = "WAITING";

		SmsVerificationEntity smsVerification = SmsVerificationEntity.builder()
			.processGuid(guid)
			.phoneNumber("123456789")
			.secretCode(secretCode)
			.status(status)
			.build();

		SmsVerificationEntity createdEntity = repository.save(smsVerification);
		assertThat(smsVerification).isEqualTo(createdEntity);
		assertThat(createdEntity.getVerificationId()).isNotNull();
	}
	
	@Test
	public void findBySecretCodeAndProcessGuidAndStatusTest() {

		String guid = UUID.randomUUID().toString();
		String secretCode = "0007";
		String status = "WAITING";

		SmsVerificationEntity smsVerification = SmsVerificationEntity.builder()
			.processGuid(guid)
			.phoneNumber("123456789")
			.secretCode(secretCode)
			.status(status)
			.build();

		 SmsVerificationEntity createdEntity = repository.save(smsVerification);

		 assertThat(repository.findBySecretCodeAndProcessGuidAndStatus(secretCode, guid, status).orElse(SmsVerificationEntity.builder().build())).isEqualTo(createdEntity);
		 assertThat(repository.findBySecretCodeAndProcessGuidAndStatus("22222", guid, status)).isEmpty();
	}

} // The End of Class;
