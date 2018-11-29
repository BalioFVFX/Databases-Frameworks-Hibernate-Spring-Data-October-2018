package app.domain.dtos.output.query4;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UsersAndProductsUserListDto {
    @XmlAttribute(name = "count")
    private Integer useresCount;
    @XmlElement(name = "user")
    private List<UsersAndProductsUserDto> users;

    public UsersAndProductsUserListDto() {
        this.users = new ArrayList<>();
    }

    public Integer getUseresCount() {
        return useresCount;
    }

    public void setUseresCount(Integer useresCount) {
        this.useresCount = useresCount;
    }

    public List<UsersAndProductsUserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UsersAndProductsUserDto> users) {
        this.users = users;
    }
}
