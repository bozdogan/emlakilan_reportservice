package org.bozdgn.reportservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/testuser")
    public ResponseEntity<String> showCurrentUserInfo() {
        return ResponseEntity.ok("Only users can see this!");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/testadmin")
    public ResponseEntity<String> adminPage() {
        return ResponseEntity.ok("Only amdins can see this!");
    }
}
