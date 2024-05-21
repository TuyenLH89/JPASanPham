package SanPham.service;

import SanPham.dto.request.LoaiSanPham_Request;
import SanPham.dto.response.LoaiSanPham_Response;
import SanPham.entity.LoaiSanPham;
import SanPham.exception.AppException;
import SanPham.exception.ErrorCode;
import SanPham.mapper.LoaiSanPhamMapper;
import SanPham.repository.LoaiSanPhamRepo;
import SanPham.service.serviceinterface.ILoaiSanPhamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LoaiSanPhamService implements ILoaiSanPhamService {
    LoaiSanPhamRepo loaiSanPhamRepo;
    LoaiSanPhamMapper loaiSanPhamMapper;
    @Override
    public LoaiSanPham_Response addSanPham(LoaiSanPham_Request request) {
        if (loaiSanPhamRepo.existsLoaiSanPhamByTenLoaiSanPham(request.getTenLoaiSanPham()))
            throw new AppException(ErrorCode.LOAISANPHAM_EXIST);
        LoaiSanPham loaiSanPham = loaiSanPhamMapper.toLoaiSanPham(request);
        return loaiSanPhamMapper.toLoaiSanPhamResponse(loaiSanPhamRepo.save(loaiSanPham));
    }

    @Override
    public LoaiSanPham_Response updateLoaiSanPham(int loaiSanPhamId, LoaiSanPham_Request request) {
        return null;
    }

    @Override
    public void deleteLoaiSanPham(int loaiSanPhamId) {

    }
}
