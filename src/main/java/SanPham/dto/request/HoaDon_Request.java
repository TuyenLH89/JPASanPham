package SanPham.dto.request;

import SanPham.entity.ChiTietHoaDon;
import SanPham.entity.KhachHang;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDon_Request {
    String tenHoaDon;
    String maGiaoDich;
    LocalDate thoiGianTao = LocalDate.now();
    LocalDate thoiGianCapNhat;
    String ghiChu;
    int tongTien;
    List<ChiTietHoaDon_Request> chiTietHoaDons;
    KhachHang khachHang;
}
