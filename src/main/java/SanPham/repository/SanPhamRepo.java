package SanPham.repository;

import SanPham.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepo extends JpaRepository<SanPham, Integer> {
    boolean existsByTenSanPham(String tenSanPham);
}
