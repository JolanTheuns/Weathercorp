package com.example.wethercorp1;

import android.annotation.SuppressLint;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import static com.example.wethercorp1.ImportantConst.DATA_URL_APP_WEEK;

public class BodyDisplayGraph extends Fragment {

    private LineChart temperatureChart;
    private LineChart humidityChart;
    private LineChart airPressureChart;
    private LineChart luminosityChart;
    private ArrayList<Data> dataList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataList = new ArrayList<>();
        getJSON(DATA_URL_APP_WEEK);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.body_display_graph_fragment, container, false);
        temperatureChart = view.findViewById(R.id.temperature);
        humidityChart = view.findViewById(R.id.humidity);
        airPressureChart = view.findViewById(R.id.air_pressure);
        luminosityChart = view.findViewById(R.id.luminosity);

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
                    displayTemperatureChart();
                    displayHumidityChart();
                    displayLuminosityChart();
                    displayAirPressureChart();
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
        }
    }

    private void setSampleChart(LineChart chart){
        ArrayList<Entry> sampleValues;
        LineDataSet sampleSet;
        sampleValues = new ArrayList<>();
        sampleValues.add(new Entry(10, 300));
        sampleValues.add(new Entry(11, 250));
        sampleValues.add(new Entry(12, 230));
        sampleValues.add(new Entry(13, 230));
        sampleValues.add(new Entry(14, 230));
        sampleValues.add(new Entry(15, 230));

        sampleSet = new LineDataSet(sampleValues, "Sample Data");
        setChartDisplay(sampleSet,Color.BLUE);

        ArrayList<ILineDataSet> sampleDataSets = new ArrayList<>();
        sampleDataSets.add(sampleSet);
        LineData sampleData = new LineData(sampleDataSets);
        chart.setData(sampleData);
    }

    private void setChartDisplay (LineDataSet sampleSet, int color){
        sampleSet.setColor(color);
        sampleSet.setDrawCircles(true);
        sampleSet.setHighlightEnabled(true);
        sampleSet.setHighlightLineWidth(1f);
        sampleSet.setLineWidth(1.3f);
        sampleSet.setDrawValues(false);
        sampleSet.setDrawFilled(true);
        sampleSet.setFillColor(color);
        sampleSet.setCircleColor(Color.DKGRAY);
        sampleSet.setCircleRadius(3f);
        sampleSet.setDrawCircleHole(false);
    }

    private void displayHumidityChart() {
        humidityChart.setTouchEnabled(true);
        humidityChart.setScaleXEnabled(true);
        humidityChart.setScaleYEnabled(false);
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            values.add(new Entry(extractDate(dataList.get(i).getDate()), dataList.get(i).getHumidity()));
        }

        LineDataSet set = new LineDataSet(values, "Humidity %");
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        Description description = new Description();
        description.setText("Last 7 days");
        description.setTextSize(10);
        humidityChart.setDescription(description);

        setChartDisplay(set,Color.GREEN);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);
        LineData data = new LineData(dataSets);

        humidityChart.setData(data);
        humidityChart.setVisibleXRangeMinimum(3);
        humidityChart.fitScreen();
        humidityChart.animateX(1000);

        if (humidityChart.getData() != null
                && humidityChart.getData().getDataSetCount() > 0) {
            set = (LineDataSet) humidityChart.getData().getDataSetByIndex(0);
            set.setValues(values);
            humidityChart.getData().notifyDataChanged();
            humidityChart.notifyDataSetChanged();
        } else {
            setSampleChart(humidityChart);
        }
    }

    private void displayTemperatureChart() {
        temperatureChart.setTouchEnabled(true);
        temperatureChart.setScaleXEnabled(true);
        temperatureChart.setScaleYEnabled(false);
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            values.add(new Entry(extractDate(dataList.get(i).getDate()), dataList.get(i).getTemperature()));
        }

        LineDataSet set = new LineDataSet(values, "Temperature ºC");
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        Description description = new Description();
        description.setText("Last 7 days");
        description.setTextSize(10);
        temperatureChart.setDescription(description);

        setChartDisplay(set,Color.BLUE);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);
        LineData data = new LineData(dataSets);

        temperatureChart.setData(data);
        temperatureChart.setVisibleXRangeMinimum(3);
        temperatureChart.fitScreen();
        temperatureChart.animateX(1000);

        if (temperatureChart.getData() != null
                && temperatureChart.getData().getDataSetCount() > 0) {
            set = (LineDataSet) temperatureChart.getData().getDataSetByIndex(0);
            set.setValues(values);
            temperatureChart.getData().notifyDataChanged();
            temperatureChart.notifyDataSetChanged();
        } else {
            setSampleChart(temperatureChart);
        }
    }

    private void displayLuminosityChart() {
        luminosityChart.setTouchEnabled(true);
        luminosityChart.setScaleXEnabled(true);
        luminosityChart.setScaleYEnabled(false);
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            values.add(new Entry(extractDate(dataList.get(i).getDate()), dataList.get(i).getLuminosity()));
        }

        LineDataSet set = new LineDataSet(values, "Luminosity Lum");
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        Description description = new Description();
        description.setText("Last 7 days");
        description.setTextSize(10);
        luminosityChart.setDescription(description);

        setChartDisplay(set,Color.RED);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);
        LineData data = new LineData(dataSets);

        luminosityChart.setData(data);
        luminosityChart.setVisibleXRangeMinimum(3);
        luminosityChart.fitScreen();
        luminosityChart.animateX(1000);

        if (luminosityChart.getData() != null
                && luminosityChart.getData().getDataSetCount() > 0) {
            set = (LineDataSet) luminosityChart.getData().getDataSetByIndex(0);
            set.setValues(values);
            luminosityChart.getData().notifyDataChanged();
            luminosityChart.notifyDataSetChanged();
        } else {
            setSampleChart(luminosityChart);
        }
    }

    private void displayAirPressureChart() {
        airPressureChart.setTouchEnabled(true);
        airPressureChart.setScaleXEnabled(true);
        airPressureChart.setScaleYEnabled(false);
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            values.add(new Entry(extractDate(dataList.get(i).getDate()), dataList.get(i).getAirPressure()/100));
        }

        LineDataSet set = new LineDataSet(values, "AirPressure mbar");
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        Description description = new Description();
        description.setText("Last 7 days");
        description.setTextSize(10);
        airPressureChart.setDescription(description);

        setChartDisplay(set,Color.GRAY);
        airPressureChart.setVisibleXRangeMinimum(3);
        airPressureChart.fitScreen();
        airPressureChart.animateX(1000);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);
        LineData data = new LineData(dataSets);

        airPressureChart.setData(data);

        if (airPressureChart.getData() != null
                && airPressureChart.getData().getDataSetCount() > 0) {
            set = (LineDataSet) airPressureChart.getData().getDataSetByIndex(0);
            set.setValues(values);
            airPressureChart.getData().notifyDataChanged();
            airPressureChart.notifyDataSetChanged();
        } else {
            setSampleChart(airPressureChart);
        }
    }

    private int extractDate (String date){
        String subStr = date.substring(date.length()-2);
        return Integer.parseInt(subStr);
    }
}
