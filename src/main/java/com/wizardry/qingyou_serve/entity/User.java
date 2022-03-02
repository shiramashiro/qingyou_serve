package com.wizardry.qingyou_serve.entity;
import java.io.Serializable;
import java.util.Date;
import lombock.Data
import lombock.NoArgsConstructor
import lombock.AllArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	// 共有私有不影响吗？我原先这里写的private
	public int id;
    public String psw;
    public String uname;
    public String phone;
    public String email;
    public Date createdDate;
    public Date birthday;
    public String signature;
    public String sex;
    public String location;
    public String constellation;
    public int age;
    public String avatar;
    public String occupation;
    public String salt;
    
    //equals和hashcod重写
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (getAge() != user.getAge()) return false;
        if (getPsw() != null ? !getPsw().equals(user.getPsw()) : user.getPsw() != null) return false;
        if (getUname() != null ? !getUname().equals(user.getUname()) : user.getUname() != null) return false;
        if (getPhone() != null ? !getPhone().equals(user.getPhone()) : user.getPhone() != null) return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getCreatedDate() != null ? !getCreatedDate().equals(user.getCreatedDate()) : user.getCreatedDate() != null)
            return false;
        if (getBirthday() != null ? !getBirthday().equals(user.getBirthday()) : user.getBirthday() != null)
            return false;
        if (getSignature() != null ? !getSignature().equals(user.getSignature()) : user.getSignature() != null)
            return false;
        if (getSex() != null ? !getSex().equals(user.getSex()) : user.getSex() != null) return false;
        if (getLocation() != null ? !getLocation().equals(user.getLocation()) : user.getLocation() != null)
            return false;
        if (getConstellation() != null ? !getConstellation().equals(user.getConstellation()) : user.getConstellation() != null)
            return false;
        if (getAvatar() != null ? !getAvatar().equals(user.getAvatar()) : user.getAvatar() != null) return false;
        if (getOccupation() != null ? !getOccupation().equals(user.getOccupation()) : user.getOccupation() != null)
            return false;
        return getSalt() != null ? getSalt().equals(user.getSalt()) : user.getSalt() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getPsw() != null ? getPsw().hashCode() : 0);
        result = 31 * result + (getUname() != null ? getUname().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getCreatedDate() != null ? getCreatedDate().hashCode() : 0);
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        result = 31 * result + (getSignature() != null ? getSignature().hashCode() : 0);
        result = 31 * result + (getSex() != null ? getSex().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        result = 31 * result + (getConstellation() != null ? getConstellation().hashCode() : 0);
        result = 31 * result + getAge();
        result = 31 * result + (getAvatar() != null ? getAvatar().hashCode() : 0);
        result = 31 * result + (getOccupation() != null ? getOccupation().hashCode() : 0);
        result = 31 * result + (getSalt() != null ? getSalt().hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", psw='" + psw + '\'' +
                ", uname='" + uname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", createdDate=" + createdDate +
                ", birthday=" + birthday +
                ", signature='" + signature + '\'' +
                ", sex='" + sex + '\'' +
                ", location='" + location + '\'' +
                ", constellation='" + constellation + '\'' +
                ", age=" + age +
                ", avatar='" + avatar + '\'' +
                ", occupation='" + occupation + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
    
    
    
    
    
    
}
