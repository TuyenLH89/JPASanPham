package SanPham.service.serviceinterface;

import SanPham.dto.request.KhachHang_Request;
import SanPham.dto.response.KhachHang_Response;

public interface IKhachHangService {
    KhachHang_Response addKhachHang(KhachHang_Request request);
    KhachHang_Response updateKhachHang(int khachHangId, KhachHang_Request request);
    void deleteKhachHang(int khachHangId);
}
