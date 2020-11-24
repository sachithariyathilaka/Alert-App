package com.zacseed.alertapp.utils;

import android.content.Context;
import android.media.MediaPlayer;

public class AlarmSoundSingleton {
    private static AlarmSoundSingleton instance = null;
    private MediaPlayer mediaPlayer;

    //a private constructor so no instances can be made outside this class
    private AlarmSoundSingleton() {}

    //Everytime you need an instance, call this
    //synchronized to make the call thread-safe
    public static synchronized AlarmSoundSingleton getInstance() {
        if(instance == null)
            instance = new AlarmSoundSingleton();

        return instance;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    //Initialize this or any other variables in probably the Application class
    public void init(Context context) {}
}
