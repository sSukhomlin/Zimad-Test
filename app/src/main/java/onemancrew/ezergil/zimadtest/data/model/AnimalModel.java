package onemancrew.ezergil.zimadtest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class AnimalModel implements Parcelable {

    @SerializedName("url")
    private String url;
    @SerializedName("title")
    private String title;

    protected AnimalModel(Parcel in) {
        url = in.readString();
        title = in.readString();
    }

    public static final Creator<AnimalModel> CREATOR = new Creator<AnimalModel>() {
        @Override
        public AnimalModel createFromParcel(Parcel in) {
            return new AnimalModel(in);
        }

        @Override
        public AnimalModel[] newArray(int size) {
            return new AnimalModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(title);
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalModel that = (AnimalModel) o;
        return url.equals(that.url) &&
                title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, title);
    }

    @NonNull
    @Override
    public String toString() {
        return "AnimalModel{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
