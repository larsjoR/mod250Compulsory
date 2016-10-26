package no.hib.mod250.anthrax.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by royne on 24.10.2016.
 */
@Entity
public class UserGroup {
    @Id
    private String groupId;

    @ManyToMany(mappedBy = "userGroups")
    private List<User> users;

    public UserGroup() {
    }



    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
