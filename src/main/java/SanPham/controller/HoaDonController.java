package SanPham.controller;

import SanPham.dto.request.HoaDon_Request;
import SanPham.dto.request.HoaDon_Update_Request;
import SanPham.dto.response.ApiResponse;
import SanPham.dto.response.HoaDon_Response;
import SanPham.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("hoadon")
public class HoaDonController {
    @Autowired
    HoaDonService hoaDonService;

    @PostMapping("/addhoadon")
    ApiResponse<HoaDon_Response> addHoaDon(@RequestBody HoaDon_Request request){
        ApiResponse<HoaDon_Response> apiResponse = new ApiResponse<>();
        apiResponse.setResult(hoaDonService.addHoaDon(request));
        return apiResponse;
    }
    @GetMapping("/getall/{pageNo}-{pageSize}")
    ApiResponse<List<?>> getAllHoaDon(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize){
        ApiResponse<List<?>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(hoaDonService.phanTrang(hoaDonService.getAllHoaDon(), pageNo, pageSize));
        return apiResponse;
    }
    @PutMapping("/updatehoadon")
    ApiResponse<HoaDon_Response> updateHoaDon(@RequestBody HoaDon_Update_Request request){
        ApiResponse<HoaDon_Response> apiResponse = new ApiResponse<>();
        apiResponse.setResult(hoaDonService.updateHoaDon(request));
        return apiResponse;
    }
    @DeleteMapping("/deletehoadon/{id}")
    String deleteHoaDon(@PathVariable("id") int hoaDonId){
        hoaDonService.deleteHoaDon(hoaDonId);
        return "Delete complete";
    }
    @GetMapping("/getallhoadonorderbythoigiantao/{pageNo}-{pageSize}")
    ApiResponse<?> getAllHoaDonOrderByThoiGianTao(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize){
        ApiResponse<List<?>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(hoaDonService.phanTrang(hoaDonService.getAllHoaDonOrderByThoiGianTao(), pageNo, pageSize));
        return apiResponse;
    }
    @GetMapping("/gethoadonbyyearandmonth/{year}/{month}/{pageNo}-{pageSize}")
    ApiResponse<List<?>> getHoaDonByYearAndMonth(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize){
        ApiResponse<List<?>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(hoaDonService.phanTrang(hoaDonService.getHoaDonByYearAndMonth(year, month), pageNo, pageSize));
        return apiResponse;
    }
    @GetMapping("/searchhoadonbytime/{pageNo}-{pageSize}")
    ApiResponse<List<?>> getHoaDonByTime(@RequestParam("date1") LocalDate date1, @RequestParam("date2") LocalDate date2, @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize){
        ApiResponse<List<?>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(hoaDonService.phanTrang(hoaDonService.searchHoaDonByTime(date1, date2), pageNo, pageSize));
        return apiResponse;
    }
    @GetMapping("/gethoadonbytongtien/{pageNo}-{pageSize}")
    ApiResponse<List<?>> getHoaDonByTongTien(@RequestParam("tt1") int tongTien1, @RequestParam("tt2") int tongTien2, @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize){
        ApiResponse<List<?>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(hoaDonService.phanTrang(hoaDonService.getHoaDonByTongTien(tongTien1, tongTien2), pageNo, pageSize));
        return apiResponse;
    }
    @GetMapping("/searchhoadonbymagiaodich")
    ApiResponse<HoaDon_Response> searchHoaDonByMaGiaoDich(@RequestParam("mgd") String maGiaoDich){
        ApiResponse<HoaDon_Response> apiResponse = new ApiResponse<>();
        apiResponse.setResult(hoaDonService.searchHoaDonByMaGiaoDich(maGiaoDich));
        return apiResponse;
    }
}
