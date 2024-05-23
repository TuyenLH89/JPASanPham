package SanPham.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChiTietHoaDon_Response {
    int chiTietHoaDonId;
    String tenSanPham;
    int soLuong;
    String donViTinh;
    int thanhTien;
}
