package com.google.fdp.moviecataloguev2.widgets;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.fdp.moviecataloguev2.R;
import com.google.fdp.moviecataloguev2.models.Movie;
import com.google.fdp.moviecataloguev2.repositories.MovieRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gama on 2019-08-17.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private final List<Movie> mWidgetItems = new ArrayList<>();
    private final Context mContext;

    public  StackRemoteViewsFactory(Context context) {
        mContext = context;

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        long token = Binder.clearCallingIdentity();
        mWidgetItems.clear();
        mWidgetItems.addAll(new MovieRepository((Application) mContext).getAllFavouriteMovieForWidget());
        Binder.restoreCallingIdentity(token);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        URL imageUrl = null;
        try {
            imageUrl = new URL(((Movie)this.mWidgetItems.get(i)).getPosterUrl());
            rv.setImageViewBitmap(R.id.imageView,
                    BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream()));

            Bundle extras = new Bundle();
            extras.putInt(FavouriteMovieWidget.EXTRA_ITEM, i);
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);

            rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
