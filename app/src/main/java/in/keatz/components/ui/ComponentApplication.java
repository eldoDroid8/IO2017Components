package in.keatz.components.ui;

import android.app.Application;
import android.content.Context;

public class ComponentApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static ComponentApplication get(Context context) {
        return (ComponentApplication) context.getApplicationContext();
    }
}


