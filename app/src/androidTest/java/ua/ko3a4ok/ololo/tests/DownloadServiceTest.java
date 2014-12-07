package ua.ko3a4ok.ololo.tests;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.test.ServiceTestCase;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.MediumTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import ua.ko3a4ok.ololo.ImageHolder;
import ua.ko3a4ok.ololo.MyApplication;
import ua.ko3a4ok.ololo.MyService;

/**
 * Created by ko3a4ok on 12/7/14.
 */
public class DownloadServiceTest extends ServiceTestCase<MyService> {


    public DownloadServiceTest() {
        super(MyService.class);
    }

    @Override
    @UiThreadTest
    protected void setUp() throws Exception {
        super.setUp();
        setApplication(new MyApplication());
    }

    @MediumTest
    public void testDownload() throws InterruptedException {
        final Intent SERVICE_INTENT = new Intent(getContext(), MyService.class);
        final MyApplication app = (MyApplication) getApplication();
        final String link = PerformanceTest.IMAGES[0];
        final CountDownLatch latch = new CountDownLatch(1);
        ResultReceiver receiver = new ResultReceiver(null) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                ImageHolder ih = app.getImageHolder(link);
                assertNotNull(ih);
                if (ih.getUri() != null || ih.isFailed())
                    latch.countDown();
            }
        };
        SERVICE_INTENT.putExtra(MyService.RECEIVER, receiver);
        startService(SERVICE_INTENT);
        MyService.MyBinder binder = (MyService.MyBinder) bindService(SERVICE_INTENT);
        assertNotNull(binder);
        app.addTask(link);
        binder.loadLink(link);
        latch.await();
    }

    @MediumTest
    public void testFailDownload() throws InterruptedException {
        final Intent SERVICE_INTENT = new Intent(getContext(), MyService.class);
        final MyApplication app = (MyApplication) getApplication();
        final String link = "http://google.com/";
        final Object mutex = new Object();
        ResultReceiver receiver = new ResultReceiver(null) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                ImageHolder ih = app.getImageHolder(link);
                assertNotNull(ih);
                if (ih.getUri() != null || ih.isFailed())
                    synchronized (mutex) {
                        mutex.notify();
                    }
            }
        };
        SERVICE_INTENT.putExtra(MyService.RECEIVER, receiver);
        startService(SERVICE_INTENT);
        MyService.MyBinder binder = (MyService.MyBinder) bindService(SERVICE_INTENT);
        assertNotNull(binder);
        app.addTask(link);
        binder.loadLink(link);
        synchronized (mutex) {
            mutex.wait();
        }
        assert app.getImageHolder(link).isFailed();
    }


    @MediumTest
    public void testNotExistLink() throws InterruptedException, BrokenBarrierException {
        final Intent SERVICE_INTENT = new Intent(getContext(), MyService.class);
        final MyApplication app = (MyApplication) getApplication();
        final String link = "http://ololo.ua/mega_image.png";
        final CyclicBarrier barrier = new CyclicBarrier(2);

        ResultReceiver receiver = new ResultReceiver(null) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                ImageHolder ih = app.getImageHolder(link);
                if (ih.getUri() != null || ih.isFailed())
                    try {
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        };
        SERVICE_INTENT.putExtra(MyService.RECEIVER, receiver);
        startService(SERVICE_INTENT);
        MyService.MyBinder binder = (MyService.MyBinder) bindService(SERVICE_INTENT);
        assertNotNull(binder);
        app.addTask(link);
        binder.loadLink(link);
        barrier.await();
        ImageHolder ih = app.getImageHolder(link);
        assertNotNull(ih);
        assert ih.isFailed() && ih.getPercentage() == 0;
    }


    @MediumTest
    public void testStopDownload() throws InterruptedException {
        final Intent SERVICE_INTENT = new Intent(getContext(), MyService.class);
        final MyApplication app = (MyApplication) getApplication();
        final String link = PerformanceTest.IMAGES[0];
        final ReentrantLock lock = new ReentrantLock();

        final Condition condition = lock.newCondition();
        ResultReceiver receiver = new ResultReceiver(null) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                shutdownService();
                lock.lock();
                condition.signal();
                lock.unlock();
            }
        };
        SERVICE_INTENT.putExtra(MyService.RECEIVER, receiver);
        startService(SERVICE_INTENT);
        MyService.MyBinder binder = (MyService.MyBinder) bindService(SERVICE_INTENT);
        assertNotNull(binder);
        app.addTask(link);
        lock.lock();
        binder.loadLink(link);
        condition.await();
        ImageHolder ih = app.getImageHolder(link);
        assert ih.isFailed();
        lock.unlock();
    }

}
