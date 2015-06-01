package org.wonderland.dev.levi9.springboot.engine.utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonProcessor {
	
	ObjectMapper mapper = new ObjectMapper();
	
	public <T> T readJson(String pathToFile, Class<T> valueType) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(new File(pathToFile), valueType);
	}
	
	public void writeJson(String pathToFile, Object o) throws JsonGenerationException, JsonMappingException, IOException {
		mapper.writeValue(new File(pathToFile), o);
	}
	
}
