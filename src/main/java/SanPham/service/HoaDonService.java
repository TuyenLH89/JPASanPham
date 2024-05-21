package SanPham.service;

import SanPham.dto.request.ChiTietHoaDon_Request;
import SanPham.dto.request.HoaDon_Request;
import SanPham.dto.response.ChiTietHoaDon_Response;
import SanPham.dto.response.HoaDon_Response;
import SanPham.dto.response.KhachHang_Response;
import SanPham.entity.ChiTietHoaDon;
import SanPham.entity.HoaDon;
import SanPham.entity.KhachHang;
import SanPham.entity.SanPham;
import SanPham.exception.AppException;
import SanPham.exception.ErrorCode;
import SanPham.mapper.HoaDonMapper;
import SanPham.mapper.KhachHangMapper;
import SanPham.repository.ChiTietHoaDonRepo;
import SanPham.repository.HoaDonRepo;
import SanPham.repository.KhachHangRepo;
import SanPham.repository.SanPhamRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HoaDonService {
    HoaDonMapper hoaDonMapper;
    HoaDonRepo hoaDonRepo;
    KhachHangRepo khachHangRepo;
    SanPhamRepo sanPhamRepo;
    ChiTietHoaDonRepo chiTietHoaDonRepo;
    KhachHangMapper khachHangMapper;

    public HoaDon_Response addHoaDon(HoaDon_Request request){
        KhachHang khachHang = khachHangRepo.findById(request.getKhachHang().getKhachHangId())
                .orElseThrow(()-> new AppException(ErrorCode.KHACHHANG_NOT_EXIST));
        HoaDon hoaDon = hoaDonMapper.toHoaDon(request);
        hoaDonRepo.save(hoaDon);
        List<ChiTietHoaDon_Response> value = new ArrayList<>();
        hoaDon.setChiTietHoaDons(addChiTietHD(hoaDon.getHoaDonId(), request.getChiTietHoaDons(), value));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String maGiaoDich = LocalDate.now().format(formatter);
        //kiểm tra số hóa đơn trong ngày
        List<HoaDon> list = new ArrayList<>();
        for (HoaDon hd : hoaDonRepo.findAll()){
            try {
                if (hd.getThoiGianTao().equals(LocalDate.now())){
                    list.add(hd);
                }
            }catch (Exception e){
            }
        }
        int soLuongHoaDon = list.size();
        if (soLuongHoaDon == 0){
            maGiaoDich += "001";
        }
        else if (soLuongHoaDon < 10){
            maGiaoDich += "00"+soLuongHoaDon;
        }else if (soLuongHoaDon < 100){
            maGiaoDich += "0"+soLuongHoaDon;
        }else
            maGiaoDich += soLuongHoaDon;

        hoaDon.setMaGiaoDich(maGiaoDich);
        KhachHang_Response khachHangResponse = khachHangMapper.toKhachHangResponse(khachHang);
        HoaDon_Response response = hoaDonMapper.toHoaDonResponse(hoaDonRepo.save(hoaDon));
        response.setKhachHang(khachHangResponse);
        response.setChiTietHoaDons(value);
        return response;
    }

    List<ChiTietHoaDon> addChiTietHD(int hoaDonId, List<ChiTietHoaDon_Request> requests, List<ChiTietHoaDon_Response> responses){
        HoaDon hoaDon = hoaDonRepo.findById(hoaDonId)
                .orElseThrow(()-> new AppException(ErrorCode.HOADON_NOT_EXIST));
        List<ChiTietHoaDon> result = new ArrayList<>();

        for (ChiTietHoaDon_Request ct : requests){
            SanPham sanPham = sanPhamRepo.findById(ct.getSanPhamId())
                    .orElseThrow(()-> new AppException(ErrorCode.SANPHAM_NOT_EXIST));
            ChiTietHoaDon_Response chiTietHoaDonResponse = new ChiTietHoaDon_Response();
            chiTietHoaDonResponse.setTenSanPham(sanPham.getTenSanPham());
            chiTietHoaDonResponse.setDonViTinh(ct.getDonViTinh());
            chiTietHoaDonResponse.setSoLuong(ct.getSoLuong());
            chiTietHoaDonResponse.setThanhTien(ct.getSoLuong()* sanPham.getGiaThanh());
            responses.add(chiTietHoaDonResponse);

            // tạo chi tiết hóa đơn
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setHoaDon(hoaDon);
            chiTietHoaDon.setSanPham(sanPham);
            chiTietHoaDon.setSoLuong(ct.getSoLuong());
            chiTietHoaDon.setDonViTinh(ct.getDonViTinh());
            chiTietHoaDon.setThanhTien(ct.getSoLuong()* sanPham.getGiaThanh());
            chiTietHoaDonRepo.save(chiTietHoaDon);

            // set tổng tiền cho hóa đơn
            hoaDon.setTongTien(hoaDon.getTongTien()+chiTietHoaDon.getThanhTien());
            hoaDonRepo.save(hoaDon);
            result.add(chiTietHoaDon);
        }
        return result;
    }
}
