package in.keatz.components.ui;

import android.app.Application;
import android.content.Context;




/**
 * Created by Inspiron on 5/25/2017.
 */

public class ComponentApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


    }

    public static ComponentApplication get(Context context) {
        return (ComponentApplication) context.getApplicationContext();
    }

}


