package SanPham.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sanpham")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sanphamid")
    int sanPhamId;
    String tenSanPham;
    int giaThanh;
    String moTa;
    LocalDate ngayHetHan;
    String kyHieuSanPham;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loaiSanPhamId")
    @JsonBackReference
    @JsonIgnore
    LoaiSanPham loaiSanPham;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sanPham")
    @JsonManagedReference
    @JsonIgnore
    List<ChiTietHoaDon> chiTietHoaDons;
}
