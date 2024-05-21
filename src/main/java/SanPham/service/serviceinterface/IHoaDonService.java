package SanPham.service.serviceinterface;

import SanPham.dto.request.HoaDon_Request;
import SanPham.dto.response.HoaDon_Response;

public interface IHoaDonService {
    HoaDon_Response addHoaDon(HoaDon_Request request);
    HoaDon_Response updateHoaDon(int hoaDonId, HoaDon_Request request);
    void deleteHoaDon(int hoaDonId);
}
