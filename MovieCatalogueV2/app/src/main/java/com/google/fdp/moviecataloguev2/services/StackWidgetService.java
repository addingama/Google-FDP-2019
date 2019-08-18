package com.google.fdp.moviecataloguev2.services;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.google.fdp.moviecataloguev2.widgets.StackRemoteViewsFactory;

/**
 * Created by gama on 2019-08-17.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext());
    }
}
