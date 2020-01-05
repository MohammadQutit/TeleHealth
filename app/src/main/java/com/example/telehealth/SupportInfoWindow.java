package com.example.telehealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class SupportInfoWindow implements GoogleMap.InfoWindowAdapter
{

    View w;
    Context con;


    public SupportInfoWindow(Context c)
    {
        con=c;
        w = LayoutInflater.from(c).inflate(R.layout.supportinfowindow, null);
    }

    public View getInfoWindow(Marker m)
    {
        fill(m);
        return w;
    }

    @Override
    public View getInfoContents(Marker m)
    {
        fill(m);
        return w;
    }

    public void fill (Marker m)
    {
        TextView t1 = (TextView) w.findViewById(R.id.t1);
        TextView t2 = (TextView) w.findViewById(R.id.t2);
        t1.setText(m.getTitle());
        t2.setText(m.getSnippet());
    }

}


