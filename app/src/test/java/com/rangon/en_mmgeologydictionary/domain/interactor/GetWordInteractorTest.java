package com.rangon.en_mmgeologydictionary.domain.interactor;


import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetWordInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.impl.GetWordInteractorImpl;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.threading.TestMainThread;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

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

        when(mWordRepository.getWord("test", "d427beaa-ca5c-485d-bb76-ab086dea8f9f"))
                .thenReturn(dummyWordModel);

        GetWordInteractorImpl interactor = new GetWordInteractorImpl(mExecutor, mMainThread, mWordRepository, "test", "d427beaa-ca5c-485d-bb76-ab086dea8f9f", mMockedCallback);
        interactor.run();
        Mockito.verify(mWordRepository).getWord("test", "d427beaa-ca5c-485d-bb76-ab086dea8f9f");
        Mockito.verifyNoMoreInteractions(mWordRepository);
        Mockito.verify(mMockedCallback).onWordRetrieved(dummyWordModel);

    }
}
