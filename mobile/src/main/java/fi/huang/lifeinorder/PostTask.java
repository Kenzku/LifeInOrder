package fi.huang.lifeinorder;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ken on 21/09/14.
 */
public class PostTask extends AsyncTask<Void, Integer, String> {

    private AsyncTaskResponse toDoItem = null;
    private String data;

    public PostTask(String data) {
        this.data = data;
    }

    @Override
    protected String doInBackground(Void... voids) {
        // declare
        APIVersion1 apiVersion1 = new APIVersion1();
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        List<NameValuePair> nameValuePair = null;
        HttpResponse response = null;
        BufferedReader reader = null;
        String parsedResponse = null;

        apiVersion1.setPOST_TODO("556445");
        // Creating HTTP client
        httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        httpPost = new HttpPost(apiVersion1.getPOST_TODO());
        // Building post parameters, key and value pair
        nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("data", data));
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            response = httpClient.execute(httpPost);
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            if (reader != null) {
                parsedResponse = reader.readLine();
            }
            // writing response to log
            Log.d("Http Response:", response.toString());
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();

        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }

        return parsedResponse;
    }

    @Override
    protected void onPostExecute(String response) {
        List<String> aListData = new ArrayList<String>();
        aListData.add(response);
        toDoItem.processDone(aListData);
    }

    public void setToDoItem(AsyncTaskResponse aToDoItem) {
        this.toDoItem = aToDoItem;
    }
}