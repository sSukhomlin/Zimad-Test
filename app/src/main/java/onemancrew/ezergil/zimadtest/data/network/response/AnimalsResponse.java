package onemancrew.ezergil.zimadtest.data.network.response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

import onemancrew.ezergil.zimadtest.data.model.AnimalModel;

public class AnimalsResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<AnimalModel> data;

    public String getMessage() {
        return message;
    }

    public List<AnimalModel> getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalsResponse that = (AnimalsResponse) o;
        return message.equals(that.message) &&
                data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, data);
    }

    @NonNull
    @Override
    public String toString() {
        return "AnimalsResponse{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}