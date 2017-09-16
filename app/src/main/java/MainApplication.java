import android.app.Application;

import util.DBHelper;

/**
 * Created by winhtaikaung on 14/9/17.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.MakeDB();
    }
}
