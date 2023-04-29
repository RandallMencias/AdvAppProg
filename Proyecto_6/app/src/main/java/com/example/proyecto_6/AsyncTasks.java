package com.example.proyecto_6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



//Carga la Imagen al ImageView
class LoadImageTask extends AsyncTask<String,Void, Bitmap> {
    private ImageView imageView;
    private singleton s = singleton.getInstance(); //instancia de la clase singleton




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
//                s.addtoCache(strings[1],bitmap);
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








// ---------------------------------------------------------------------------------------------***---------------------------------------------------------------------------------------------


class LoadData extends AsyncTask<String,Void,Void>{
    private String url;
    private Elements linkElements;
    private singleton s = singleton.getInstance(); //instancia de la clase singleton

    private Elements JPGElements;
    public LoadData(String url){
        this.url = url;
        linkElements = new Elements();
        JPGElements = new Elements();
    }
    @Override
    protected Void doInBackground(String... strings) {

        try {
            Document doc = Jsoup.connect(url).get();
            Element resultsDiv = (Element) doc.getElementById("results");
            linkElements = resultsDiv.select("a[href]");
            for(int i = 0; i < linkElements.size()-1; i++){
                Document doc2 = Jsoup.connect(linkElements.get(i).attr("href")).get();
                JPGElements = doc2.select("a:contains(Download JPG)");
                if(JPGElements.size() > 0) {
                    s.addNasa(new Nasa(linkElements.get(i).html(),linkElements.get(i).attr("href"), JPGElements.get(0).attr("href") ));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return null;
    }


    



}














