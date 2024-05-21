package SanPham.controller;

import SanPham.dto.request.LoaiSanPham_Request;
import SanPham.dto.response.ApiResponse;
import SanPham.dto.response.LoaiSanPham_Response;
import SanPham.service.LoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loaisanpham")
public class LoaiSanPhamController {
    @Autowired
    LoaiSanPhamService loaiSanPhamService;
    @PostMapping("/addloaisanpham")
    ApiResponse<LoaiSanPham_Response> addLoaiSanPham(@RequestBody LoaiSanPham_Request request){
        ApiResponse<LoaiSanPham_Response> apiResponse = new ApiResponse<>();
        apiResponse.setResult(loaiSanPhamService.addSanPham(request));
        return apiResponse;
    }
}
