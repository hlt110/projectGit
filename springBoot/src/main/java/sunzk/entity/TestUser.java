package sunzk.entity;

import java.util.Optional;

/**
 * Created by 82681 on 2017/12/28.
 */
public class TestUser {
    private Optional<Employee> emp = Optional.empty();
    // 用户id
    private java.lang.String userid;
    private Integer age;
    // 用户名称
    private java.lang.String username;
    // 密码
    private java.lang.String password;
    // 旧密码
    private java.lang.String oldPassword;
    // 状态，：停用，1启用
    private java.lang.Integer status;
    // 手机
    private String phone;

    @Override
    public String toString() {
        return "TestUser{" +
                "emp=" + emp +
                ", userid='" + userid + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Optional<Employee> getEmp() {
        return emp;
    }

    public void setEmp(Optional<Employee> emp) {
        this.emp = emp;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
