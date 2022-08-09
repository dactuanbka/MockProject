package com.tuanqd.mockproject.main;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.loader.app.LoaderManager;

public class BaseViewModel extends AndroidViewModel {
    Context mContext;
    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.mContext=application.getApplicationContext();
}


}
