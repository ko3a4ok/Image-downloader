package ua.ko3a4ok.ololo.tests;

import android.app.Activity;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Button;
import android.widget.EditText;

import ua.ko3a4ok.ololo.MyActivity;
import ua.ko3a4ok.ololo.R;

/**
 * Created by ko3a4ok on 12/5/14.
 */
public class LargeFileLoadingTest extends ActivityInstrumentationTestCase2<MyActivity> {

    private static final String LARGE_FILE_URL = "http://dl.google.com/android/android-sdk_r22.6.2-macosx.zip";

    private EditText edit;
    private Button btn;
    private Activity activity;
    public LargeFileLoadingTest() {
        super(MyActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        edit = (EditText) activity.findViewById(R.id.url);
        btn = (Button) activity.findViewById(R.id.btn);

    }

    @LargeTest
    public void testLargeFile() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                edit.setText(LARGE_FILE_URL);
                btn.performClick();
            }
        });

        SystemClock.sleep(200000);

    }

}
