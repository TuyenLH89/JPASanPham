package SanPham.mapper;

import SanPham.dto.request.HoaDon_Request;
import SanPham.dto.response.HoaDon_Response;
import SanPham.entity.HoaDon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HoaDonMapper {
    HoaDon toHoaDon(HoaDon_Request request);
    HoaDon_Response toHoaDonResponse(HoaDon hoaDon);
}
