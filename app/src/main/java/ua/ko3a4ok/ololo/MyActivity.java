package ua.ko3a4ok.ololo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MyActivity extends ActionBarActivity {

    public static final String REFRESH_DATA = "refresh";
    public static final int BACK_PRESS_TIMEOUT = 1000;

    private EditText url;
    private View btn;
    private MenuItem serviceItem;
    private BaseAdapter adapter;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            adapter.notifyDataSetChanged();
        }
    };
    private MyApplication application;

    private long previousBackPressTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        url = (EditText) findViewById(R.id.url);
        btn = findViewById(R.id.btn);
        application = (MyApplication) getApplication();
        adapter = new MyAdapter(this, application);
        ((ListView)findViewById(android.R.id.list)).setAdapter(adapter);
        startService();
        application.setServiceRunning(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(REFRESH_DATA));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        serviceItem = menu.findItem(R.id.action_service);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_service) {
            if (!application.isServiceRunning()) {
                startService();
            } else
                stopService();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void stopService() {
        final Intent SERVICE_INTENT = new Intent(this, MyService.class);
        stopService(SERVICE_INTENT);
        serviceItem.setTitle(R.string.start_service);
        application.setServiceRunning(false);
        btn.setEnabled(false);
    }

    private void startService() {
        final Intent SERVICE_INTENT = new Intent(this, MyService.class);
        startService(SERVICE_INTENT);
        btn.setEnabled(true);
        application.setServiceRunning(true);
        if (serviceItem != null)
            serviceItem.setTitle(R.string.stop_service);
        btn.setEnabled(true);
    }

    public void onDownload(View v) {
        String link = url.getText().toString();
        if (TextUtils.isEmpty(link)) {
            Toast.makeText(this, R.string.empty_url, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!URLUtil.isValidUrl(link)) {
            Toast.makeText(this, R.string.not_valid_url, Toast.LENGTH_SHORT).show();
            return;
        }
        sendLink(link);
    }

    private void sendLink(String link) {
        ImageHolder ih = application.getImageHolder(link);
        boolean isExist = ih != null;
        application.addTask(link);
        adapter.notifyDataSetChanged();
        if (!isExist || ih.isFailed())
            Utils.sendLinkToServer(this, link);
    }


    @Override
    public void onBackPressed() {
        if (application.isServiceRunning()) {
            long now = System.currentTimeMillis();
            if (now - previousBackPressTime <= BACK_PRESS_TIMEOUT)
                stopService();
            else
                previousBackPressTime = now;
        } else
            super.onBackPressed();
    }

}
