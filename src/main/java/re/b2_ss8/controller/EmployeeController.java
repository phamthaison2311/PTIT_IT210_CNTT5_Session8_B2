package re.b2_ss8.controller;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import re.b2_ss8.model.dto.EmployeeDTO;

/**
 * Quy định của Spring MVC: Tham số BindingResult phải nằm ngay lập tức sau đối tượng được kiểm tra
 *
 * Vấn đề: Trong code của bạn, Model model đang nằm xen giữa EmployeeDto employee và BindingResult bindingResult.
 *
 * Cơ chế gây lỗi 400: Khi Spring thực hiện validate và phát hiện lỗi,
 * nó tìm kiếm một BindingResult ngay kế tiếp để lưu thông tin lỗi.
 * Do không thấy (vì gặp Model), Spring sẽ coi đây là một lỗi nghiêm trọng trong việc thiết lập tham số
 * và ném ra ngoại lệ MethodArgumentNotValidException. Kết quả là trình duyệt nhận về mã lỗi 400 Bad Request
 * và hiển thị trang trắng mặc định của server thay vì chạy vào logic if (bindingResult.hasErrors()).
 */

@Controller
@RequestMapping("/hr")
public class EmployeeController {

    // Hiển thị form thêm mới
    @GetMapping("/add-employee")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        return "employee-form";
    }

    @PostMapping("/add-employee")
    public String saveEmployee(
            @Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Vui lòng kiểm tra lại các trường thông tin!");
            return "employee-form";
        }

        return "redirect:/hr/success";
    }
    @GetMapping("/success")
    public String successPage() {
        return "success";
    }
}