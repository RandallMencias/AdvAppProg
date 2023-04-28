package com.example.proyecto_6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class LoadImageTask extends AsyncTask<String,Void, Bitmap> {
    private ImageView imageView;

    public LoadImageTask(ImageView imageView) {
    this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = null;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(strings[0]); // create URL for image

            // open an HttpURLConnection, get its InputStream
            // and download the image
            connection = (HttpURLConnection) url.openConnection();

            try (InputStream inputStream = connection.getInputStream()) {
                bitmap = BitmapFactory.decodeStream(inputStream);
//                bitmaps.put(params[0], bitmap); // cache for later use
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect(); // close the HttpURLConnection
        }

        return bitmap;

    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }
}

class html extends AsyncTask<String,Void,Void>{
    private URL url;
    private ArrayList<String> lista;
    public html(URL url){
        lista = new ArrayList<>();
        this.url = url;
    }
    @Override
    protected Void doInBackground(String... strings) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));){
            while (reader.readLine() != null){
                lista.add(reader.readLine());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid){
        for (int i = 75; i < 80; i++) {
            Log.d("html", "onPostExecute: "+lista.get(i));
        }
    }


}
