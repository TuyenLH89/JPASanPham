package SanPham.service.serviceinterface;

import SanPham.dto.request.LoaiSanPham_Request;
import SanPham.dto.response.LoaiSanPham_Response;

public interface ILoaiSanPhamService {
    LoaiSanPham_Response addSanPham(LoaiSanPham_Request request);
    LoaiSanPham_Response updateLoaiSanPham(int loaiSanPhamId, LoaiSanPham_Request request);
    void deleteLoaiSanPham(int loaiSanPhamId);
}
