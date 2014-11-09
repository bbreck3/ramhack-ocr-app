package com.example.jeffreychow.ramhacks;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.URL;


public class Home extends Activity {
    //Button button;
    //EditText editText;
    private Socket client;
    private FileInputStream fileInputStream;
    private BufferedInputStream bufferedInputStream;
    private OutputStream outputStream;
    private Button button;
    private TextView text;
    static  File file = new File("/storage/extSdCard/DCIM/Camera/20141108_182727.jpg");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button=(Button)findViewById(R.id.button1);
        //editText = (EditText)findViewById(R.id.editText);



        //Log.d("ffdffw",file.getAbsolutePath());
        text = (TextView)findViewById(R.id.textView1);
        SendImage image = new SendImage();
        image.execute();
        /*button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("EditText", editText.getText().toString()
                        );
                    }
                }
        );*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
class SendImage extends AsyncTask<String, Void, String> {

    private Exception exception;
    private Socket client;
    private FileInputStream fileInputStream;
    private BufferedInputStream bufferedInputStream;
    private OutputStream outputStream;
    private Button button;
    private TextView text;


    @Override
    protected String doInBackground(String...URL) {
        try{

            //File file = new File("/storage/extSdCard/DCIM/Camera/20141108_182727.jpg");
            client = new Socket("172.23.6.106", 4444);
            byte[] mybytearray = new byte[(int) Home.file.length()]; //create a byte array to file

            fileInputStream = new FileInputStream(Home.file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);

            bufferedInputStream.read(mybytearray, 0, mybytearray.length); //read the file

            outputStream = client.getOutputStream();

            outputStream.write(mybytearray, 0, mybytearray.length); //write file to the output stream byte by byte
            outputStream.flush();
            bufferedInputStream.close();
            outputStream.close();
            client.close();


            return "File Sent";

        }catch(UnknownHostException e) {
            e.printStackTrace();
            Log.d("Error","Crapppatch1");
            return "crappatch1";
        }
        catch (IOException e) {
            e.printStackTrace();
            Log.d("Error","Crapppatch2");
            return "crappatch2";
        }

    }



    /*protected void onPostExecute(RSSFeed feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }*/
}