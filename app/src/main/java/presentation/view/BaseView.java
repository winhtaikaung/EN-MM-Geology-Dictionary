package presentation.view;

/**
 * Created by win on 3/25/17
 * <p/>
 * This interface represents a basic view. All views should implement these common methods.
 */
public interface BaseView {

    /**
     * This is a general method used for showing some kind of progress during a background task. For example, this
     * method should show a progress bar and/or disable buttons before some background work starts.
     */
    void showProgress();

    /**
     * This is a general method used for hiding progress information after a background task finishes.
     */
    void hideProgress();

    /**
     * This method is used for showing error messages on the UI.
     *
     * @param message The error message to be dislayed.
     */
    void showError(String message);


    /**
     * This method is used for hiding error messages on the UI.
     *
     * @param message The error message to be dislayed.
     */
    void hideError(String message);
}