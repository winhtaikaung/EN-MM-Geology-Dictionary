package presentation.presenters;

import model.Word;
import presentation.view.BaseView;

/**
 * Created by winhtaikaung on 29/7/17.
 */

public interface WordDetailPresenter extends BaseView {

    void getWordDetail(String word);

    interface View extends BaseView {
        void onWordRetrieved(Word word);
    }
}
