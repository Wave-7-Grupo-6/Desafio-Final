package br.com.dh.meli.desafiofinal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteAnnouncementDTO {

    public Long categoryId;

    public String categoryName;

    public String description;

    public Long id;
}
