package com.rangon.en_mmgeologydictionary.domain.interactor;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetApiConfigListInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.impl.GetApiConfigListInteractorImpl;
import com.rangon.en_mmgeologydictionary.domain.repository.APIConfigRepository;
import com.rangon.en_mmgeologydictionary.model.configrestmodels.ApiConfig;
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
 * Created by win on 9/16/17.
 */

public class GetApiConfigInteractorTest {

    private MainThread mMainThread;
    @Mock
    private Executor mExecutor;
    @Mock
    private APIConfigRepository mWordRepository;
    @Mock
    private GetApiConfigListInteractor.Callback mMockedCallback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = new TestMainThread();
    }


    @Test
    public void testGetAPIConfigModel() throws Exception {

        ApiConfig dummyWordfirst = new ApiConfig();
        ApiConfig dummyWordsecond = new ApiConfig();
        ApiConfig dummyWordthird = new ApiConfig();


        List<ApiConfig> dummyWordList = new ArrayList<>();
        dummyWordList.add(dummyWordfirst);
        dummyWordList.add(dummyWordsecond);
        dummyWordList.add(dummyWordthird);


        Observable<List<ApiConfig>> dummyWordModel = Observable.just(dummyWordList);

        when(mWordRepository.getAPIConfigList(1, 100))
                .thenReturn(dummyWordModel);

        GetApiConfigListInteractorImpl interactor = new GetApiConfigListInteractorImpl(mExecutor, mMainThread, mWordRepository, 1, 100, mMockedCallback);
        interactor.run();
        Mockito.verify(mWordRepository).getAPIConfigList(1, 100);
        Mockito.verifyNoMoreInteractions(mWordRepository);
        Mockito.verify(mMockedCallback).onApiConfigListReceived(dummyWordModel);

    }
}
