package com.bezkoder.springjwt.adminControllers.bannerController;

import com.bezkoder.springjwt.urlMappings.URLMappings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLMappings.AUTH_ACCESS_URL_ADMIN)
public class BannerController {

    @PostMapping("/makeBanner")
    public ResponseEntity<?> makeBanner()
    {
        return null;
    }
}