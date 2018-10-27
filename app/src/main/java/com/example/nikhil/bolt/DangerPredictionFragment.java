package com.example.nikhil.bolt;

import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class DangerPredictionFragment extends Fragment {

    AccidentDatabaseHelper dbHelper;
    Location myLocation;
    LocationAdapter adapter;
    RecyclerView recyclerView;
    TextView indexView;
    TextView constituencyLabel;
    TextView constituencyIdx;

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.danger_fragment,container,false);
       // Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

        recyclerView = view.findViewById(R.id.recycler);
        indexView = view.findViewById(R.id.index);
        constituencyLabel = view.findViewById(R.id.label);
        constituencyIdx = view.findViewById(R.id.label_index);

        dbHelper = new AccidentDatabaseHelper(getContext());
        myLocation = new Location("");
        myLocation.setLatitude(28.6129);
        myLocation.setLongitude(77.2273);

        try {
            getDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("Messages", "IOException in main()");
            Log.i("Messages", e.toString());
        }

        ArrayList<LocationData> list = getClosest();
        String zone = list.get(0).getZone();
        int count = dbHelper.getZonewiseCount(zone);
        float odds = (float) count/(145-count);
        double index = average(list)*odds*10;
        double zoneIndex = dbHelper.getZoneIndex(zone);
        Log.i("Messages", "Zone index: "+zone+ "::"+dbHelper.getZoneIndex(zone));

        Log.i("Messages", "Particular zone index: "+"Count: "+count+": "+ average(list)*odds);
        dbHelper.dropTable();

        indexView.setText(new DecimalFormat("##.##").format(index)+"");
        constituencyLabel.setText(getConstituency(list.get(0).getZone())+": ");
        constituencyIdx.setText(new DecimalFormat("##.##").format(zoneIndex)+"");

        adapter = new LocationAdapter(list, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

    public static DangerPredictionFragment newInstance() {

        Bundle args = new Bundle();

        DangerPredictionFragment fragment = new DangerPredictionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void getDatabase() throws IOException {

        InputStream is = getContext().getAssets().open("AccidentProneAreas.csv");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line = "";

        int count = 0;
        while ((line = buffer.readLine()) != null) {
            if(count==0){
                count++;
                continue;
            }

            String[] str = line.split(",");
            String name = str[1];
            double lat = Double.parseDouble(str[2]);
            double lang = Double.parseDouble(str[3]);
            int simpleAccidents = Integer.parseInt(str[4]);
            int fatalAccidents = Integer.parseInt(str[5]);
            double dangerPoints = Double.parseDouble(str[6]);
            String zone = str[7];
            double dangerIndex = Double.parseDouble(str[8]);
            double distance = getDistance(lat, lang);
            dbHelper.insertData(name,lat,lang,simpleAccidents,fatalAccidents,dangerPoints,zone,dangerIndex,distance);
        }
    }

    private double getDistance(double lat, double lang){
        Location targetLocation = new Location("");
        targetLocation.setLatitude(lat);
        targetLocation.setLongitude(lang);
        return myLocation.distanceTo(targetLocation)/1000;
    }

    private ArrayList<LocationData> getClosest(){
        ArrayList<LocationData> list= new ArrayList<>();
        Cursor cr = dbHelper.getAll();

        int count = 0;
        while (cr.moveToNext() && count<5){
            list.add(new LocationData(cr.getString(cr.getColumnIndex("name")),
                    cr.getDouble(cr.getColumnIndex("distance")),
                    cr.getDouble(cr.getColumnIndex("lat")),
                    cr.getDouble(cr.getColumnIndex("lang")),
                    cr.getDouble(cr.getColumnIndex("danger_idx")),
                    cr.getString(cr.getColumnIndex("zone"))));
            count++;
            Log.i("Messages", count+". "+
                    cr.getDouble(cr.getColumnIndex("distance")));
        }
        cr.close();
        return list;
    }

    private double average(ArrayList<LocationData> data){
        double avg = 0;
        for(LocationData location : data){
            avg+=location.getScore();
        }
        avg/=data.size();
        return avg;
    }

    private String getConstituency(String label) {
        switch (label){
            case "new": return "NEW DELHI";
            case "south": return "SOUTH DELHI";
            case "northeast": return "NORTH-EAST DELHI";
            case "northwest": return "NORTH-WEST DELHI";
            case "east": return "EAST DELHI";
            case "west": return "WEST DELHI";
            case "chandnichowk": return "CHANDNI CHOWK";
            default: return "Unknown Constituency";
        }
    }


}
