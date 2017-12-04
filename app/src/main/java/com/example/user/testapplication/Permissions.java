package com.example.user.testapplication;

import android.*;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.Manifest;
import android.content.pm.PackageManager;

import static android.support.v4.app.ActivityCompat.requestPermissions;
import static android.support.v4.content.PermissionChecker.checkCallingOrSelfPermission;


/**
 * Created by User on 9/12/2017.
 */

public class Permissions {

    public static final int REQUEST_CALL=123;

    String[] permissions=new String[]{android.Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CONTROL_LOCATION_UPDATES};
    Context context;

    Permissions(Context context)
    {

        this.context=context;
        if ((hasPermissions(permissions)))
        {}
        else
            requestPerms(permissions,REQUEST_CALL);

    }



    public boolean hasPermissions(String[] permissions)
    {
        int res=0;
        String[] permiss=permissions;
        for (String perms:permiss)
        {
            res=checkCallingOrSelfPermission(context,perms);
            if (!(res== PackageManager.PERMISSION_GRANTED))
                return false;

        }


        return true;


    }
    private void requestPerms(String[] permissions, int requestCall) {

        String[] permiss=permissions;
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
            requestPermissions((Activity)context,permissions,REQUEST_CALL);
    }
}
