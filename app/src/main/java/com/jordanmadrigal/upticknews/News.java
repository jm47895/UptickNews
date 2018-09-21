package com.jordanmadrigal.upticknews;

/**
 * News card class to initialize data from json
 */

public class News {

    private static final String LOG_TAG = News.class.getName();

    private String imageSource;
    private String headline;
    private String newsSource;
    private String date;
    private String learnMore;
    private String url;


    /**
     *
     * @param imageSource is the uri for the headline pic associated with news article
     * @param headline is the headline of news article
     * @param newsSource is the source of news article
     * @param date is the date the news article was published
     * @param learnMore is the textview that will be a clickable
     * @param url is the JSON url provided by the Google new API
     */

    public News (String imageSource, String headline, String newsSource, String date, String learnMore, String url) {
        this.imageSource = imageSource;
        this.headline = headline;
        this.newsSource = newsSource;
        this.date = date;
        this.learnMore = learnMore;
        this.url = url;
    }

    public String getImageSource() {
        return imageSource;
    }

    public String getHeadline() {
        return headline;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public String getDate() {
        return date;
    }

    public String getLearnMore() {return learnMore;}

    public String getUrl() { return url; }
}
