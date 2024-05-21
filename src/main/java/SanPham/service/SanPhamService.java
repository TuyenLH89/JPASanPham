package SanPham.service;

import SanPham.dto.request.SanPham_Request;
import SanPham.dto.response.SanPham_Response;
import SanPham.entity.SanPham;
import SanPham.exception.AppException;
import SanPham.exception.ErrorCode;
import SanPham.mapper.SanPhamMapper;
import SanPham.repository.SanPhamRepo;
import SanPham.service.serviceinterface.ISanPhamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SanPhamService implements ISanPhamService {
    SanPhamMapper sanPhamMapper;
    SanPhamRepo sanPhamRepo;

    @Override
    public SanPham_Response addSanPham(SanPham_Request request) {
        if (sanPhamRepo.existsByTenSanPham(request.getTenSanPham()))
            throw new AppException(ErrorCode.SANPHAM_EXIST);
        SanPham sanPham = sanPhamMapper.toSanPham(request);
        return sanPhamMapper.toSanPhamResponse(sanPhamRepo.save(sanPham));
    }

    @Override
    public SanPham_Response updateSanPham(int sanPhamId, SanPham_Request request) {
        return null;
    }

    @Override
    public void deleteSanPham(int sanPhamId) {

    }
}
