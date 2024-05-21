package SanPham.repository;

import SanPham.entity.LoaiSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiSanPhamRepo extends JpaRepository<LoaiSanPham, Integer> {
    boolean existsLoaiSanPhamByTenLoaiSanPham(String tenLoaiSanPham);
}
