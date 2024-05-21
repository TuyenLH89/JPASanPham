package SanPham.dto.request;

import SanPham.entity.ChiTietHoaDon;
import SanPham.entity.LoaiSanPham;
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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPham_Request {
    String tenSanPham;
    int giaThanh;
    String moTa;
    LocalDate ngayHetHan;
    String kyHieuSanPham;
    LoaiSanPham loaiSanPham;
}
