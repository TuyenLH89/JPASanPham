package SanPham.dto.request;

import SanPham.entity.HoaDon;
import SanPham.entity.SanPham;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTietHoaDon_Request {
    int sanPhamId;
    int soLuong;
    String donViTinh;
    int thanhTien;
}
