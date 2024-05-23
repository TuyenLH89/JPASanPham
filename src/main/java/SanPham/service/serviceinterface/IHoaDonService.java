package SanPham.service.serviceinterface;

import SanPham.dto.request.HoaDon_Request;
import SanPham.dto.request.HoaDon_Update_Request;
import SanPham.dto.response.HoaDon_Response;

import java.util.List;

public interface IHoaDonService {
    HoaDon_Response addHoaDon(HoaDon_Request request);
    HoaDon_Response updateHoaDon(HoaDon_Update_Request request);
    void deleteHoaDon(int hoaDonId);
    List<HoaDon_Response> getAllHoaDon();
}
