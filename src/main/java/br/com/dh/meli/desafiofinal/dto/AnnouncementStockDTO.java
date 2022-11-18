package br.com.dh.meli.desafiofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * The type Announcement stock dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementStockDTO {
    private SectionDTO section;
    private Long announcementId;
    private List<BatchStockDTOResponse> batchList;
}
