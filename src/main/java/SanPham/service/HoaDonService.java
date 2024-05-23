package SanPham.service;

import SanPham.dto.request.ChiTietHoaDon_Request;
import SanPham.dto.request.HoaDon_Request;
import SanPham.dto.request.HoaDon_Update_Request;
import SanPham.dto.response.ChiTietHoaDon_Response;
import SanPham.dto.response.HoaDon_Response;
import SanPham.dto.response.KhachHang_Response;
import SanPham.entity.ChiTietHoaDon;
import SanPham.entity.HoaDon;
import SanPham.entity.KhachHang;
import SanPham.entity.SanPham;
import SanPham.exception.AppException;
import SanPham.exception.ErrorCode;
import SanPham.mapper.ChiTietHoaDonMapper;
import SanPham.mapper.HoaDonMapper;
import SanPham.mapper.KhachHangMapper;
import SanPham.repository.ChiTietHoaDonRepo;
import SanPham.repository.HoaDonRepo;
import SanPham.repository.KhachHangRepo;
import SanPham.repository.SanPhamRepo;
import SanPham.service.serviceinterface.IHoaDonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HoaDonService implements IHoaDonService {
    HoaDonMapper hoaDonMapper;
    HoaDonRepo hoaDonRepo;
    KhachHangRepo khachHangRepo;
    SanPhamRepo sanPhamRepo;
    ChiTietHoaDonRepo chiTietHoaDonRepo;
    KhachHangMapper khachHangMapper;
    ChiTietHoaDonMapper chiTietHoaDonMapper;

    List<ChiTietHoaDon> addChiTietHD(int hoaDonId, List<ChiTietHoaDon_Request> requests, List<ChiTietHoaDon_Response> responses){
        HoaDon hoaDon = hoaDonRepo.findById(hoaDonId)
                .orElseThrow(()-> new AppException(ErrorCode.HOADON_NOT_EXIST));
        List<ChiTietHoaDon> result = new ArrayList<>();

        for (ChiTietHoaDon_Request ct : requests){
            SanPham sanPham = sanPhamRepo.findById(ct.getSanPhamId())
                    .orElseThrow(()-> new AppException(ErrorCode.SANPHAM_NOT_EXIST));

            // tạo chi tiết hóa đơn
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setHoaDon(hoaDon);
            chiTietHoaDon.setSanPham(sanPham);
            chiTietHoaDon.setSoLuong(ct.getSoLuong());
            chiTietHoaDon.setDonViTinh(ct.getDonViTinh());
            chiTietHoaDon.setThanhTien(ct.getSoLuong()* sanPham.getGiaThanh());
            chiTietHoaDonRepo.save(chiTietHoaDon);

            //tạo dữ liệu trả về
            ChiTietHoaDon_Response chiTietHoaDon_response = chiTietHoaDonMapper.toChiTietHoaDonResponse(chiTietHoaDon);
            responses.add(chiTietHoaDon_response);

            // set tổng tiền cho hóa đơn
            hoaDon.setTongTien(hoaDon.getTongTien()+chiTietHoaDon.getThanhTien());
            hoaDonRepo.save(hoaDon);
            result.add(chiTietHoaDon);
        }
        return result;
    }

    @Override
    public HoaDon_Response addHoaDon(HoaDon_Request request) {
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

    @Override
    public HoaDon_Response updateHoaDon(HoaDon_Update_Request request) {
        HoaDon hoaDon = hoaDonRepo.findById(request.getHoaDonId())
                .orElseThrow(()-> new AppException(ErrorCode.HOADON_NOT_EXIST));

        KhachHang khachHang = khachHangRepo.findById(hoaDon.getKhachHang().getKhachHangId())
                .orElseThrow(()-> new AppException(ErrorCode.KHACHHANG_NOT_EXIST));

        KhachHang_Response khachHang_response = khachHangMapper.toKhachHangResponse(khachHang);

        int check = hoaDon.getChiTietHoaDons().stream().filter(x -> x.getChiTietHoaDonId() == request.getChiTietHoaDonId()).toList().size();
        List<ChiTietHoaDon_Response> list = new ArrayList<>();
        if (check == 1){
            for (ChiTietHoaDon ct: chiTietHoaDonRepo.findAll()){
                if (ct.getChiTietHoaDonId() == request.getChiTietHoaDonId()){
                    hoaDon.setTongTien(hoaDon.getTongTien() - ct.getThanhTien());
                    ct.setSoLuong(request.getSoLuong());
                    ct.setThanhTien(ct.getSanPham().getGiaThanh()* request.getSoLuong());
                    chiTietHoaDonRepo.save(ct);

                    ChiTietHoaDon_Response chiTietHoaDon_response = chiTietHoaDonMapper.toChiTietHoaDonResponse(ct);
                    chiTietHoaDon_response.setTenSanPham(ct.getSanPham().getTenSanPham());
                    list.add(chiTietHoaDon_response);


                    hoaDon.setTongTien(hoaDon.getTongTien() + ct.getThanhTien());
                    hoaDon.setThoiGianCapNhat(LocalDate.now());
                    hoaDonRepo.save(hoaDon);
                }else if (ct.getHoaDon().getHoaDonId() == hoaDon.getHoaDonId()){
                    ChiTietHoaDon_Response chiTietHoaDon_response = chiTietHoaDonMapper.toChiTietHoaDonResponse(ct);
                    chiTietHoaDon_response.setTenSanPham(ct.getSanPham().getTenSanPham());
                    list.add(chiTietHoaDon_response);
                }
            }

        }else
            throw new AppException(ErrorCode.CTHD_NOT_EXIST_IN_HOADON);
        HoaDon_Response hoaDon_response = hoaDonMapper.toHoaDonResponse(hoaDon);
        hoaDon_response.setKhachHang(khachHang_response);
        hoaDon_response.setChiTietHoaDons(list);
        return hoaDon_response;
    }

    @Override
    public void deleteHoaDon(int hoaDonId) {
        for (ChiTietHoaDon ct: chiTietHoaDonRepo.findAll()){
            try {
                if (ct.getHoaDon().getHoaDonId() == hoaDonId){
                    chiTietHoaDonRepo.delete(ct);
                }
            }catch (Exception e){
            }
        }
        hoaDonRepo.deleteById(hoaDonId);
    }

    @Override
    public List<HoaDon_Response> getAllHoaDon() {
        List<HoaDon_Response> result = new ArrayList<>();

        for (HoaDon hd : hoaDonRepo.findAll()){
            HoaDon_Response hoaDon_response = hoaDonMapper.toHoaDonResponse(hd);
            KhachHang khachHang = khachHangRepo.findById(hd.getKhachHang().getKhachHangId())
                    .orElseThrow(()-> new AppException(ErrorCode.KHACHHANG_NOT_EXIST));
            //gọi ra khách hàng
            KhachHang_Response khachHang_response = khachHangMapper.toKhachHangResponse(khachHang);
            hoaDon_response.setKhachHang(khachHang_response);

            //gọi ra list hóa đơn
            List<ChiTietHoaDon_Response> chiTietHoaDon_responses = getChiTietHoaDonResponses(hd);
            hoaDon_response.setChiTietHoaDons(chiTietHoaDon_responses);
            result.add(hoaDon_response);
        }
        return result;
    }
    public List<?> phanTrang(List<?> list, int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        int start = (int) pageable.getOffset();
        int end = (start+pageable.getPageSize())  > list.size() ? list.size(): (int) (start + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<>(list, pageable, list.size()).getContent();
    }
    public List<HoaDon_Response> getAllHoaDonOrderByThoiGianTao(){
        List<HoaDon_Response> result = new ArrayList<>();
        for (HoaDon hd: hoaDonRepo.findAll()){
            toListHoaDonResponse(result, hd);
        }
        result.sort(new Comparator<HoaDon_Response>() {
            @Override
            public int compare(HoaDon_Response o1, HoaDon_Response o2) {
                return o2.getThoiGianTao().compareTo(o1.getThoiGianTao());
            }
        });
        return result;
    }

    //get hóa đơn theo năm và tháng
    public List<HoaDon_Response> getHoaDonByYearAndMonth(int year, int month){
        List<HoaDon_Response> result = new ArrayList<>();
        for (HoaDon hd: hoaDonRepo.findAll()){
            try {
                int yearHD = hd.getThoiGianTao().getYear();
                int monthHD = hd.getThoiGianTao().getMonthValue();
                if (yearHD == year && monthHD == month){
                    toListHoaDonResponse(result, hd);
                }
            }catch (Exception e){

            }
        }
        return result;
    }
    //tìm kiếm hóa đơn từ ngày a đến ngày b
    public List<HoaDon_Response> searchHoaDonByTime(LocalDate date1, LocalDate date2){
        List<HoaDon_Response> result = new ArrayList<>();
        if (date1.isAfter(date2)){
            LocalDate temp = date1;
            date1 = date2;
            date2 = temp;
        }
        for (HoaDon hoaDon: hoaDonRepo.findAll()){
            try {
                if (!hoaDon.getThoiGianTao().isBefore(date1) && !hoaDon.getThoiGianTao().isAfter(date2)){
                    toListHoaDonResponse(result, hoaDon);
                }
            }catch (Exception e){

            }
        }
        result.sort(new Comparator<HoaDon_Response>() {
            @Override
            public int compare(HoaDon_Response o1, HoaDon_Response o2) {
                return o1.getThoiGianTao().compareTo(o2.getThoiGianTao());
            }
        });
        return result;
    }

    //lấy hóa đơn theo tổng tiền từ a đến b
    public List<HoaDon_Response> getHoaDonByTongTien(int tongTien1, int tongTien2){
        List<HoaDon_Response> result = new ArrayList<>();
        if (tongTien1 > tongTien2){
            int temp = tongTien1;
            tongTien1 = tongTien2;
            tongTien2 = temp;
        }
        for (HoaDon hoaDon: hoaDonRepo.findAll()){
            try {
                if (hoaDon.getTongTien() >= tongTien1 && hoaDon.getTongTien() <= tongTien2){
                    toListHoaDonResponse(result, hoaDon);
                }
            }catch (Exception e){

            }
        }
        result.sort(new Comparator<HoaDon_Response>() {
            @Override
            public int compare(HoaDon_Response o1, HoaDon_Response o2) {
                return Integer.compare(o2.getTongTien(), o1.getTongTien());
            }
        });
        return result;
    }
    //tìm kiếm hóa đơn theo mã giao dịch hoặc tên hóa đơn
    public HoaDon_Response searchHoaDonByMaGiaoDich(String maGiaoDich){
        for (HoaDon hoaDon: hoaDonRepo.findAll()){
            try {
                if (hoaDon.getMaGiaoDich().equals(maGiaoDich)){
                    return hoaDonMapper.toHoaDonResponse(hoaDon);
                }
            }catch (Exception e){

            }
        }
        return null;
    }
    private void toListHoaDonResponse(List<HoaDon_Response> result, HoaDon hd) {
        HoaDon_Response hoaDon_response = hoaDonMapper.toHoaDonResponse(hd);

        KhachHang khachHang = khachHangRepo.findById(hd.getKhachHang().getKhachHangId())
                .orElseThrow(()-> new AppException(ErrorCode.KHACHHANG_NOT_EXIST));

        List<ChiTietHoaDon_Response> chiTietHoaDon_responses = getChiTietHoaDonResponses(hd);

        hoaDon_response.setKhachHang(khachHangMapper.toKhachHangResponse(khachHang));
        hoaDon_response.setChiTietHoaDons(chiTietHoaDon_responses);
        result.add(hoaDon_response);
    }

    private List<ChiTietHoaDon_Response> getChiTietHoaDonResponses(HoaDon hd) {
        List<ChiTietHoaDon_Response> chiTietHoaDon_responses = new ArrayList<>();
        for (ChiTietHoaDon cthd : hd.getChiTietHoaDons()){
            ChiTietHoaDon_Response chiTietHoaDon_response = chiTietHoaDonMapper.toChiTietHoaDonResponse(cthd);
            chiTietHoaDon_response.setTenSanPham(cthd.getSanPham().getTenSanPham());
            chiTietHoaDon_responses.add(chiTietHoaDon_response);
        }
        return chiTietHoaDon_responses;
    }
}
