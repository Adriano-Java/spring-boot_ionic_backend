package br.com.ans.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 27/03/2020
 */
public class URL {
    /**
     * Método que espera receber uma cadeia de caracteres (String) numéricos separados por vírgulas (exemplo: "1,2,3...")
     * para convertê-la numa lista de números inteiros.
     * @param string
     * @return
     */
    public static List<Integer> decodeIntList(String string){
        String [] vetor = string.split(",");
        List<Integer> list = new ArrayList<>();
        for(int inteiro = 0; inteiro < vetor.length; inteiro++){
            list.add(Integer.parseInt(vetor[inteiro]));
        }
        return list;
        /*As linhas acimas convertidas numa lambda.*/
//        return Arrays.asList(string.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }

    /**
     * Método que decodifica uma cadeia de caracteres (String) passada como parâmetro no URL, retornando um valor vazio
     * caso uma UnsupportedEncodingException ocorra.
     * @param string
     * @return
     */
    public static String decodeParam(String string){
        try {
            return URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}