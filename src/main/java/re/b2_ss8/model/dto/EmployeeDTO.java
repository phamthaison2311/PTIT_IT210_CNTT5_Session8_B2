package re.b2_ss8.model.dto;

import jakarta.validation.constraints.*;

public class EmployeeDTO {
    @NotBlank(message = "Tên nhân viên không được để trống")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotNull(message = "Vui lòng nhập tuổi")
    @Min(value = 18, message = "Nhân viên phải từ 18 tuổi trở lên")
    @Max(value = 60, message = "Nhân viên không được vượt quá 60 tuổi")
    private Integer age;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String fullName, String email, Integer age) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
