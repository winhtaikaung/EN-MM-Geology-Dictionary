package com.rangon.en_mmgeologydictionary.domain.interactor;


import com.rangon.en_mmgeologydictionary.threading.TestMainThread;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import domain.executor.Executor;
import domain.executor.MainThread;
import domain.interactors.GetWordInteractor;
import domain.interactors.impl.GetWordInteractorImpl;
import domain.repository.WordRepository;
import io.reactivex.Observable;
import model.Word;

import static org.mockito.Mockito.when;

/**
 * Created by winhtaikaung on 20/3/17.
 */

public class GetWordInteractorTest {

    private MainThread mMainThread;
    @Mock
    private Executor mExecutor;
    @Mock
    private WordRepository mWordRepository;
    @Mock
    private GetWordInteractor.Callback mMockedCallback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new TestMainThread();
    }


    @Test
    public void testGetWordModel() throws Exception {

        Word dummyWordfirst = new Word("sdfg", "Mona", "http://ab.com", "http://www.flightstatus.com", "test", false
        );


        Observable<Word> dummyWordModel = Observable.just(dummyWordfirst);

        when(mWordRepository.getWord("test"))
                .thenReturn(dummyWordModel);

        GetWordInteractorImpl interactor = new GetWordInteractorImpl(mExecutor, mMainThread, mWordRepository, "test", mMockedCallback);
        interactor.run();
        Mockito.verify(mWordRepository).getWord("test");
        Mockito.verifyNoMoreInteractions(mWordRepository);
        Mockito.verify(mMockedCallback).onWordRetrieved(dummyWordModel);

    }
}
