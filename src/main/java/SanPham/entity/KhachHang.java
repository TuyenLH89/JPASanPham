package SanPham.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "khachhang")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "khachhangid")
    int khachHangId;
    String hoTen;
    LocalDate ngaySinh;
    String soDienThoai;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "khachHang")
    @JsonManagedReference
    @JsonIgnore
    List<HoaDon> hoaDons;
}
