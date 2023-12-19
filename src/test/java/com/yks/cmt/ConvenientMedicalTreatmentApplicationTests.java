package com.yks.cmt;

import com.yks.cmt.config.Sender;
import com.yks.cmt.entity.Message;
import com.yks.cmt.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConvenientMedicalTreatmentApplicationTests {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private Sender sender;

	@Test
	public void testBasicPublish() {
		Message message = new Message();
		message.setMessage("111");
		sender.sendMessage(message);
	}

}
