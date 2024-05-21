package SanPham.mapper;

import SanPham.dto.request.LoaiSanPham_Request;
import SanPham.dto.response.LoaiSanPham_Response;
import SanPham.entity.LoaiSanPham;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LoaiSanPhamMapper {
    LoaiSanPham toLoaiSanPham(LoaiSanPham_Request request);
    LoaiSanPham_Response toLoaiSanPhamResponse(LoaiSanPham loaiSanPham);
    void updateLoaiSanPham(@MappingTarget LoaiSanPham loaiSanPham, LoaiSanPham_Request request);
}
