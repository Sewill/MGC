package com.mgc.club.app.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.mgc.club.app.Application.AppController;
import com.mgc.club.app.Model.Certificates;
import com.mgc.club.app.Model.Events;
import com.mgc.club.app.R;

import java.util.List;

/**
 * Created by savva on 07.07.2015.
 */
public class Events_Adapter extends BaseAdapter {
    private List<Events> events;
    private Activity activity;
    private LayoutInflater inflater;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public Events_Adapter(Activity activity, List<Events> events) {
        this.activity = activity;
        this.events = events;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        thumbNail.setDefaultImageResId(R.drawable.logo_mgc);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);

        // getting movie data for the row
        Events m = events.get(position);

        // thumbnail image
        if(m.getEventcover()!=null&&!m.getEventcover().equals("null")) {
            thumbNail.setImageUrl(m.getEventcover(), imageLoader);
        }
        // title
        title.setText(m.getName());

        // rating
        rating.setText(m.getStart()+"\n"+m.getFinish());

//        // genre
//        String genreStr = "";
//        for (String str : m.getGenre()) {
//            genreStr += str + ", ";
//        }
//        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
//                genreStr.length() - 2) : genreStr;
//        genre.setText(genreStr);
//
//        // release year
//        year.setText(String.valueOf(m.getYear()));

        return convertView;
    }

    private Certificates getCertificates(int pos) {
        return (Certificates) getItem(pos);
    }
}
