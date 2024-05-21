package SanPham.mapper;

import SanPham.dto.request.KhachHang_Request;
import SanPham.dto.response.KhachHang_Response;
import SanPham.entity.KhachHang;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KhachHangMapper {
    KhachHang toKhachHang(KhachHang_Request request);
    KhachHang_Response toKhachHangResponse(KhachHang khachHang);
    void updateKhachHang(@MappingTarget KhachHang khachHang, KhachHang_Request request);
}
