/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private ArrayList<Word> words;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        int color = getResources().getColor(R.color.category_colors);

        words = new ArrayList<>();
        words.add(new Word("red", "ahsjahs", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("green", "sdsds", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "sdsdsd", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "dfsdfs", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "dfsfsdfsd", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "dfdfdfd", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("dusty yellow", "fdfsdf", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow", "fdfdfdfd", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        ListView listView = findViewById(R.id.list);
        WordAdapter adapter = new WordAdapter(this, words, color);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                    } else {
                        mediaPlayer.release();
                    }
                }
//                Log.d("NumbersActivity", ((Word)parent.getItemAtPosition(position)).getEnglishTranslation());
                mediaPlayer = MediaPlayer.create(ColorsActivity.this, words.get(position).getAudioResourceId());
                mediaPlayer.start();
            }
        });
    }
}
