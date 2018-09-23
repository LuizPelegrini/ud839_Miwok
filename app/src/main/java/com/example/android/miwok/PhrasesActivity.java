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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private ArrayList<Word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        int color = getResources().getColor(R.color.category_phrases);

        words = new ArrayList<>();
        words.add(new Word("phrase1", "lutti"));
        words.add(new Word("phrase2", "otiiko"));
        words.add(new Word("phrase3", "tolookosu"));
        words.add(new Word("phrase4", "oyyisa"));
        words.add(new Word("phrase5", "massokka"));
        words.add(new Word("phrase6", "temmokka"));
        words.add(new Word("phrase7", "kenekaku"));
        words.add(new Word("phrase8", "kawinta"));
        words.add(new Word("phrase9", "wo\'e"));
        words.add(new Word("phrase10", "na\'aacha"));

        ListView listView = findViewById(R.id.list);
        WordAdapter adapter = new WordAdapter(this, words, color);

        listView.setAdapter(adapter);
    }
}
