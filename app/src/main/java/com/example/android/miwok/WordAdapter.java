package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private static class ViewHolder
    {
        TextView englishTextView;
        TextView miwokiTextView;
    }

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects) {
        super(context, 0, objects);
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

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.englishTextView.setText(word.getEnglishTranslation());
        viewHolder.miwokiTextView.setText(word.getMiwokiTranslation());

        return convertView;
    }
}
