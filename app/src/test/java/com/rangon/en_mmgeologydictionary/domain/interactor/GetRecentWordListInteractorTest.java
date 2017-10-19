package com.rangon.en_mmgeologydictionary.domain.interactor;


import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetRecentWordListInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.impl.GetRecentWordListInteractorImpl;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.threading.TestMainThread;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.when;

/**
 * Created by winhtaikaung on 20/3/17.
 */

public class GetRecentWordListInteractorTest {

    private MainThread mMainThread;
    @Mock
    private Executor mExecutor;
    @Mock
    private WordRepository mWordRepository;
    @Mock
    private GetRecentWordListInteractor.Callback mMockedCallback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new TestMainThread();
    }


    @Test
    public void testGetRecentWordList() throws Exception {

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
        String[] tablesName = {"a", "b", "c", "d"};

        Observable<List<Word>> dummyWordModel = Observable.just(dummyWordList);

        when(mWordRepository.getRecentWord(tablesName, 1, 10))
                .thenReturn(dummyWordModel);

        GetRecentWordListInteractorImpl interactor = new GetRecentWordListInteractorImpl(mExecutor, mMainThread, mWordRepository, tablesName, 1, 10, mMockedCallback);
        interactor.run();
        Mockito.verify(mWordRepository).getRecentWord(tablesName, 1, 10);
        Mockito.verifyNoMoreInteractions(mWordRepository);
        Mockito.verify(mMockedCallback).onRecentWordListRetrieved(dummyWordModel);

    }
}
