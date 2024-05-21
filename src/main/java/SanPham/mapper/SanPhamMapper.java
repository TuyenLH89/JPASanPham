package SanPham.mapper;

import SanPham.dto.request.SanPham_Request;
import SanPham.dto.response.SanPham_Response;
import SanPham.entity.SanPham;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SanPhamMapper {
    SanPham toSanPham(SanPham_Request request);
    SanPham_Response toSanPhamResponse(SanPham sanPham);
}
