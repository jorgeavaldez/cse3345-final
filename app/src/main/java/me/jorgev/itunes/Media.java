package me.jorgev.itunes;

import hugo.weaving.DebugLog;

/**
 * Created by jorgeavaldez on 12/15/15.
 */


public class Media {
    private String wrapperType;
    private String explicitness;
    private String kind;
    private String trackName;
    private String artistName;
    private String collectionName;
    private String censoredName;
    private String viewURL;
    private String previewUrl;
    private String trackTimeMillis;
    private String trackPrice;
    private String ratingIndex;
    private String primaryGenreName;
    private String artworkUrl100;

    /**
     * No args constructor for use in serialization
     */
    public Media() {
    }

    /**
     * @param censoredName
     * @param viewURL
     * @param explicitness
     * @param trackTimeMillis
     * @param wrapperType
     * @param trackName
     * @param artistName
     * @param collectionName
     * @param kind
     * @param previewUrl
     * @param ratingIndex
     * @param primaryGenreName
     * @param trackPrice
     * @param artworkUrl100
     */
    @DebugLog
    public Media(String wrapperType, String explicitness, String kind, String trackName,
                 String artistName, String collectionName, String censoredName, String viewURL,
                 String previewUrl, String trackTimeMillis, String trackPrice, String ratingIndex,
                 String primaryGenreName, String artworkUrl100) {
        this.wrapperType = wrapperType;
        this.explicitness = explicitness;
        this.kind = kind;
        this.trackName = trackName;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.censoredName = censoredName;
        this.viewURL = viewURL;
        this.previewUrl = previewUrl;
        this.trackTimeMillis = trackTimeMillis;
        this.trackPrice = trackPrice;
        this.ratingIndex = ratingIndex;
        this.primaryGenreName = primaryGenreName;
        this.artworkUrl100 = artworkUrl100;
    }

    /**
     * @return The wrapperType
     */
    public String getWrapperType() {
        return wrapperType;
    }

    /**
     * @param wrapperType The wrapperType
     */
    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    /**
     * @return The explicitness
     */
    public String getExplicitness() {
        return explicitness;
    }

    /**
     * @param explicitness The explicitness
     */
    public void setExplicitness(String explicitness) {
        this.explicitness = explicitness;
    }

    /**
     * @return The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * @param kind The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * @return The trackName
     */
    public String getTrackName() {
        return trackName;
    }

    /**
     * @param trackName The trackName
     */
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    /**
     * @return The artistName
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * @param artistName The artistName
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * @return The collectionName
     */
    public String getCollectionName() {
        return collectionName;
    }

    /**
     * @param collectionName The collectionName
     */
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * @return The censoredName
     */
    public String getCensoredName() {
        return censoredName;
    }

    /**
     * @param censoredName The censoredName
     */
    public void setCensoredName(String censoredName) {
        this.censoredName = censoredName;
    }

    /**
     * @return The viewURL
     */
    public String getViewURL() {
        return viewURL;
    }

    /**
     * @param viewURL The viewURL
     */
    public void setViewURL(String viewURL) {
        this.viewURL = viewURL;
    }

    /**
     * @return The previewUrl
     */
    public String getPreviewUrl() {
        return previewUrl;
    }

    /**
     * @param previewUrl The previewUrl
     */
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    /**
     * @return The trackTimeMillis
     */
    public String getTrackTimeMillis() {
        return trackTimeMillis;
    }

    /**
     * @param trackTimeMillis The trackTimeMillis
     */
    public void setTrackTimeMillis(String trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public String getTrackPrice() {
        return this.trackPrice;
    }

    public void setTrackPrice(String trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getRatingIndex() {
        return this.ratingIndex;
    }

    public void setRatingIndex(String ratingIndex) {
        this.ratingIndex = ratingIndex;
    }

    public String getPrimaryGenreName() {
        return this.primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getArtworkUrl100() {
        return this.artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }
}