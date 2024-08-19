package org.scoula.weather.controller;

import lombok.extern.slf4j.Slf4j;
import org.scoula.weather.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@Slf4j
@RequestMapping("/weather")
@PropertySource({"classpath:/application.properties"})
public class WeatherController {
    @Value("${weather.url}")
    private String URL;

    @Value("${weather.icon_url}")
    private String ICON_URL;

    @Value("${weather.api_key}")
    private String API_KEY;

    @GetMapping({"", "/{city}"})
    public String weather(Model model, @PathVariable(value="city", required=false) String city) { // 필수 아님
        city = city == null ? "seoul" : city;

        RestTemplate restTemplate = new RestTemplate();
        // 쿼리문 ? 뒤에 올 문자열 설정
        String url = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("q", city)
                .queryParam("units", "metric")
                .queryParam("APPID", API_KEY)
                .queryParam("lang", "kr")
                .toUriString();

        WeatherDTO weather = restTemplate.getForObject(url, WeatherDTO.class); // 요청 문자열, dto
        String iconUrl = ICON_URL.formatted(weather.getWeather().get(0).getIcon()); // List<WeatherItem>의 0번째 요소 -> %s를 대체 (printf)
        log.info("오늘의 날씨: " + weather);

        model.addAttribute("city", city);
        model.addAttribute("weather", weather);
        model.addAttribute("icon", iconUrl);

        return "weather/today";
    }
}
