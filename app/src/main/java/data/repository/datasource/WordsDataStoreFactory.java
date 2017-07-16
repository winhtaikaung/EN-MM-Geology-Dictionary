package data.repository.datasource;

/**
 * Created by winhtaikaung on 16/7/17.
 */

public class WordsDataStoreFactory {
    public WordsDataStoreFactory(){

    }

    public WordsDataStore create(){
        return new WordsDataStore();
    }
}
