package com.rangon.en_mmgeologydictionary.services;

import com.rangon.en_mmgeologydictionary.model.restmodels.WordListResponse;
import com.rangon.en_mmgeologydictionary.network.Endpoints;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by win on 9/10/17.
 */

public interface WordService {

    @GET(Endpoints.WORD_API)
    Observable<WordListResponse> getWordList(@Query("word") String wordIndex,
                                             @Query("page") int page,
                                             @Query("limit") int limit);
}
