package me.jorgev.itunes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import me.jorgev.itunes.Media;
import me.jorgev.itunes.R;

/**
 * Created by jorgeavaldez on 12/20/15.
 */
public class FavoriteAdapter extends ArrayAdapter<Media> {
    public FavoriteAdapter(Context context, Media[] favs) {
        super(context, 0, favs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Media b = getItem(position);

        if (convertView == null) {
            convertView =
                    LayoutInflater.from(getContext()).inflate(R.layout.one_favorite, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.favorite_text_view);

        String wrapper = b.getWrapperType();

        if (wrapper.equals("track")) {
            textView.setText("SONG: " + b.getTrackName());
        }

        else if (wrapper.equals("collection")) {
            textView.setText("ALBUM: " + b.getCollectionName());
        }

        else if (wrapper.equals("artist")) {
            textView.setText("ARTIST: " + b.getArtistName());
        }

        else {
            textView.setText("VIDEO: " + b.getTrackName());
        }

        return convertView;
    }
}
