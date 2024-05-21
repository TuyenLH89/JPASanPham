package SanPham.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "loaisanpham")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoaiSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loaisanphamid")
    int loaiSanPhamId;
    String tenLoaiSanPham;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiSanPham")
    @JsonManagedReference
    @JsonIgnore
    List<SanPham> sanPhams;
}
