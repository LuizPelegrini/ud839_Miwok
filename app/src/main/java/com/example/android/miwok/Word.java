package com.example.android.miwok;

public class Word {

    private String _englishTranslation;
    private String _miwokiTranslation;

    public Word(String englishTranslation, String miwokiTranslation)
    {
        this._englishTranslation = englishTranslation;
        this._miwokiTranslation = miwokiTranslation;
    }

    public String getEnglishTranslation() {
        return this._englishTranslation;
    }

    public String getMiwokiTranslation() {
        return this._miwokiTranslation;
    }

    public void setEnglishTranslation(String englishTranslation) {
        this._englishTranslation = englishTranslation;
    }

    public void setMiwokiTranslation(String miwokiTranslation) {
        this._miwokiTranslation = miwokiTranslation;
    }
}
