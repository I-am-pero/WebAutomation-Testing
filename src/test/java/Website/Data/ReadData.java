package Website.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadData {
	public List<HashMap<String, String>> getJsonDataToHashMap() throws IOException {
		// Read json to string
		String jsonData = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\Website\\Data\\PurchaseOrderData.json"),
				StandardCharsets.UTF_8);

		// String to HashMap -> get jackson datbind from repo.
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
}
