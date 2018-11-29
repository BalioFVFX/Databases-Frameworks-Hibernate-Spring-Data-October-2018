package app.domain.dtos.seeders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UserSeederListDto {
    @XmlElement(name = "user")
    private List<UserSeederDto> users;

    public List<UserSeederDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserSeederDto> users) {
        this.users = users;
    }
}
