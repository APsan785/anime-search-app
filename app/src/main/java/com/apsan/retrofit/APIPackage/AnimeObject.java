package com.apsan.retrofit.APIPackage;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnimeObject implements Parcelable {

    @SerializedName("mal_id")
    @Expose
    private Integer malId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("airing")
    @Expose
    private Boolean airing;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("episodes")
    @Expose
    private Integer episodes;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("members")
    @Expose
    private Integer members;
    @SerializedName("rated")
    @Expose
    private String rated;

    protected AnimeObject(Parcel in) {
        if (in.readByte() == 0) {
            malId = null;
        } else {
            malId = in.readInt();
        }
        url = in.readString();
        imageUrl = in.readString();
        title = in.readString();
        byte tmpAiring = in.readByte();
        airing = tmpAiring == 0 ? null : tmpAiring == 1;
        synopsis = in.readString();
        type = in.readString();
        if (in.readByte() == 0) {
            episodes = null;
        } else {
            episodes = in.readInt();
        }
        if (in.readByte() == 0) {
            score = null;
        } else {
            score = in.readDouble();
        }
        startDate = in.readString();
        endDate = in.readString();
        if (in.readByte() == 0) {
            members = null;
        } else {
            members = in.readInt();
        }
        rated = in.readString();
    }

    public static final Creator<AnimeObject> CREATOR = new Creator<AnimeObject>() {
        @Override
        public AnimeObject createFromParcel(Parcel in) {
            return new AnimeObject(in);
        }

        @Override
        public AnimeObject[] newArray(int size) {
            return new AnimeObject[size];
        }
    };

    public Integer getMalId() {
        return malId;
    }

    public void setMalId(Integer malId) {
        this.malId = malId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getAiring() {
        return airing;
    }

    public void setAiring(Boolean airing) {
        this.airing = airing;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (malId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(malId);
        }
        dest.writeString(url);
        dest.writeString(imageUrl);
        dest.writeString(title);
        dest.writeByte((byte) (airing == null ? 0 : airing ? 1 : 2));
        dest.writeString(synopsis);
        dest.writeString(type);
        if (episodes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(episodes);
        }
        if (score == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(score);
        }
        dest.writeString(startDate);
        dest.writeString(endDate);
        if (members == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(members);
        }
        dest.writeString(rated);
    }
}

