package SanPham.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SanPham_Response {
    int sanPhamId;
    String tenSanPham;
    int giaThanh;
    String moTa;
    LocalDate ngayHetHan;
    String kyHieuSanPham;
}
