package SanPham.repository;

import SanPham.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepo extends JpaRepository<KhachHang, Integer> {
    boolean existsByKhachHangId(int khachHangId);
}
