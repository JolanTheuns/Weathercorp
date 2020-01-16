package com.example.wethercorp1;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.graphics.Color;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.wethercorp1.ImportantConst.DATA_URL_APP_DAY;
import static com.example.wethercorp1.ImportantConst.DATA_URL_APP_HOUR;
import static com.example.wethercorp1.ImportantConst.DATA_URL_APP_WEEK;

public class BodyDisplaySingleGraph extends Fragment {

    private LineChart singleChart;
    private TextView lastInformation;
    private String lastRecordTime;
    private Description description;
    private int xAxisIndicator;

    private ArrayList<Data> dataList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body_display_single_graph, container, false);
        singleChart = view.findViewById(R.id.singleGraph);
        Button hourButton = view.findViewById(R.id.lastHour);
        Button dayButton = view.findViewById(R.id.lastDay);
        Button weekButton = view.findViewById(R.id.lastWeek);
        lastInformation = view.findViewById(R.id.lastDataInformation);

        weekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xAxisIndicator = 1;
                description = new Description();
                description.setText("Last week");
                description.setTextSize(11);
                description.setTextColor(R.color.label);
                dataList = new ArrayList<>();
                getJSON(DATA_URL_APP_WEEK);
            }
        });

        dayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xAxisIndicator = 2;
                description = new Description();
                description.setText("Last day");
                description.setTextSize(11);
                description.setTextColor(R.color.label);
                dataList = new ArrayList<>();
                getJSON(DATA_URL_APP_DAY);
            }
        });

        hourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xAxisIndicator = 3;
                description = new Description();
                description.setText("Last hour");
                description.setTextSize(11);
                description.setTextColor(R.color.label);
                dataList = new ArrayList<>();
                getJSON(DATA_URL_APP_HOUR);
            }
        });
        return view;
    }

    private void getJSON(final String urlWebService) {
        @SuppressLint("StaticFieldLeak")
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadIntoData(s);
                    displayChart();
                    setInformationText();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;

                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoData(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length();i++){
            JSONObject object = jsonArray.getJSONObject(i);

            Integer airPressure = Integer.parseInt(object.getString("airPressure"));
            Log.v("airPressure",airPressure.toString());

            Integer humidity = Integer.parseInt(object.getString("humidity"));
            Log.v("humidity",humidity.toString());

            Integer temperature = Integer.parseInt(object.getString("temperature"));
            Log.v("temperature",temperature.toString());

            Integer luminosity = Integer.parseInt(object.getString("luminosity"));
            Log.v("airPress",luminosity.toString());

            String dateFromJson = object.getString("dateTime");
            Log.v("dateTime", dateFromJson);

            dataList.add(new Data(dateFromJson,temperature,humidity,airPressure,luminosity));

            if (i==jsonArray.length()-1){
                lastRecordTime = object.getString("dateTime");
            }
        }
    }

    private void setChartDisplay (LineDataSet sampleSet, int colorLine, int colorBackGround){
        sampleSet.setColor(colorLine);
        sampleSet.setDrawCircles(false);
        sampleSet.setHighlightEnabled(true);
        sampleSet.setHighlightLineWidth(1f);
        sampleSet.setLineWidth(1.3f);
        sampleSet.setDrawValues(false);
        sampleSet.setDrawFilled(true);
        sampleSet.setFillColor(colorBackGround);
    }

    private void displayChart() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
        { singleChart.setHardwareAccelerationEnabled(false); }

        singleChart.setTouchEnabled(true);
        singleChart.setScaleXEnabled(true);
        singleChart.setScaleYEnabled(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setHumidityLine());
        dataSets.add(setAirPressureLine());
        dataSets.add(setTemperatureLine());
        dataSets.add(setLuminosityLine());

        LineData data = new LineData(dataSets);
        data.setHighlightEnabled(true);

        singleChart.setDescription(description);
        singleChart.setData(data);
        singleChart.setVisibleXRangeMinimum(3);
        singleChart.fitScreen();
        singleChart.animateX(1000);
    }

    private LineDataSet setHumidityLine(){
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            if (xAxisIndicator==1) {
                values.add(new Entry(extractWeek(dataList.get(i).getDate()), dataList.get(i).getHumidity()));
            }else {
                values.add(new Entry(i+1, dataList.get(i).getHumidity()));
            }
        }

        LineDataSet setHumidity = new LineDataSet(values, "Hum %");
        setHumidity.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        setHumidity.setAxisDependency(singleChart.getAxisRight().getAxisDependency());
        setChartDisplay(setHumidity,Color.GREEN,Color.GREEN);

        return setHumidity;
    }

    private LineDataSet setAirPressureLine(){
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            if (xAxisIndicator==1) {
                values.add(new Entry(extractWeek(dataList.get(i).getDate()), dataList.get(i).getAirPressure()));
            }else {
                values.add(new Entry(i+1, dataList.get(i).getAirPressure()));
            }        }

        LineDataSet setAirPressure = new LineDataSet(values, "AirPress mbar");
        setAirPressure.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        setAirPressure.setAxisDependency(singleChart.getAxisLeft().getAxisDependency());
        setChartDisplay(setAirPressure,Color.GRAY,Color.GRAY);

        return setAirPressure;
    }

    private LineDataSet setLuminosityLine(){
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            if (xAxisIndicator==1) {
                values.add(new Entry(extractWeek(dataList.get(i).getDate()), dataList.get(i).getLuminosity()));
            }else {
                values.add(new Entry(i+1, dataList.get(i).getLuminosity()));
            }
        }

        LineDataSet setLuminosity = new LineDataSet(values, "Luminosity Lum");
        setLuminosity.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        setLuminosity.setAxisDependency(singleChart.getAxisRight().getAxisDependency());
        setChartDisplay(setLuminosity,Color.RED,Color.RED);

        return setLuminosity;
    }

    private LineDataSet setTemperatureLine(){
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            if (xAxisIndicator==1) {
                values.add(new Entry(extractWeek(dataList.get(i).getDate()), dataList.get(i).getTemperature()));
            }else {
                values.add(new Entry(i+1, dataList.get(i).getTemperature()));
            }
        }

        LineDataSet setTemperature = new LineDataSet(values, "Temp ºC");
        setTemperature.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        setTemperature.setAxisDependency(singleChart.getAxisRight().getAxisDependency());
        setChartDisplay(setTemperature,Color.BLUE,Color.BLUE);

        return setTemperature;
    }

    private int extractWeek (String date){
        String subStr = date.substring(date.length()-2);
        return Integer.parseInt(subStr);
    }

    @SuppressLint("SetTextI18n")
    private void setInformationText(){
        if (xAxisIndicator==1) {
            lastInformation.setText(
                            "Latest luminosity lux:"
                            + "\n" + dataList.get(dataList.size() - 1).getLuminosity().toString()
                            + " " + "lux" + "\n" + luxIndicate() + "\n\n"
                            + "Latest temperature ºC:"
                            + "\n" + dataList.get(dataList.size() - 1).getTemperature().toString()
                            + "ºC" + "\n\n"
                            + "Latest air pressure mbar:"
                            + "\n" + dataList.get(dataList.size() - 1).getAirPressure().toString()
                            + " " + "mBar" + "\n\n"
                            + "Latest humidity %:"
                            + "\n" + dataList.get(dataList.size() - 1).getHumidity().toString()
                            + "%" + "\n\nfrom "
                            + lastRecordTime);
        } else  if (xAxisIndicator==2){
            lastInformation.setText(
                            "Luminosity of the last day lux:"
                            + "\n" + dataList.get(dataList.size() - 1).getLuminosity().toString()
                            + " " + "lux" + "\n" + luxIndicate() + "\n\n"
                            + "Temperature of the last day ºC:"
                            + "\n" + dataList.get(dataList.size() - 1).getTemperature().toString()
                            + "ºC" + "\n\n"
                            + "Air pressure of the last day mbar:"
                            + "\n" + dataList.get(dataList.size() - 1).getAirPressure().toString()
                            + " " + "mBar" + "\n\n"
                            + "Humidity of the last day %:"
                            + "\n" + dataList.get(dataList.size() - 1).getHumidity().toString()
                            + "%" + "\n\nfrom "
                            + lastRecordTime+":00");
        } else {
            lastInformation.setText(
                            "Luminosity of the last hour lux:"
                            + "\n" + dataList.get(dataList.size() - 1).getLuminosity().toString()
                            + " " + "lux" + "\n" + luxIndicate() + "\n\n"
                            + "Temperature of the last hour ºC:"
                            + "\n" + dataList.get(dataList.size() - 1).getTemperature().toString()
                            + "ºC" + "\n\n"
                            + "Air pressure of the last hour mbar:"
                            + "\n" + dataList.get(dataList.size() - 1).getAirPressure().toString()
                            + " " + "mBar" + "\n\n"
                            + "Humidity of the last hour %:"
                            + "\n" + dataList.get(dataList.size() - 1).getHumidity().toString()
                            + "%" + "\n\nfrom "
                            + lastRecordTime);
        }
    }

    private String luxIndicate(){
        if (dataList.get(dataList.size() - 1).getLuminosity()==0) {
            return "Total darkness, the lights may be turned off.";
        }else if (dataList.get(dataList.size() - 1).getLuminosity()<20){
            return "Little too dark.";
        }else if (dataList.get(dataList.size() - 1).getLuminosity()<50){
            return "There is some light.";
        }else {
            return "The sensor may be placed in direct sunlight.";
        }
    }
}
