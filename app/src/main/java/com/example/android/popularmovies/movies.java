package com.example.android.popularmovies;

public class movies {

    private String mTitle;
    private String mImagePath;
    private int mId;
    private String mReleaseDate;
    private String mRating;
    private String mDescription;

    public movies(String Title , String ImagePath , int Id) {
        mTitle = Title;
        mImagePath = ImagePath;
        mId = Id;
    }

    public movies(String Title , String ImagePath , int Id , String ReleaseDate , String Rating , String Description){
        mTitle = Title;
        mImagePath = ImagePath;
        mId = Id;
        mReleaseDate = ReleaseDate;
        mRating = Rating;
        mDescription = Description;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public int getmId() {
        return mId;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getRating() {
        return mRating;
    }

    public String getDescription() {
        return mDescription;
    }
}
