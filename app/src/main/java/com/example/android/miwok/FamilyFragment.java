package com.example.android.miwok;


import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

    private ArrayList<Word> words;
    private MediaPlayer mediaPlayer;
    private AudioFocusRequest audioFocusRequest;
    private AudioManager audioManager;


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final FragmentActivity activity = getActivity();

        if(activity != null)
            audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);

        createAudioFocusRequestObject();

        int color = getResources().getColor(R.color.category_family);

        words = new ArrayList<>();
        words.add(new Word("father", "ahsjahs", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "sdsds", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "sdsdsd", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "dfsdfs", R.drawable.family_daughter, R.raw.family_daughter));

        ListView listView = rootView.findViewById(R.id.list);
        WordAdapter adapter = new WordAdapter(activity, words, color);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
//                Log.d("NumbersActivity", ((Word)parent.getItemAtPosition(position)).getEnglishTranslation());


                // Request focus (differently depending on the API level)
                int res;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                    res = audioManager.requestAudioFocus(audioFocusRequest);
                else
                    res = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(activity, words.get(position).getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });


        return rootView;
    }

    private void releaseMediaPlayer()
    {
        if(mediaPlayer != null) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();

            mediaPlayer.release();

            mediaPlayer = null;

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                audioManager.abandonAudioFocusRequest(audioFocusRequest);
            else
                audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    public MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch(focusChange)
            {
                // if the
                case AudioManager.AUDIOFOCUS_GAIN:
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mediaPlayer.pause();
                    break;

            }
        }
    };


    private void createAudioFocusRequestObject()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AudioAttributes audioAttributes;

            // Build audio attributes to later provide to the audioFocusRequest object
            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build();

            // Build audioFocusRequest to later provide to the requestAudioFocus method of the AudioManager
            audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                    .setAudioAttributes(audioAttributes)
                    .setAcceptsDelayedFocusGain(true)
                    .setOnAudioFocusChangeListener(onAudioFocusChangeListener)
                    .build();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


}
