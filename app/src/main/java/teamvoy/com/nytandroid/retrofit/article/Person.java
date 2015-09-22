package teamvoy.com.nytandroid.retrofit.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class Person {
    @SerializedName("organization")
    @Expose
    public String organization;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("firstname")
    @Expose
    public String firstname;
    @SerializedName("rank")
    @Expose
    public Integer rank;
    @SerializedName("lastname")
    @Expose
    public String lastname;

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
