
package sample.android.com.customloadermanager;

import java.util.MissingResourceException;

/**
 * Created by a.katyal on 2015-03-26.
 */
public class MyTask implements Runnable {

    private final String TAG = MyTask.this.getClass().toString();
    private final MyTaskListener mTaskListener;
    private final String ERROR_MSG = "Please ensure Task Listener is properly initalized";

    public MyTask(MyTaskListener lTaskListener) {
        mTaskListener = lTaskListener;
    }

    @Override
    public void run() {
        if (null == mTaskListener)
            throw new MissingResourceException(ERROR_MSG, TAG, mTaskListener.toString());

    }
}
