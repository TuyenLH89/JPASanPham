package SanPham.exception;

import SanPham.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException e){
        ErrorCode errorCode = ErrorCode.KEY_ERROR_NOT_EXIST;
        try {
            errorCode = e.getErrorCode();
        }catch (Exception exception){

        }
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(value = IllegalArgumentException.class)
    ResponseEntity<ApiResponse> handlingIllegalArgumentException(IllegalArgumentException exception){
        String enumKey = exception.getMessage();
        ErrorCode errorCode = ErrorCode.KEY_ERROR_NOT_EXIST;
        if (enumKey.equals("Page index must not be less than zero"))
            errorCode = ErrorCode.PAGEINDEX_INVALID;
        else if (enumKey.equals("Page size must not be less than one"))
            errorCode = ErrorCode.PAGESIZE_INVALID;
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
