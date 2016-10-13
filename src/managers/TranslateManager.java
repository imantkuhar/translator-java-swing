package managers;

import api.TranslateApi;
import model.Word;
import parser.WordParser;

/**
 * Created by imant
 */
public class TranslateManager {
    public Word translateWord(String word, String languageFrom, String languageTo) {
        TranslateApi api = new TranslateApi();
        String request = api.translateWord(word, languageFrom, languageTo);
        WordParser wordParser = new WordParser();
        return wordParser.parse(request);
    }
}