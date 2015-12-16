package me.jorgev.itunes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by jorgeavaldez on 12/15/15.
 */
public class MediaAdapter extends ArrayAdapter<Media> {
    public MediaAdapter(Context context, Media[] books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Media b = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.advanced_search, parent, false);
        }

        TextView tTitle = (TextView) convertView.findViewById(R.id.super_item_title);
        TextView tExplicit = (TextView) convertView.findViewById(R.id.super_item_explicit);
        TextView tType = (TextView) convertView.findViewById(R.id.super_item_type);

        tTitle.setText(b.getTrackName());
        tExplicit.setText(b.getExplicitness());

        // Return the completed view to render on screen
        return convertView;
    }

}