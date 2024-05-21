package SanPham.controller;

import SanPham.dto.request.SanPham_Request;
import SanPham.dto.response.ApiResponse;
import SanPham.dto.response.SanPham_Response;
import SanPham.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sanpham")
public class SanPhamController {
    @Autowired
    SanPhamService sanPhamService;

    @PostMapping("/addsanpham")
    ApiResponse<SanPham_Response> addSanPham(@RequestBody SanPham_Request request){
        ApiResponse<SanPham_Response> apiResponse = new ApiResponse<>();
        apiResponse.setResult(sanPhamService.addSanPham(request));
        return apiResponse;
    }
}
