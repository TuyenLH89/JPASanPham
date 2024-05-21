package SanPham.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KhachHang_Response {
    int khachHangId;
    String hoTen;
    LocalDate ngaySinh;
    String soDienThoai;
}
