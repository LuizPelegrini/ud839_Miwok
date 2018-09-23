package com.example.android.miwok;

public class Word {

    private String _englishTranslation;
    private String _miwokiTranslation;
    private int _imageResourceId = -1;

    public Word(String englishTranslation, String miwokiTranslation)
    {
        this._englishTranslation = englishTranslation;
        this._miwokiTranslation = miwokiTranslation;
    }

    public Word(String englishTranslation, String miwokiTranslation, int imageResourceId)
    {
        this(englishTranslation, miwokiTranslation);
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
}
