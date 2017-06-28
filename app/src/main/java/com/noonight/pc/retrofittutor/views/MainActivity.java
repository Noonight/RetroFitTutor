package com.noonight.pc.retrofittutor.views;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.noonight.pc.retrofittutor.R;
import com.noonight.pc.retrofittutor.database.managers.NumberStringManager;
import com.noonight.pc.retrofittutor.database.tables.NumberStringTable;
import com.noonight.pc.retrofittutor.views.setting.SettingsActivity;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private NumberStringManager manager;
    private SimpleCursorAdapter simpleCursorAdapter;

    private ListView lvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {

        lvOut = (ListView) findViewById(R.id.lvOut);
        manager = new NumberStringManager(getBaseContext());
        //manager.insert(1, "text");
        String[] from = new String[]{
                NumberStringTable.NumberString.COLUMN_NAME_NUMBER,
                NumberStringTable.NumberString.COLUMN_NAME_TEXT
        };
        int[] to = new int[]{
                R.id.tvNumber,
                R.id.tvText
        };
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.item, null, from, to);
        lvOut.setAdapter(simpleCursorAdapter);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    protected void onStart() {
        getSupportLoaderManager().getLoader(0).forceLoad();
        super.onStart();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(this, manager);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        simpleCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    static class MyCursorLoader extends CursorLoader {

        private static final String LOG_TAG = "LOG";
        NumberStringManager db;

        public MyCursorLoader(Context context, NumberStringManager db) {
            super(context);
            this.db = db;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Cursor loadInBackground() {
            Cursor c = db.read();
            if (c.moveToFirst()) {
                int number = c.getColumnIndex(NumberStringTable.NumberString.COLUMN_NAME_NUMBER);
                int text = c.getColumnIndex(NumberStringTable.NumberString.COLUMN_NAME_TEXT);
                do {
                    Log.d(
                            LOG_TAG,
                                    " number = " + c.getString(number) +
                                    ", text = " + c.getString(text)
                    );
                } while (c.moveToNext());
            } else {
                Log.d(LOG_TAG, "0 rows");
            }
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return c;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        /*MenuItem menuItem = menu.add(0, 1, 0, "Setting");
        menuItem.setIntent(new Intent(this, SettingFragment.class));*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menuShowSomeActivity:
                intent = new Intent(this, SomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuShowSetting:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuShowBasic:
                intent = new Intent(this, BasicActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }
}
