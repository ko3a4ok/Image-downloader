package ua.ko3a4ok.ololo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends ActionBarActivity {

    public static final String REFRESH_DATA = "refresh";
    public static final int BACK_PRESS_TIMEOUT = 1000;

    private boolean runningService;

    private EditText url;
    private View btn;
    private MenuItem serviceItem;
    private BaseAdapter adapter;
    private List<String> tasks = new ArrayList<String>();
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
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.name, tasks) {
            final int TASK_TYPE = 0;
            final int IMAGE_TYPE = 1;
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                int type = getItemViewType(position);
                final String link = getItem(position);
                ImageHolder ih = application.getImageHolder(link);
                if (type == IMAGE_TYPE) {
                    if (convertView == null) {
                        convertView = getLayoutInflater().inflate(R.layout.image_item, null);
                        convertView.setTag(new ImageViewHolder(convertView));
                    }
                    ImageViewHolder ivh = (ImageViewHolder) convertView.getTag();
                    ivh.name.setText(link);
                    ivh.image.setImageURI(Uri.parse(ih.getUri()));
                    return convertView;
                }
                convertView = super.getView(position, convertView, parent);
                if (convertView.getTag() == null) {
                    convertView.setTag(new ViewHolder(convertView));
                }
                if (ih == null) return convertView;
                ViewHolder holder = (ViewHolder) convertView.getTag();
                holder.progress.setProgress(ih.getPercentage());
                if (ih.isFailed()) {
                    holder.failed.setVisibility(View.VISIBLE);
                    holder.failed.setText(ih.getRawData() == null ? R.string.network_problem: R.string.storing_problem);
                    holder.failedLayout.setVisibility(View.VISIBLE);
                    holder.remove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tasks.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    holder.tryAgain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (runningService)
                                sendLinkToServer(link);
                            else
                                Toast.makeText(MyActivity.this, R.string.service_not_started, Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    holder.failed.setVisibility(View.INVISIBLE);
                    holder.failedLayout.setVisibility(View.GONE);
                }
                return convertView;
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int position) {
                final String link = getItem(position);
                ImageHolder ih = application.getImageHolder(link);
                return ih != null && ih.getUri() != null ? IMAGE_TYPE : TASK_TYPE;
            }


        };
        ((ListView)findViewById(android.R.id.list)).setAdapter(adapter);
        startService();
        runningService = true;
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
            if (!runningService) {
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
        runningService = false;
        btn.setEnabled(false);
    }

    private void startService() {
        final Intent SERVICE_INTENT = new Intent(this, MyService.class);
        startService(SERVICE_INTENT);
        btn.setEnabled(true);
        runningService = true;
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
        tasks.add(link);
        adapter.notifyDataSetChanged();
        sendLinkToServer(link);
    }

    private void sendLinkToServer(String link) {
        final Intent sendLinkIntent = new Intent(this, MyService.class);
        sendLinkIntent.putExtra(MyService.LINK, link);
        startService(sendLinkIntent);
    }


    @Override
    public void onBackPressed() {
        if (runningService) {
            long now = System.currentTimeMillis();
            if (now - previousBackPressTime <= BACK_PRESS_TIMEOUT)
                stopService();
            else
                previousBackPressTime = now;
        } else
            super.onBackPressed();
    }

    private class ViewHolder {
        TextView name;
        ProgressBar progress;
        TextView failed;
        View failedLayout;
        View tryAgain;
        View remove;

        public ViewHolder(View convertView) {
            name = (TextView) convertView.findViewById(R.id.name);
            progress = (ProgressBar) convertView.findViewById(R.id.progress);
            failed = (TextView) convertView.findViewById(R.id.failed);
            failedLayout = convertView.findViewById(R.id.failed_buttons);
            tryAgain =  failedLayout.findViewById(R.id.try_again);
            remove = failedLayout.findViewById(R.id.remove);
        }
    }

    private class ImageViewHolder {
        TextView name;
        ImageView image;

        public ImageViewHolder(View convertView) {
            name = (TextView) convertView.findViewById(R.id.link);
            image = (ImageView) convertView.findViewById(R.id.img);
        }
    }
}
