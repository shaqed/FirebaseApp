package com.example.shaked.firebaseapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloader {

    /**
     * Changes the ImageView to display the image in the given URL.
     * The operation will not happen immediately, as the operation should not block the main UI thread
     * while it loads something from the internet
     * @param imageView A non - null ImageView (make sure you have initialized it with findViewById
     *                  before
     * @param url A link to an image on the web (http://www........jpg)
     */
    public static void changeImageViewFromURL(final ImageView imageView, final String url) {

        new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(Void... voids) {
                // This code will run on a background Thread (a "worker" thread)
                // This is a good place to download the image from the internet
                try {
                    URL urlObject = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

                    Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());

                    connection.disconnect();
                    return bitmap;

                } catch (IOException e) {
                    e.printStackTrace();

                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                // This code will run when the doInBackground() is complete
                // this code will run on the UI Thread and here is a good
                // place for us to change the UI, hence - we change the ImageView here

                if (bitmap == null) {
                    Log.e("ID", "ImageDownloader Warning: bitmap loading failed");
                    return;
                }

                imageView.setImageBitmap(bitmap);
            }
        }.execute();

    }

}
