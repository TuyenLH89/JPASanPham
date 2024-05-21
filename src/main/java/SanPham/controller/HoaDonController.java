package SanPham.controller;

import SanPham.dto.request.HoaDon_Request;
import SanPham.dto.response.ApiResponse;
import SanPham.dto.response.HoaDon_Response;
import SanPham.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
