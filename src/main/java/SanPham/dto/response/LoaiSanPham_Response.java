package SanPham.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoaiSanPham_Response {
    int loaiSanPhamId;
    String tenLoaiSanPham;
}
