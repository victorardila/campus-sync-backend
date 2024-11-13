package com.facade.pattern.campus_sync.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "<div style='background-color: #212121; color: white; padding: 20px; text-align: center;'>" +
                "<pre style='font-family: monospace; line-height: 1; margin: 0;'>" +
                "█▀▀▄ █▀▀█ █▀▀ █─█ █▀▀ █▀▀▄ █▀▀▄ \n" +
                "█▀▀▄ █▄▄█ █── █▀▄ █▀▀ █──█ █──█ \n" +
                "▀▀▀─ ▀──▀ ▀▀▀ ▀─▀ ▀▀▀ ▀──▀ ▀▀▀─ \n" +
                "</pre>" +
                "<pre style='font-family: monospace; line-height: 1; margin: 0;'>" +
                "░█▀▀█ ─█▀▀█ ░█▀▄▀█ ░█▀▀█ ░█─░█ ░█▀▀▀█ 　 ░█▀▀▀█ ░█──░█ ░█▄─░█ ░█▀▀█ 　 ░█▀▀█ ░█─░█ ░█▄─░█ ▀█▀ ░█▄─░█ ░█▀▀█      \n"
                +
                "░█─── ░█▄▄█ ░█░█░█ ░█▄▄█ ░█─░█ ─▀▀▀▄▄ 　 ─▀▀▀▄▄ ░█▄▄▄█ ░█░█░█ ░█─── 　 ░█▄▄▀ ░█─░█ ░█░█░█ ░█─ ░█░█░█ ░█─▄▄ ▄ ▄ ▄\n"
                +
                "░█▄▄█ ░█─░█ ░█──░█ ░█─── ─▀▄▄▀ ░█▄▄▄█ 　 ░█▄▄▄█ ──░█── ░█──▀█ ░█▄▄█ 　 ░█─░█ ─▀▄▄▀ ░█──▀█ ▄█▄ ░█──▀█ ░█▄▄█ █ █ █"
                +
                "</pre></div>";
    }
}
