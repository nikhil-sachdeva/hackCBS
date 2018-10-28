package com.example.nikhil.bolt;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.deepakbaliga.beautifulgraph.Plotter;

import java.util.ArrayList;
import java.util.List;

import me.ithebk.barchart.BarChart;
import me.ithebk.barchart.BarChartModel;

public class StatisticsActivity extends AppCompatActivity {
FloatingActionButton predict;
    BarChartModel barChartModel11,barChartModel12,barChartModel9_2,barChartModel10_2;
    BarChart barChart1,barChart2,barChart3,barChart4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        barChart1 = (BarChart) findViewById(R.id.bar_chart1);
        barChart1.setBarMaxValue(700000);
        barChart2=findViewById(R.id.bar_chart2);
        barChart2.setBarMaxValue(2200);
        predict=findViewById(R.id.predict);
        final BarChartModel barChartModel3=getModel(486112,"#452312","2010");
        BarChartModel barChartModel4=getModel(512988,"#452312","2011");
        BarChartModel barChartModel5=getModel(529712,"#452312","2012");
        BarChartModel barChartModel6=getModel(531332,"#452312","2013");
        BarChartModel barChartModel7=getModel(574602,"#452312","2014");
        BarChartModel barChartModel8=getModel(430603,"#452312","2015");
        BarChartModel barChartModel9=getModel(462255,"#452312","2016");
        BarChartModel barChartModel10=getModel(463776,"#452312","2017");
        barChartModel11=getModel(419688,"#123456","2018");
        barChartModel12=getModel(396591,"#123456","2019");


        final BarChartModel barChartModel1_2=getModel(2104,"#452312","2010");
        BarChartModel barChartModel2_2=getModel(2104,"#452312","2011");
        BarChartModel barChartModel3_2=getModel(1822,"#452312","2012");
        BarChartModel barChartModel4_2=getModel(1778,"#452312","2013");
        BarChartModel barChartModel5_2=getModel(1629,"#452312","2014");
        BarChartModel barChartModel6_2=getModel(1582,"#452312","2015");
        BarChartModel barChartModel7_2=getModel(1548,"#452312","2016");
        BarChartModel barChartModel8_2=getModel(1500,"#452312","2017");
        barChartModel9_2=getModel(1682,"#123456","2018");
        barChartModel10_2=getModel(1951,"#123456","2018");









        barChart1.addBar(barChartModel3);
        barChart1.addBar(barChartModel4);
        barChart1.addBar(barChartModel5);
        barChart1.addBar(barChartModel6);
        barChart1.addBar(barChartModel7);
        barChart1.addBar(barChartModel8);
        barChart1.addBar(barChartModel9);
        barChart1.addBar(barChartModel10);



        barChart2.addBar(barChartModel1_2);
        barChart2.addBar(barChartModel2_2);
        barChart2.addBar(barChartModel3_2);
        barChart2.addBar(barChartModel4_2);
        barChart2.addBar(barChartModel5_2);
        barChart2.addBar(barChartModel6_2);
        barChart2.addBar(barChartModel7_2);
        barChart2.addBar(barChartModel8_2);


        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barChart1.addBar(barChartModel11);
                barChart1.addBar(barChartModel12);

                barChart2.addBar(barChartModel9_2);
                barChart2.addBar(barChartModel10_2);

            }
        });


    }
    public BarChartModel getModel(int val,String col,String text){
        BarChartModel barChartModel = new BarChartModel();
        barChartModel.setBarValue(val);
        barChartModel.setBarColor(Color.parseColor(col));
        barChartModel.setBarTag(null); //You can set your own tag to bar model
        barChartModel.setBarText(text);
        return barChartModel;
    }
}
