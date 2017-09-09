package data.repository.datasource;

import java.util.List;

import data.service.WordDAL;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import model.Word;
import services.WordService;

import static network.RestClient.getRetrofit;

/**
 * Created by win on 9/9/17.
 */

public class WordsCloudDataStore implements WordsDataStore {

    private WordDAL wordDAL;

    public WordsCloudDataStore(WordDAL dal) {
        this.wordDAL = dal;
    }

    @Override
    public Observable<Word> getWord(String word) {
        return null;
    }

    @Override
    public Observable<List<Word>> getLikelyWord(String searchKeyword) {
        return null;
    }

    @Override
    public Observable<List<Word>> getWordList(String wordIndex, int page, int size) {
        return getRetrofit().create(WordService.class).getWordList(wordIndex, page, size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(wordListResponse -> wordListResponse.getData())
                .doOnNext(words -> wordDAL.bulkInsertWord(words, wordIndex));
    }

    @Override
    public Observable<Boolean> insertWordList(List<Word> wordList, String tableName) {
        return null;
    }
}
