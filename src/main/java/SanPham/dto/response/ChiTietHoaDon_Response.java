package SanPham.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTietHoaDon_Response {
    String tenSanPham;
    int soLuong;
    String donViTinh;
    int thanhTien;
}
