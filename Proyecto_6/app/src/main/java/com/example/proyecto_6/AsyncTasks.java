package com.example.proyecto_6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
    private String url;
    private Elements linkElements;
    private ArrayList<String> lista;
    public html(String url){
        lista = new ArrayList<>();
        this.url = url;
        linkElements = new Elements();
    }
    @Override
    protected Void doInBackground(String... strings) {

        try {
            Document doc = Jsoup.connect(url).get();
            Element resultsDiv = (Element) doc.getElementById("results");
            linkElements = resultsDiv.select("a[href]");



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;

    }
    @Override
    protected void onPostExecute(Void aVoid){
        for (int i = 0; i < 5; i++) {
            Log.d("html", "onPostExecute: "+ linkElements.get(i).attr("href"));
            Log.d("html", "onPostExecute: "+ linkElements.get(i).html());
        }
    }

    // Bajarse el html y almacenar el url y titulop a la pagina de la imagen
    //buscar el url del jpg y almacenar eso


}

class url extends AsyncTask<String,Void,Void> {
    private String url;
    private Elements linkElements;
    private ArrayList<String> lista;

    public url(String url) {
        lista = new ArrayList<>();
        this.url = url;
        linkElements = new Elements();
    }

    @Override
    protected Void doInBackground(String... strings) {

        try {
            Document doc = Jsoup.connect(url).get();
            linkElements = doc.select("a:contains(Download JPG)");
//            System.out.println(doc.select("a:contains(Download JPG)"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid){
        Log.d("html", "onPostExecute: "+ linkElements.size());
        for (int i = 0; i < linkElements.size(); i++) {
            Log.d("html", "onPostExecute: "+ linkElements.get(i).attr("href"));

        }
    }




}