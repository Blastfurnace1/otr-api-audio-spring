package com.blastfurnace.otr.audio;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.blastfurnace.otr.AppConfigTest;
import com.blastfurnace.otr.data.audiofile.model.AudioFileProperties;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.assertTrue;

import java.util.Map;

/**
 * Integration Tests for Audio Services
 *
 * @author Jim Blackson
 */
public class AudioAPIRestTests extends AppConfigTest {
	
	private static final Logger log = LoggerFactory.getLogger(AudioAPIRestTests.class); 
	
	@Test
	public void shouldPerformAudioAPIRestTests() throws Exception {
		log.info("Audio API Rest Tests - Start");
		
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.getTestRestTemplate().getForEntity(
				"http://localhost:" + this.getPort() + "/rest/audio/get/3", Map.class);

		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		HttpHeaders httpHeaders = this.getTestRestTemplate()
				  .headForHeaders("http://localhost:" + this.getPort() + "/rest/audio/get/3");
		
		assertTrue(httpHeaders.getContentType()
				  .includes(MediaType.APPLICATION_JSON));
		
		AudioFileProperties audio = this.getTestRestTemplate().getForObject(
				"http://localhost:" + this.getPort() + "/rest/audio/get/3", AudioFileProperties.class);

		then(entity).isNotNull();
		then(audio.getId()).isEqualTo(3);
		then(audio.getFilename()).isNotNull();
		log.info("Audio API Rest Tests - End");
	}
	
}
