package br.edu.unoesc.controller;

import org.springframework.ui.Model;

public abstract class BaseController {

    protected void setCommonAttributes(Model model, String pageTitle, String newButtonLabel, String newButtonLink) {
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("newButtonLabel", newButtonLabel);
        model.addAttribute("newButtonLink", newButtonLink);
    }
}
