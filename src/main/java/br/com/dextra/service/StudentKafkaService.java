package br.com.dextra.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import br.com.dextra.dto.StudentDTO;

@Service
public class StudentKafkaService {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void sendStudentMessage(StudentDTO dto) {
		
		
		ListenableFuture<SendResult<String, Object>> future = 
			      kafkaTemplate.send("NEW_USER", dto);
				
			    future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
			 
			        @Override
			        public void onSuccess(SendResult<String, Object> result) {
			            System.out.println("Sent message=[" + dto + 
			              "] with offset=[" + result.getRecordMetadata().offset() + "]");
			        }
			        @Override
			        public void onFailure(Throwable ex) {
			            System.out.println("Unable to send message=[" 
			              + dto + "] due to : " + ex.getMessage());
			        }
			    });
		
	}

	public void sendStudentMessage(String id, @Valid StudentDTO dto) {
		dto.setId(id);
		sendStudentMessage(dto);
	}

}
