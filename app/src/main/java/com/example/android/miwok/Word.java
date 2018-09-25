package com.example.android.miwok;

public class Word {

    private String _englishTranslation;
    private String _miwokiTranslation;
    private int _imageResourceId = -1;
    private int _audioResourceId;

    public Word(String englishTranslation, String miwokiTranslation, int audioResourceId)
    {
        this._englishTranslation = englishTranslation;
        this._miwokiTranslation = miwokiTranslation;
        this._audioResourceId = audioResourceId;
    }

    public Word(String englishTranslation, String miwokiTranslation, int imageResourceId, int audioResourceId)
    {
        this(englishTranslation, miwokiTranslation, audioResourceId);
        this._imageResourceId = imageResourceId;
    }


    //// GETTERS ////
    public String getEnglishTranslation() {
        return this._englishTranslation;
    }

    public String getMiwokiTranslation() {
        return this._miwokiTranslation;
    }

    public int getImageResourceId(){ return this._imageResourceId; }

    public int getAudioResourceId() { return this._audioResourceId; }

    @Override
    public String toString() {
        return "Word{" +
                "_englishTranslation='" + _englishTranslation + '\'' +
                ", _miwokiTranslation='" + _miwokiTranslation + '\'' +
                ", _imageResourceId=" + _imageResourceId +
                ", _audioResourceId=" + _audioResourceId +
                '}';
    }
}
