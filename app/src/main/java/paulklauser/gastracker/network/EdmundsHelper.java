package paulklauser.gastracker.network;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import paulklauser.gastracker.ui.addcar.AddCarActivity;

/**
 * Created by Paul on 6/3/2015.
 */
public class EdmundsHelper {

    public static final String API_KEY = "<API KEY HERE>";
    public static final String DBG_TAG = "Edmunds Helper";

    private AddCarActivity mActivity;

    private ArrayList<String> mMakes;
    private ArrayList<String> mModels;
    private ArrayList<String> mYears;

    public interface LoadMakesListener {
        void onMakesLoaded(ArrayList<String> carMakes);
    }
    public interface LoadModelsListener {
        void onModelsLoaded(ArrayList<String> carModels);
    }
    public interface LoadYearsListener {
        void onYearsLoaded(ArrayList<String> carYears);
    }

    public EdmundsHelper(AddCarActivity activity) {
        mActivity = activity;
    }

    public void loadMakesFromWeb(final LoadMakesListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final ArrayList<String> makes = new ArrayList<>();
                    JSONObject jsonObject = getResponse("api/vehicle/v2/makes");
                    final JSONArray makesJson = jsonObject.getJSONArray("makes");
                    for (int i = 0; i < makesJson.length(); i++) {
                        makes.add(((JSONObject) makesJson.get(i)).getString("name"));
                    }
                    mMakes = makes;
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onMakesLoaded(makes);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(DBG_TAG, "Couldn't open connection to Edmunds for makes");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(DBG_TAG, "Couldn't parse JSON for makes");
                }
            }
        }).start();
    }

    public void loadModelsFromWeb(final String make, final LoadModelsListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final ArrayList<String> models = new ArrayList<>();
                    JSONObject jsonObject = getResponse("api/vehicle/v2/" + make + "/models");
                    JSONArray modelsJson = jsonObject.getJSONArray("models");
                    for (int i = 0; i < modelsJson.length(); i++) {
                        models.add(((JSONObject)modelsJson.get(i)).getString("name"));
                    }
                    mModels = models;
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onModelsLoaded(models);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(DBG_TAG, "Couldn't open connection to Edmunds for models");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(DBG_TAG, "Couldn't parse JSON for models");
                }
            }
        }).start();
    }

    public void loadYearsFromWeb(final String make, final String model, final LoadYearsListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final ArrayList<String> years = new ArrayList<>();
                    JSONObject jsonObject = getResponse("api/vehicle/v2/" + make + "/" + model + "/years");
                    JSONArray yearsJson = jsonObject.getJSONArray("years");
                    for (int i = 0; i < yearsJson.length(); i++) {
                        years.add(((JSONObject)yearsJson.get(i)).getString("year"));
                    }
                    mYears = years;
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onYearsLoaded(years);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(DBG_TAG, "Couldn't open connection to Edmunds for years");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(DBG_TAG, "Couldn't parse JSON for years");
                }
            }
        }).start();
    }

    private JSONObject getResponse(String command) throws IOException, JSONException {
        HttpURLConnection connection = (HttpURLConnection) getEdmundsUrl(command).openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line + "\n");
        }
        Log.d(DBG_TAG, builder.toString());
        return new JSONObject(builder.toString());
    }

//    private HttpURLConnection openConnection() throws IOException {
//        HttpURLConnection connection = (HttpURLConnection) getEdmundsUrl("api/vehicle/v2/makes").openConnection();
//        Log.d(DBG_TAG, connection.toString());
//        return connection;
//    }

    private URL getEdmundsUrl(String endpoint) throws MalformedURLException {
        return new URL("https://api.edmunds.com/" + endpoint + "?fmt=json&api_key=" +
            API_KEY);
    }

}
