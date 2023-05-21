package com.kaliada.tickets.services.impl;

import com.kaliada.tickets.services.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    private final RestTemplate restTemplate;

    @Override
    public <T> T getResponseContent(String url, Class<T> clazz) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Cookie", List.of("fr-correlation-id=5203ccc1-66da-40d6-8680-422c690d6b2e; rid=2ad1e116-de2e-4b7c-8e07-db1efea5600e; rid.sig=OCBtG/WF9HO7H4S44kdr0fXMm31XOLv14+zlFkNwLbmaltHhi8t83gCkOFtno4CVq+VI9qRKPcGVbfxumiQd87bdgolWSkDK9rP2Ypwkc08U6a0Acl+fmjzcyny4G68yB4W7PJhxP3fXvsCEYJMEfoO+7i9JTTLpErcRC2g5r+xP/nS0MHe2XXb5hooMR+8f144lqjOrI98ynoDTtL6x4MQif6yc8o6PcAq37v6bH0TovnOtdQcewlRuNACdDRE4hiI3hwqpV9GOTNxlnR5hPCmqFEHv8+a8nnn9RIRsONXF22zSYtLeOmbtKDF1VDIIZa/5A9wi3/G/GdUMMlcL2TFEXQV3GNxdcto8y5GpIB8+0AfBKi3NBJ3n6hAQpdnU3pN5dMnMb+A4+BWEfoCmt7pwHlNrxQR7i+1gnWpHRuXjopZZpsQiFRwb5S3ubfH6ze3CmRcDKncmGPaP5sHSw8h7X4+A4Xr1YpSsO6Hwx2fHEkDNLApB6Ds/Ukmh/PxP/Rc4ASF3okknK3CiLqo/2Q==; mkt=/pl/pl/; fr-visit-id=0bf79967-e827-4408-805f-28be106da8ec; myRyanairID=; RYANSESSION=ZFqjuQqhAekAAEBAXcgAAAAs; STORAGE_PREFERENCES={\"STRICTLY_NECESSARY\":true,\"PERFORMANCE\":false,\"FUNCTIONAL\":false,\"TARGETING\":false,\"SOCIAL_MEDIA\":false,\"PIXEL\":false,\"GANALYTICS\":false,\"__VERSION\":2}; RY_COOKIE_CONSENT=true; .AspNetCore.Session=CfDJ8IEZM%2BUQcyNApRyvZIgkrlMD%2Be8JYsTkgSHPgUcu9L0ARUjWt0fvHisKwVElNB7D%2FTgElEyIojxIRn18Xaw%2F%2BBOIwXUWwZ72FTCDSprgjwa8NhOHQuPCw0gLzK5dda51Wklv2wQoefYYKb3e5ll%2B%2F%2BY342T5LPQK65I%2BBPj4s8lD"));
        headers.put("User-Agent", List.of("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36"));
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(null, headers), clazz).getBody();
    }

    @Override
    public <T> T[] getArrayResponseContent(String url, Class<T[]> clazz) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Cookie", List.of("fr-correlation-id=5203ccc1-66da-40d6-8680-422c690d6b2e; rid=2ad1e116-de2e-4b7c-8e07-db1efea5600e; rid.sig=OCBtG/WF9HO7H4S44kdr0fXMm31XOLv14+zlFkNwLbmaltHhi8t83gCkOFtno4CVq+VI9qRKPcGVbfxumiQd87bdgolWSkDK9rP2Ypwkc08U6a0Acl+fmjzcyny4G68yB4W7PJhxP3fXvsCEYJMEfoO+7i9JTTLpErcRC2g5r+xP/nS0MHe2XXb5hooMR+8f144lqjOrI98ynoDTtL6x4MQif6yc8o6PcAq37v6bH0TovnOtdQcewlRuNACdDRE4hiI3hwqpV9GOTNxlnR5hPCmqFEHv8+a8nnn9RIRsONXF22zSYtLeOmbtKDF1VDIIZa/5A9wi3/G/GdUMMlcL2TFEXQV3GNxdcto8y5GpIB8+0AfBKi3NBJ3n6hAQpdnU3pN5dMnMb+A4+BWEfoCmt7pwHlNrxQR7i+1gnWpHRuXjopZZpsQiFRwb5S3ubfH6ze3CmRcDKncmGPaP5sHSw8h7X4+A4Xr1YpSsO6Hwx2fHEkDNLApB6Ds/Ukmh/PxP/Rc4ASF3okknK3CiLqo/2Q==; mkt=/pl/pl/; fr-visit-id=0bf79967-e827-4408-805f-28be106da8ec; myRyanairID=; RYANSESSION=ZFqjuQqhAekAAEBAXcgAAAAs; STORAGE_PREFERENCES={\"STRICTLY_NECESSARY\":true,\"PERFORMANCE\":false,\"FUNCTIONAL\":false,\"TARGETING\":false,\"SOCIAL_MEDIA\":false,\"PIXEL\":false,\"GANALYTICS\":false,\"__VERSION\":2}; RY_COOKIE_CONSENT=true; .AspNetCore.Session=CfDJ8IEZM%2BUQcyNApRyvZIgkrlMD%2Be8JYsTkgSHPgUcu9L0ARUjWt0fvHisKwVElNB7D%2FTgElEyIojxIRn18Xaw%2F%2BBOIwXUWwZ72FTCDSprgjwa8NhOHQuPCw0gLzK5dda51Wklv2wQoefYYKb3e5ll%2B%2F%2BY342T5LPQK65I%2BBPj4s8lD"));
        headers.put("User-Agent", List.of("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36"));
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(null, headers), clazz).getBody();
    }
}
