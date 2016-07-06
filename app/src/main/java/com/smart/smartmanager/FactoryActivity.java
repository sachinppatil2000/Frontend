package com.smart.smartmanager;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.smart.smartmanager.R;

public class FactoryActivity extends ActionBarActivity {

    private String message = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   if(savedInstanceState == null) {
            super.onCreate(savedInstanceState);
            Intent latestIntent = getIntent();
            Bundle Extras = latestIntent.getExtras();
            if (Extras!=null) {
                if (Extras.containsKey("gcmMessage")) {
                    String msg = Extras.getString("gcmMessage");
                    message = msg;
                }
            }
            else
            {
                String msg_dummy = getString(R.string.dummy_data);
                message = msg_dummy;
            }
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FactoryActivityFragment fragment = new FactoryActivityFragment();
            fragmentTransaction.add(R.id.factory, fragment);
            //  fragment = new FactoryActivityFragment();
            // fragmentTransaction.add(R.id.factory,fragment);
            fragmentTransaction.commit();
            setContentView(R.layout.activity_factory);
      //  }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_factory, menu);
        return true;
    }

    public String getGCMMessage()
    {
        return message;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.string.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
