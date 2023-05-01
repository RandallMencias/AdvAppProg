package com.example.proyecto_6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


//Carga la Imagen al ImageView
class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
    private String a;
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

                InputStream inputStream = connection.getInputStream();
                //Usar URL para descargar imagen
                Bitmap temp = BitmapFactory.decodeStream(inputStream);
                //definir dimensiones de bitmap
                bitmap  = Bitmap.createScaledBitmap(temp, 500, 500, false);
                a = strings[1];



        } catch (Exception e) {
            e.printStackTrace();
        }


        return bitmap;

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        //actualizar imageview y colocar en cache
        imageView.setImageBitmap(bitmap);
        s.addtoCache(a, bitmap);
    }
}


// ---------------------------------------------------------------------------------------------***---------------------------------------------------------------------------------------------

//cargar objetos Nasa
class LoadData extends AsyncTask<String, Integer, Integer> {
    private String url;
    private Elements linkElements;
    private final ArrayAdapter<String> adapter;
    private singleton s = singleton.getInstance(); //instancia de la clase singleton

    private Elements JPGElements;

    public LoadData(String url, ArrayAdapter adapter) {
        this.url = url;
        this.adapter = adapter;
        linkElements = new Elements();
        JPGElements = new Elements();
    }

    @Override
    protected Integer doInBackground(String... strings) {

        try {
            //obtener elementos para clase Nasa
            Document doc = Jsoup.connect(url).get();
            Element resultsDiv = (Element) doc.getElementById("results");
            linkElements = resultsDiv.select("a[href]");
            for (int i = 0; i < linkElements.size() - 1; i++) {
                Document doc2 = Jsoup.connect(linkElements.get(i).attr("href")).get();
                JPGElements = doc2.select("a:contains(Download JPG)");
                if (JPGElements.size() > 0) {//obtener los items validos
                    URL JPG = new URL(JPGElements.get(0).attr("href"));
                    HttpURLConnection connection = (HttpURLConnection) JPG.openConnection();
                    if ((connection.getContentLength() < 10000000))//validar tamanio de imagenes
                    {
                        //crear elemento Nasa y agregarlo al singleton
                        connection.disconnect();
                        s.addNasa(new Nasa(linkElements.get(i).html(), linkElements.get(i).attr("href"), JPGElements.get(0).attr("href")));
                        publishProgress();
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Integer... porc) {
        //mantener actualizado el adapter
        adapter.clear();
        adapter.add(s.showImageMap().toString());
        adapter.notifyDataSetChanged();
    }

}














