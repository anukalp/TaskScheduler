
package sample.android.com.customloadermanager;

/**
 * Created by a.katyal on 2015-03-26.
 */
public class MyTaskInfo {
    private final int mId;

    private Object mDataSet;

    private ThreadStatus mThreadStatus;

    private CustomThreadManagerCallBacks myTaskListener;

    public enum ThreadStatus {
        Init, Started, Running, Completed, Rejected
    }

    public ThreadStatus getTaskStatus() {
        return mThreadStatus;
    }

    public void setThreadStatus(ThreadStatus status) {
        mThreadStatus = status;
    }

    public MyTaskInfo(int mId) {
        mThreadStatus = ThreadStatus.Init;
        this.mId = mId;
    }

    public int getId() {
        return mId;
    }

    public void setDataSet(Object mDataSet) {
        this.mDataSet = mDataSet;
    }
}
