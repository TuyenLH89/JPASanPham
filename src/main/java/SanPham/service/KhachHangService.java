package SanPham.service;

import SanPham.dto.request.KhachHang_Request;
import SanPham.dto.response.KhachHang_Response;
import SanPham.entity.KhachHang;
import SanPham.exception.AppException;
import SanPham.exception.ErrorCode;
import SanPham.mapper.KhachHangMapper;
import SanPham.repository.KhachHangRepo;
import SanPham.service.serviceinterface.IKhachHangService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class KhachHangService implements IKhachHangService {
    KhachHangMapper khachHangMapper;
    KhachHangRepo khachHangRepo;


    @Override
    public KhachHang_Response addKhachHang(KhachHang_Request request) {
        if (!LocalDate.now().isAfter(request.getNgaySinh()))
            throw new AppException(ErrorCode.NGAYSINH_INVALID);
        KhachHang khachHang = khachHangMapper.toKhachHang(request);
        return khachHangMapper.toKhachHangResponse(khachHangRepo.save(khachHang));
    }

    @Override
    public KhachHang_Response updateKhachHang(int khachHangId, KhachHang_Request request) {
        KhachHang khachHang = khachHangRepo.findById(khachHangId)
                .orElseThrow(()-> new AppException(ErrorCode.KHACHHANG_NOT_EXIST));
        khachHangMapper.updateKhachHang(khachHang, request);
        return khachHangMapper.toKhachHangResponse(khachHangRepo.save(khachHang));
    }

    @Override
    public void deleteKhachHang(int khachHangId) {

    }
}
