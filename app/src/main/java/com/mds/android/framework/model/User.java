package com.mds.android.framework.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vivekjha on 26/08/16.
 * User model is sample model , make two constructors if necessary one is default another one setting values (when required)
 * {@link Cloneable} is used to make a field-for-field copy of instances of the class.
 */
public class User extends BaseModel implements Cloneable, Parcelable{

    private String name;
    private int userId;

    public User(){

    }

    public User(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    protected User(Parcel in) {
        name = in.readString();
        userId = in.readInt();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getCopied() {
        try {
            return (User) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof User)) return  false;
        User other = (User) o;
        if (getUserId() == 0 && other.getUserId() == 0) return  this == o;
        return getUserId() == other.getUserId();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        User cloneUser = (User) super.clone();
        return cloneUser;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.userId);

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
