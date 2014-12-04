package ua.ko3a4ok.ololo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;


/**
 * Created by ko3a4ok on 12/2/14.
 */
public class Utils {
    /**
     * stolen from http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
     */
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static String saveImage(Context ctx, Bitmap bm) {
        String fileName = "IMG_"+System.currentTimeMillis() + ".png";
        return MediaStore.Images.Media.insertImage(ctx.getContentResolver(), bm, fileName, "");
    }

    public static void sendLinkToServer(Context ctx, String link) {
        final Intent sendLinkIntent = new Intent(ctx, MyService.class);
        sendLinkIntent.putExtra(MyService.LINK, link);
        ctx.startService(sendLinkIntent);
    }
}

