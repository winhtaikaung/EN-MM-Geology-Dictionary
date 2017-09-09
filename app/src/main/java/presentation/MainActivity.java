package presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.rangon.en_mmgeologydictionary.R;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import model.Word;
import services.WordService;

import static network.RestClient.getRetrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRetrofit().create(WordService.class).getWordList("a", Integer.parseInt("1"), Integer.parseInt("10"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(wordListResponse -> wordListResponse.getData())
                .subscribe(new Consumer<List<Word>>() {
                    @Override
                    public void accept(List<Word> words) throws Exception {
                        Log.e("WORDS_UNI", String.valueOf(words.get(0).getMeaningUni()));
                        Log.e("WORDS_ZAWGYI", String.valueOf(words.get(0).getMeaningZg()));
                    }
                });
    }
}
