package SanPham.service.serviceinterface;

import SanPham.dto.request.SanPham_Request;
import SanPham.dto.response.SanPham_Response;

public interface ISanPhamService {
    SanPham_Response addSanPham(SanPham_Request request);
    SanPham_Response updateSanPham(int sanPhamId, SanPham_Request request);
    void deleteSanPham(int sanPhamId);

}
