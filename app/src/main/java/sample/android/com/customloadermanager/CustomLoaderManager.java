
package sample.android.com.customloadermanager;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Created by a.katyal on 2015-03-25.
 */
public abstract class CustomLoaderManager implements ComponentCallbacks2 {

    public static CustomLoaderManager sInstance;

    public static final String LOADER_MANAGER_SERVICE = "custom_manager";

    public static CustomLoaderManager getInstance(Context context) {
        Context applicationContext = context.getApplicationContext();
        sInstance = (CustomLoaderManager)applicationContext
                .getSystemService(LOADER_MANAGER_SERVICE);
        if (sInstance == null) {
            sInstance = createLoaderManager(applicationContext);
        }
        return sInstance;
    }

    public static synchronized CustomLoaderManager createLoaderManager(Context context) {
        return new CustomLoaderManagerImpl(context);
    }

    public abstract void initLoader(int loaderId,Bundle args, int tag);

}

class CustomLoaderManagerImpl extends CustomLoaderManager {

    public CustomLoaderManagerImpl(Context context) {

    }

    @Override
    public void onTrimMemory(int level) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void initLoader(int loaderId, Bundle args, int tag) {

    }
}
