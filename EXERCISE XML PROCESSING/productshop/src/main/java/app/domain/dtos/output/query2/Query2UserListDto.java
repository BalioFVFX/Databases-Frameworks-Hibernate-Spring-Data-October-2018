package app.domain.dtos.output.query2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Query2UserListDto {
    @XmlElement(name = "user")
    private List<Query2UserDto> users;

    public Query2UserListDto() {
        this.users = new ArrayList<>();
    }

    public List<Query2UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<Query2UserDto> users) {
        this.users = users;
    }
}
