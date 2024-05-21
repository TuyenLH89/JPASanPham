package SanPham.exception;

public enum ErrorCode {
    KEY_ERROR_NOT_EXIST(0000, "error key not exist"),
    LOAISANPHAM_EXIST(1001, "Loai san pham exist"),
    SANPHAM_EXIST(1002, "San pham exist"),
    NGAYSINH_INVALID(1003, "Ngay sinh Invalid"),
    KHACHHANG_NOT_EXIST(1004, "Khach hang not exist"),
    HOADON_NOT_EXIST(1005, "Hoa don not exist"),
    SANPHAM_NOT_EXIST(1006, "San pham not exist");
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
