package sample.android.com.customloadermanager;

/**
 * Created by a.katyal on 2015-03-26.
 */
public interface CustomThreadManagerCallBacks {

    public void pauseTask();

    public void resumeTask();

    public boolean abortTask();

    public MyTaskInfo onCreateTask();

    public void onLoadFinished(MyTaskInfo taskInfo);

}
