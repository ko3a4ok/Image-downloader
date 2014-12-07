package ua.ko3a4ok.ololo.tests;

import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.ko3a4ok.ololo.MyApplication;

/**
 * Created by ko3a4ok on 12/7/14.
 */
public class DataModelTest extends ApplicationTestCase<MyApplication> {
    private MyApplication app;
    public DataModelTest() {
        super(MyApplication.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
        app = getApplication();
    }

    @SmallTest
    public void testAutoRemoveUnusedImageHolders() {
        int loopsCount = 100;
        String[] images = PerformanceTest.IMAGES;
        for (int i = 0; i < loopsCount; i++)
            for (String imgLink : images)
                app.addTask(imgLink);
        int n = images.length;
        int idx = (int)(n*Math.random());
        String link = images[n-1-idx];
        for (int i = loopsCount-1; i > 0; i--) {
            app.removeTask(n * i + idx);
            assertNotNull(app.getImageHolder(link));
        }
        app.removeTask(idx);
        assertNull(app.getImageHolder(link));
    }

    @SmallTest
    public void testEqualsPercentage() {
        int loopsCount = 100;
        String[] images = PerformanceTest.IMAGES;
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < loopsCount; i++)
            list.addAll(Arrays.asList(images));
        for (String link: list)
            app.addTask(link);
        int n = images.length;
        String link = app.getTasks().get(0);
        int percentage = (int)(101*Math.random());
        app.getImageHolder(link).setPercentage(percentage);
        for (int i = 1; i < loopsCount; i++)
            assertEquals(percentage, app.getImageHolder(app.getTasks().get(n*i)).getPercentage());
    }

    @SmallTest
    public void testNullImageHolder() {
        app.addTask("a");
        assertNull(app.getImageHolder("b"));
        assertNotNull(app.getImageHolder("a"));
    }
}
