package SanPham.controller;

import SanPham.dto.request.KhachHang_Request;
import SanPham.dto.response.ApiResponse;
import SanPham.dto.response.KhachHang_Response;
import SanPham.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/khachhang")
public class KhachHangController {
    @Autowired
    KhachHangService khachHangService;

    @PostMapping("/addkhachhang")
    ApiResponse<KhachHang_Response> addKhachHang(@RequestBody KhachHang_Request request){
        ApiResponse<KhachHang_Response> apiResponse = new ApiResponse<>();
        apiResponse.setResult(khachHangService.addKhachHang(request));
        return apiResponse;
    }
    @PutMapping("/updatekhachhang/{id}")
    ApiResponse<KhachHang_Response> updateKhachHang(@PathVariable("id") int khachHangId, @RequestBody KhachHang_Request request){
        ApiResponse<KhachHang_Response> apiResponse = new ApiResponse<>();
        apiResponse.setResult(khachHangService.updateKhachHang(khachHangId, request));
        return apiResponse;
    }
}
