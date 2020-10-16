package com.example.musicplayer.Model;

public class Music {
    private String album;
    private String artist;
    private String duration;
    private String path;
    private String title;

    public Music(String album, String artist, String duration, String path, String title) {
        this.album = album;
        this.artist = artist;
        this.duration = duration;
        this.path = path;
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getDuration() {
        return duration;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }
}
