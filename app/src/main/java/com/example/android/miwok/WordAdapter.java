package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private int _color;

    private static class ViewHolder
    {
        TextView englishTextView;
        TextView miwokiTextView;
        ImageView iconImageView;
        LinearLayout linearLayout;
    }

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects, int color) {
        super(context, 0, objects);
        this._color = color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Word word = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
            viewHolder.englishTextView = convertView.findViewById(R.id.english_text_view);
            viewHolder.miwokiTextView = convertView.findViewById(R.id.miwoki_text_view);
            viewHolder.iconImageView = convertView.findViewById(R.id.item_image_view);
            viewHolder.linearLayout = convertView.findViewById(R.id.background_linear_layout);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(word != null)
        {
            viewHolder.englishTextView.setText(word.getEnglishTranslation());
            viewHolder.miwokiTextView.setText(word.getMiwokiTranslation());
            viewHolder.linearLayout.setBackgroundColor(_color);
            if(word.getImageResourceId() > 0) {
                viewHolder.iconImageView.setImageResource(word.getImageResourceId());
            }
            else {
                viewHolder.iconImageView.setVisibility(View.GONE);
            }
        }

        return convertView;
    }
}
