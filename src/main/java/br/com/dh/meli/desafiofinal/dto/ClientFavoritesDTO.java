package br.com.dh.meli.desafiofinal.dto;

import br.com.dh.meli.desafiofinal.model.Announcement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientFavoritesDTO {

    public Long clientId;

    public String clientName;

    public List<FavoriteAnnouncementDTO> lista;

}
