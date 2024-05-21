package SanPham.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "hoadon")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hoadonid")
    int hoaDonId;
    String tenHoaDon;
    String maGiaoDich;
    LocalDate thoiGianTao;
    LocalDate thoiGianCapNhat;
    String ghiChu;
    int tongTien;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hoaDon")
    @JsonManagedReference
    @JsonIgnore
    List<ChiTietHoaDon> chiTietHoaDons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khachHangId")
    @JsonBackReference
    @JsonIgnore
    KhachHang khachHang;
}
