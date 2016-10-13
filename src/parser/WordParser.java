package parser;

import model.Word;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by imant
 */
public class WordParser {

    public  Word parse(String json){
        ObjectMapper mapper = new ObjectMapper();
        Word word = null;
        try {
            word = mapper.readValue(json.toString(), Word.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return word;
    }
}
