package SanPham.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDon_Update_Request {
    int hoaDonId;
    int chiTietHoaDonId;
    int soLuong;
}
