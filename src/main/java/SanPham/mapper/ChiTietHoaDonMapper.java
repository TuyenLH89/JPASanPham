package SanPham.mapper;

import SanPham.dto.response.ChiTietHoaDon_Response;
import SanPham.entity.ChiTietHoaDon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChiTietHoaDonMapper {
    ChiTietHoaDon_Response toChiTietHoaDonResponse(ChiTietHoaDon chiTietHoaDon);
}
