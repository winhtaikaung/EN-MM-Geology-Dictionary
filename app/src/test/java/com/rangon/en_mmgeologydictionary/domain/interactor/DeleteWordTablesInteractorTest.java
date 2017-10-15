package com.rangon.en_mmgeologydictionary.domain.interactor;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.DeleteWordTablesInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.impl.DeleteWordTablesInteractorImpl;
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
 * Created by winhtaikaung on 15/10/17.
 */

public class DeleteWordTablesInteractorTest {


    private MainThread mMainThread;
    @Mock
    private Executor mExecutor;
    @Mock
    private WordRepository mWordRepository;
    @Mock
    private DeleteWordTablesInteractor.Callback mMockedCallback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new TestMainThread();
    }


    @Test
    public void testDeleteWordTables() throws Exception {


        Boolean result = new Boolean(true);
        String[] tablesName = {"a", "b", "c", "d"};


        Observable<Boolean> dummyResult = Observable.just(result);

        when(mWordRepository.deleteWordTables(tablesName))
                .thenReturn(dummyResult);

        DeleteWordTablesInteractorImpl interactor = new DeleteWordTablesInteractorImpl(mExecutor, mMainThread, mWordRepository, tablesName, mMockedCallback);
        interactor.run();
        Mockito.verify(mWordRepository).deleteWordTables(tablesName);
        Mockito.verifyNoMoreInteractions(mWordRepository);
        Mockito.verify(mMockedCallback).onWordTableDeleted(dummyResult);


    }
}
