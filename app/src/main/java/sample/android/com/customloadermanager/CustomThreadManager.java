
package sample.android.com.customloadermanager;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by a.katyal on 2015-03-25.
 */
public abstract class CustomThreadManager {

    public static CustomThreadManager sInstance;

    public static CustomThreadManager getInstance(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (sInstance == null) {
            sInstance = createThreadManager(applicationContext);
        }
        return sInstance;
    }

    public static synchronized CustomThreadManager createThreadManager(Context applicationContext) {
        return new CustomThreadManagerImpl(applicationContext);
    }

    public abstract void initTask(int Id, Bundle args, CustomThreadManagerCallBacks callBacks);
}

class CustomThreadManagerImpl extends CustomThreadManager {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;

    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;

    private static final int KEEP_ALIVE = 1;

    private HashMap<MyTaskInfo, MyTask> mAllTasks = new HashMap<MyTaskInfo, MyTask>();

    private HashMap<MyTaskInfo, MyTask> mRejectedTasks = new HashMap<MyTaskInfo, MyTask>();

    private HashMap<MyTaskInfo, MyTaskListener> mTaskListeners = new HashMap<MyTaskInfo, MyTaskListener>();

    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(
            128);

    private int mCurrentJobId;

    /**
     * An {@link java.util.concurrent.Executor} that can be used to execute
     * tasks in parallel.
     */
    private static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, sPoolWorkQueue);

    public CustomThreadManagerImpl(Context context) {
    }

    @Override
    public void initTask(int Id, Bundle args, CustomThreadManagerCallBacks callBacks) {
        MyTask myTask = null;
        MyTaskInfo taskInfo = null;
        MyTaskListener taskListener = null;
        try {
            myTask = new MyTask(taskListener);
            taskInfo = callBacks.onCreateTask();
            mAllTasks.put(taskInfo, myTask);
            mTaskListeners.put(taskInfo, taskListener);
            THREAD_POOL_EXECUTOR.execute(myTask);
        } catch (RejectedExecutionException e) {
            mRejectedTasks.put(taskInfo, myTask);
        }
    }
}
