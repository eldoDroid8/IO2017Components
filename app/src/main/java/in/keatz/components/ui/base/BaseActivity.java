package in.keatz.components.ui.base;

import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;



/**
 * Created by Inspiron on 5/10/2017.
 */

public class BaseActivity extends LifecycleActivity {

    ProgressDialog progressDialog;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    protected void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    protected void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
    public void showLoading() {
        progressDialog = ProgressDialog.show(this, "PLease wait", "Loading..");
        progressDialog.show();
    }

    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
