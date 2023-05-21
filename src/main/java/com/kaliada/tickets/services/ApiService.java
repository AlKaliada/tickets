package com.kaliada.tickets.services;

public interface ApiService {
    <T> T getResponseContent(String url, Class<T> clazz);
    <T> T[] getArrayResponseContent(String url, Class<T[]> clazz);
}
