package app.dto;

import java.util.List;

public class Query4AbstractDto {
    private Integer userCount;
    private List<Query4Dto> users;

    public Query4AbstractDto(Integer userCount, List<Query4Dto> users) {
        this.userCount = userCount;
        this.users = users;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public List<Query4Dto> getUsers() {
        return users;
    }

    public void setUsers(List<Query4Dto> users) {
        this.users = users;
    }
}
