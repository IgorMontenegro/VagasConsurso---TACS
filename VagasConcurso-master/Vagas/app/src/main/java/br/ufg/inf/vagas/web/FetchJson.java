package br.ufg.inf.vagas.web;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.vagas.R;
import br.ufg.inf.vagas.adapters.VagaAdapter;
import br.ufg.inf.vagas.models.Vaga;
import br.ufg.inf.vagas.utils.Utils;

public class FetchJson extends AsyncTask<List, Void, List> {
    private ProgressDialog ringProgressDialog;
    Context mContext;
    private List<Vaga> mList = new ArrayList<>();
    VagaAdapter adapter;
    RecyclerView mRecyclerView;

    public FetchJson(Context c,RecyclerView r, List<Vaga> l) {
        mContext = c;
        mRecyclerView = r;
        mList = l;
    }

    private final String LOG_TAG = FetchJson.class.getSimpleName();

        /* The date/time conversion code is going to be moved outside the asynctask later,
         * so for convenience we're breaking it out into its own method now.
         */






    @Override
    protected List doInBackground(List... params) {
        Utils utils = new Utils();

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String JsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            final String APIARY_BASE_URL =
                    "http://private-8be95-vagasconcursos.apiary-mock.com/notes";




            URL url = new URL(APIARY_BASE_URL);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);
            urlConnection.connect();



            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.

            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.

            }
            JsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return utils.getMatchDataFromJson(JsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        // This will only happen if there was an error getting or parsing the forecast.
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ringProgressDialog = ProgressDialog.show(mContext, mContext.getResources().getString(R.string.warning_wait),
                mContext.getResources().getString(R.string.warning_receiving_data), true);
        ringProgressDialog.show();
    }

    @Override
    protected void onPostExecute(List result) {
        ringProgressDialog.dismiss();
        if(result!=null){
            mList.clear();
            mList.addAll(result);
            adapter = new VagaAdapter(mContext, mList);
            mRecyclerView.setAdapter(adapter);
            super.onPostExecute(result);
        }}
}




