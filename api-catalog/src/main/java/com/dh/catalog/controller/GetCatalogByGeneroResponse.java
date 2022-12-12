package com.dh.catalog.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCatalogByGeneroResponse {

    private List<GetCatalogByGeneroResponse.MovieDto> movies = new ArrayList<>();
    private List<GetCatalogByGeneroResponse.SerieDto> series = new ArrayList<>();
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MovieDto{
        private Long id;
        private String name;
        private String genre;
        private String urlStream;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SerieDto{
        private Long id;
        private String name;
        private String genre;
        private String urlStream;
    }

}
