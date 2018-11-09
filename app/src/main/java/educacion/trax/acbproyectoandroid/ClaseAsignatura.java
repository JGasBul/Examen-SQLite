package educacion.trax.acbproyectoandroid;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pepeg on 09/11/2018.
 */

public class ClaseAsignatura implements Parcelable {
    private String nom;
    private int horas;

    public ClaseAsignatura() {
    }

    public ClaseAsignatura(String nom, int horas) {
        this.nom = nom;
        this.horas = horas;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    @Override
    public String toString() {
        return "ClaseAsignatura{" +
                "nom='" + nom + '\'' +
                ", horas=" + horas +
                '}';
    }

    protected ClaseAsignatura(Parcel in) {
        nom = in.readString();
        horas = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeInt(horas);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ClaseAsignatura> CREATOR = new Parcelable.Creator<ClaseAsignatura>() {
        @Override
        public ClaseAsignatura createFromParcel(Parcel in) {
            return new ClaseAsignatura(in);
        }

        @Override
        public ClaseAsignatura[] newArray(int size) {
            return new ClaseAsignatura[size];
        }
    };
}