package vikramkokareacademy.data;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		// Read json to string
		// String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\vikramkokareacademy\\data\\PurchaseOrder.json"));
		 String jsonContent = FileUtils.readFileToString( new File(filePath),StandardCharsets.UTF_8
			);
		//convert string josn data to HashMap using Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
	}

}
