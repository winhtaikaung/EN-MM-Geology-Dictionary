package com.rangon.en_mmgeologydictionary.domain.interactor;


import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.UpdateRecentWordInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.impl.UpdateRecentWordInteractorImpl;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.threading.TestMainThread;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.mockito.Mockito.when;

/**
 * Created by winhtaikaung on 18/10/17.
 */

public class UpdateRecentWordInteractorTest {

    private MainThread mMainThread;
    @Mock
    private Executor mExecutor;
    @Mock
    private WordRepository mWordRepository;
    @Mock
    private UpdateRecentWordInteractor.Callback mMockedCallback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new TestMainThread();
    }


    @Test
    public void testUpdateRecentWordModel() throws Exception {

        Observable<Boolean> dummyWordModel = Observable.just(true);

        when(mWordRepository.updateRecentWord("test", "d427beaa-ca5c-485d-bb76-ab086dea8f9f"))
                .thenReturn(dummyWordModel);

        UpdateRecentWordInteractorImpl interactor = new UpdateRecentWordInteractorImpl(mExecutor, mMainThread, mWordRepository, "test", "d427beaa-ca5c-485d-bb76-ab086dea8f9f", mMockedCallback);
        interactor.run();
        Mockito.verify(mWordRepository).updateRecentWord("test", "d427beaa-ca5c-485d-bb76-ab086dea8f9f");
        Mockito.verifyNoMoreInteractions(mWordRepository);
        Mockito.verify(mMockedCallback).onUpdateRecentWord(dummyWordModel);

    }
}
