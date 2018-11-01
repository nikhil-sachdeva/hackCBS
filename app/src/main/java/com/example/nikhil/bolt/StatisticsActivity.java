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
    BarChartModel barChartModel11,barChartModel12,barChartModel9_2,barChartModel10_2,barChartModel9_3,barChartModel10_3,barChartModel9_4,barChartModel10_4;
    BarChart barChart1,barChart2,barChart3,barChart4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        barChart1 = (BarChart) findViewById(R.id.bar_chart1);
        barChart1.setBarMaxValue(700000);
        barChart2=findViewById(R.id.bar_chart2);
        barChart2.setBarMaxValue(2200);
        barChart3=findViewById(R.id.bar_chart3);
        barChart3.setBarMaxValue(2200);
        barChart4=findViewById(R.id.bar_chart4);
        barChart4.setBarMaxValue(9000);
        predict=findViewById(R.id.predict);



        final BarChartModel barChartModel3=getModel(486112,"#FFA726","2010");
        BarChartModel barChartModel4=getModel(512988,"#FFA726","2011");
        BarChartModel barChartModel5=getModel(529712,"#FFA726","2012");
        BarChartModel barChartModel6=getModel(531332,"#FFA726","2013");
        BarChartModel barChartModel7=getModel(574602,"#FFA726","2014");
        BarChartModel barChartModel8=getModel(430603,"#FFA726","2015");
        BarChartModel barChartModel9=getModel(462255,"#FFA726","2016");
        BarChartModel barChartModel10=getModel(463776,"#FFA726","2017");
        barChartModel11=getModel(419688,"#E65100","2018");
        barChartModel12=getModel(396591,"#E65100","2019");


        final BarChartModel barChartModel1_2=getModel(2104,"#FFA726","2010");
        BarChartModel barChartModel2_2=getModel(2104,"#FFA726","2011");
        BarChartModel barChartModel3_2=getModel(1822,"#FFA726","2012");
        BarChartModel barChartModel4_2=getModel(1778,"#FFA726","2013");
        BarChartModel barChartModel5_2=getModel(1629,"#FFA726","2014");
        BarChartModel barChartModel6_2=getModel(1582,"#FFA726","2015");
        BarChartModel barChartModel7_2=getModel(1548,"#FFA726","2016");
        BarChartModel barChartModel8_2=getModel(1500,"#FFA726","2017");
        barChartModel9_2=getModel(1682,"#E65100","2018");
        barChartModel10_2=getModel(1951,"#E65100","2019");

        final BarChartModel barChartModel1_3=getModel(2153,"#FFA726","2010");
        BarChartModel barChartModel2_3=getModel(2110,"#FFA726","2011");
        BarChartModel barChartModel3_3=getModel(1866,"#FFA726","2012");
        BarChartModel barChartModel4_3=getModel(1820,"#FFA726","2013");
        BarChartModel barChartModel5_3=getModel(1671,"#FFA726","2014");
        BarChartModel barChartModel6_3=getModel(1622,"#FFA726","2015");
        BarChartModel barChartModel7_3=getModel(1591,"#FFA726","2016");
        BarChartModel barChartModel8_3=getModel(1584,"#FFA726","2017");
        barChartModel9_3=getModel(1789,"#E65100","2018");
        barChartModel10_3=getModel(2101,"#E65100","2019");


        final BarChartModel barChartModel1_4=getModel(7260,"#FFA726","2010");
        BarChartModel barChartModel2_4=getModel(7280,"#FFA726","2011");
        BarChartModel barChartModel3_4=getModel(6937,"#FFA726","2012");
        BarChartModel barChartModel4_4=getModel(7566,"#FFA726","2013");
        BarChartModel barChartModel5_4=getModel(8623,"#FFA726","2014");
        BarChartModel barChartModel6_4=getModel(8085,"#FFA726","2015");
        BarChartModel barChartModel7_4=getModel(7375,"#FFA726","2016");
        BarChartModel barChartModel8_4=getModel(6673,"#FFA726","2017");
        barChartModel9_4=getModel(4741,"#E65100","2018");
        barChartModel10_4=getModel(1925,"#E65100","2019");





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


        barChart3.addBar(barChartModel1_3);
        barChart3.addBar(barChartModel2_3);
        barChart3.addBar(barChartModel3_3);
        barChart3.addBar(barChartModel4_3);
        barChart3.addBar(barChartModel5_3);
        barChart3.addBar(barChartModel6_3);
        barChart3.addBar(barChartModel7_3);
        barChart3.addBar(barChartModel8_3);



        barChart4.addBar(barChartModel1_4);
        barChart4.addBar(barChartModel2_4);
        barChart4.addBar(barChartModel3_4);
        barChart4.addBar(barChartModel4_4);
        barChart4.addBar(barChartModel5_4);
        barChart4.addBar(barChartModel6_4);
        barChart4.addBar(barChartModel7_4);
        barChart4.addBar(barChartModel8_4);


        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barChart1.addBar(barChartModel11);
                barChart1.addBar(barChartModel12);

                barChart2.addBar(barChartModel9_2);
                barChart2.addBar(barChartModel10_2);

                barChart3.addBar(barChartModel9_3);
                barChart3.addBar(barChartModel10_3);

                barChart4.addBar(barChartModel9_4);
                barChart4.addBar(barChartModel10_4);

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
