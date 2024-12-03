package com.facade.pattern.campus_sync.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "<html>" +
                "<head>" +
                "<style>" +
                "body { margin: 0; padding: 0; }" + // Eliminar márgenes y padding del body
                "</style>" +
                "</head>" +
                "<body>" +
                "<div style='width: 100%; height: 100vh; display: flex; flex-direction: column; justify-content: center; align-items: center;background-color: #212121; color: white; text-align: center;'>"
                +
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
                "</pre></div>" +
                "</body>" +
                "</html>";
    }
}
