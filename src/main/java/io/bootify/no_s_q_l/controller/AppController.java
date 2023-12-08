package io.bootify.no_s_q_l.controller;

import io.bootify.no_s_q_l.model.AppDTO;
import io.bootify.no_s_q_l.service.AppService;
import io.bootify.no_s_q_l.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/apps")
public class AppController {

    private final AppService appService;

    public AppController(final AppService appService) {
        this.appService = appService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("apps", appService.findAll());
        return "app/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("app") final AppDTO appDTO) {
        return "app/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("app") @Valid final AppDTO appDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("app") && appDTO.getApp() == null) {
            bindingResult.rejectValue("app", "NotNull");
        }
        if (!bindingResult.hasFieldErrors("app") && appService.appExists(appDTO.getApp())) {
            bindingResult.rejectValue("app", "Exists.app.app");
        }
        if (bindingResult.hasErrors()) {
            return "app/add";
        }
        appService.create(appDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("app.create.success"));
        return "redirect:/apps";
    }

    @GetMapping("/edit/{app}")
    public String edit(@PathVariable(name = "app") final String app, final Model model) {
        model.addAttribute("app", appService.get(app));
        return "app/edit";
    }

    @PostMapping("/edit/{app}")
    public String edit(@PathVariable(name = "app") final String app,
            @ModelAttribute("app") @Valid final AppDTO appDTO, final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "app/edit";
        }
        appService.update(app, appDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("app.update.success"));
        return "redirect:/apps";
    }

    @PostMapping("/delete/{app}")
    public String delete(@PathVariable(name = "app") final String app,
            final RedirectAttributes redirectAttributes) {
        appService.delete(app);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("app.delete.success"));
        return "redirect:/apps";
    }

}
