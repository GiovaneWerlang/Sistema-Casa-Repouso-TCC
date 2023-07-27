package br.edu.utfpr.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageDTO<T> {

    List<T> lista;
    int pages;
    long total;

}
