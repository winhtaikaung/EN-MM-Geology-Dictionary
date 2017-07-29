package domain.interactors.impl;

import java.util.List;

import domain.executor.Executor;
import domain.executor.MainThread;
import domain.interactors.GetLikelyWordsInteractor;
import domain.interactors.base.AbstractInteractor;
import domain.repository.WordRepository;
import io.reactivex.Observable;
import model.Word;

/**
 * Created by winhtaikaung on 16/7/17.
 */

public class GetLikelyWordsInteractorImpl extends AbstractInteractor implements GetLikelyWordsInteractor {
    private WordRepository mWordRepository;
    private Callback mCallback;
    private String mWord;

    public GetLikelyWordsInteractorImpl(Executor threadExecutor, MainThread mainThread, WordRepository wordRespository, String word, Callback callback) {
        super(threadExecutor, mainThread);
        mWordRepository = wordRespository;
        mCallback = callback;
        mWord = word;
    }

    @Override
    public void run() {
        final Observable<List<Word>> wordListObservable = mWordRepository.getLikelyWord(mWord);
        mMainThread.post(() -> {
            mCallback.onLikelyWordsRetrieved(wordListObservable);
        });


    }
}
