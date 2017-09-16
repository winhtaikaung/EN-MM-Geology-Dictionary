package com.rangon.en_mmgeologydictionary.domain.interactor;


import com.rangon.en_mmgeologydictionary.threading.TestMainThread;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetLikelyWordsInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.impl.GetLikelyWordsInteractorImpl;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import io.reactivex.Observable;
import com.rangon.en_mmgeologydictionary.model.Word;

import static org.mockito.Mockito.when;

/**
 * Created by winhtaikaung on 20/3/17.
 */

public class GetLikelyWordsInteractorTest {

    private MainThread mMainThread;
    @Mock
    private Executor mExecutor;
    @Mock
    private WordRepository mWordRepository;
    @Mock
    private GetLikelyWordsInteractor.Callback mMockedCallback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new TestMainThread();
    }


    @Test
    public void testGetLikelyWordModel() throws Exception {

        Word dummyWordfirst = new Word("sdfg", "Mona", "http://ab.com", "http://www.flightstatus.com", "test", false
        );
        Word dummyWordsecond = new Word("sdfg", "Mona", "http://ab.com", "http://www.flightstatus.com", "test", false
        );
        Word dummyWordthird = new Word("sdfg", "Mona", "http://ab.com", "http://www.flightstatus.com", "test", false
        );

        List<Word> dummyWordList = new ArrayList<>();
        dummyWordList.add(dummyWordfirst);
        dummyWordList.add(dummyWordsecond);
        dummyWordList.add(dummyWordthird);


        Observable<List<Word>> dummyWordModel = Observable.just(dummyWordList);

        when(mWordRepository.getLikelyWord("test"))
                .thenReturn(dummyWordModel);

        GetLikelyWordsInteractorImpl interactor = new GetLikelyWordsInteractorImpl(mExecutor, mMainThread, mWordRepository, "test", mMockedCallback);
        interactor.run();
        Mockito.verify(mWordRepository).getLikelyWord("test");
        Mockito.verifyNoMoreInteractions(mWordRepository);
        Mockito.verify(mMockedCallback).onLikelyWordsRetrieved(dummyWordModel);

    }
}
