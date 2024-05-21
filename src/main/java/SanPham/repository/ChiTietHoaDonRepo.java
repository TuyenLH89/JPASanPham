package SanPham.repository;

import SanPham.entity.ChiTietHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietHoaDonRepo extends JpaRepository<ChiTietHoaDon, Integer> {
}
