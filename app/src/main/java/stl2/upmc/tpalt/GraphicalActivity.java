package stl2.upmc.tpalt;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class GraphicalActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private View chart;
    private Button btnChart;

    private int[] x = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
    private String[] friends = new String[] { "Tuan", "Thai", "Tin", "Nguyen", "Thanh", "Phong", "Nhan", "Dung", "Son",
            "Thuy", "Cuong", "Khanh" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphical);

        btnChart = (Button) findViewById(R.id.btn_chart);

        // Setting event click listener for the button btn_chart
        btnChart.setOnClickListener(onClickListener());

    }

    // Defining click event listener for the button btn_chart
    private View.OnClickListener onClickListener() {
        return  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createChart();
            }
        };
    }
    private void createChart() {
        int[] x = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        int[] height = { 170, 180, 177, 175, 173, 170, 173, 182, 174, 177, 168, 172};

        // Creating an XYSeries for Height
        XYSeries expenseSeries = new XYSeries("Height");
        // Adding data to Height Series
        for (int i = 0; i < x.length; i++) {
            expenseSeries.add(i, height[i]);
        }
        // Creating a dataset to hold height series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // Adding Height Series to dataset
        dataset.addSeries(expenseSeries);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer heightRenderer = new XYSeriesRenderer();
        heightRenderer.setColor(Color.GREEN);
        heightRenderer.setFillPoints(true);
        heightRenderer.setDisplayChartValues(true);

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setXLabels(0);
        renderer.setChartTitle("Comparing height chart ");
        renderer.setXTitle("Friends Name");
        renderer.setYTitle("Unit in centimeter");

        /***
         * Customizing graphs
         */
        // setting text size of the title
        renderer.setChartTitleTextSize(28);
        // setting text size of the axis title
        renderer.setAxisTitleTextSize(24);
        // setting text size of the graph lable
        renderer.setLabelsTextSize(12);
        // setting zoom buttons visiblity
        renderer.setZoomButtonsVisible(false);
        // setting pan enablity which uses graph to move on both axis
        renderer.setPanEnabled(true, true);
        // setting click false on graph
        renderer.setClickEnabled(false);
        // setting zoom to false on both axis
        renderer.setZoomEnabled(false, false);
        // setting lines to display on y axis
        renderer.setShowGridY(true);
        // setting lines to display on x axis
        renderer.setShowGridX(true);
        // setting legend to fit the screen size
        renderer.setFitLegend(true);
        // setting displaying line on grid
        renderer.setShowGrid(true);
        // setting zoom to false
        renderer.setZoomEnabled(false);
        // setting external zoom functions to false
        renderer.setExternalZoomEnabled(false);
        // setting displaying lines on graph to be formatted(like using
        // graphics)
        renderer.setAntialiasing(true);
        // setting to in scroll to false
        renderer.setInScroll(false);
        // setting to set legend height of the graph
        renderer.setLegendHeight(30);
        // setting x axis label align
        renderer.setXLabelsAlign(Paint.Align.CENTER);
        // setting y axis label to align
        renderer.setYLabelsAlign(Paint.Align.LEFT);
        // setting text style
        renderer.setTextTypeface("sans_serif", Typeface.NORMAL);
        // setting number of values to display in y axis
        renderer.setYLabels(20);
        //setting x axis min value
        renderer.setYAxisMin(100);
        // setting y axis max value
        renderer.setYAxisMax(200);
        // setting used to move the graph on xaxiz to .5 to the right
        renderer.setXAxisMin(-0.5);
        // setting used to move the graph on xaxiz to .5 to the right
        renderer.setXAxisMax(11);
        // setting bar size or space between two bars
        renderer.setBarSpacing(0.5);
        // Setting background color of the graph to transparent
        renderer.setBackgroundColor(Color.TRANSPARENT);
        // Setting margin color of the graph to transparent
        renderer.setMarginsColor(getResources().getColor(android.R.color.transparent));
        renderer.setApplyBackgroundColor(true);
        renderer.setScale(2f);
        // setting x axis point size
        renderer.setPointSize(4f);
        // setting the margin size for the graph in the order top, left, bottom,
        // right
        renderer.setMargins(new int[] { 10, 10, 10, 10 });

        for (int i = 0; i < x.length; i++) {
            renderer.addXTextLabel(i, friends[i]);
           // renderer.setXLabelsPadding(10);
            //renderer.setYLabelsPadding(10);
        }

        // Adding heightRender to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to
        // multipleRenderer
        // should be same
        renderer.addSeriesRenderer(heightRenderer);

        // this part is used to display graph on the xml
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);
        // remove any views before u paint the chart
        chartContainer.removeAllViews();
        //drawing bar chart
        chart = ChartFactory.getBarChartView(GraphicalActivity.this, dataset, renderer, BarChart.Type.DEFAULT);
        // adding the view to the linearlayout
        chartContainer.addView(chart);

    }
}
