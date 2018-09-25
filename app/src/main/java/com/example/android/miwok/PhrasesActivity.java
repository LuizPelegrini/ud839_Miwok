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

public class PhrasesActivity extends AppCompatActivity {

    private ArrayList<Word> words;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        int color = getResources().getColor(R.color.category_phrases);

        words = new ArrayList<>();
        words.add(new Word("phrase1", "lutti", R.raw.phrase_are_you_coming));
        words.add(new Word("phrase2", "otiiko", R.raw.phrase_are_you_coming));
        words.add(new Word("phrase3", "tolookosu", R.raw.phrase_are_you_coming));
        words.add(new Word("phrase4", "oyyisa", R.raw.phrase_are_you_coming));
        words.add(new Word("phrase5", "massokka", R.raw.phrase_are_you_coming));
        words.add(new Word("phrase6", "temmokka", R.raw.phrase_are_you_coming));
        words.add(new Word("phrase7", "kenekaku", R.raw.phrase_are_you_coming));
        words.add(new Word("phrase8", "kawinta", R.raw.phrase_are_you_coming));
        words.add(new Word("phrase9", "wo\'e", R.raw.phrase_are_you_coming));
        words.add(new Word("phrase10", "na\'aacha", R.raw.phrase_are_you_coming));

        ListView listView = findViewById(R.id.list);
        WordAdapter adapter = new WordAdapter(this, words, color);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
//                Log.d("NumbersActivity", ((Word)parent.getItemAtPosition(position)).getEnglishTranslation());
                mediaPlayer = MediaPlayer.create(PhrasesActivity.this, words.get(position).getAudioResourceId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });
    }

    private void releaseMediaPlayer()
    {
        if(mediaPlayer != null) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();

            mediaPlayer.release();
        }
    }

    public MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
}
