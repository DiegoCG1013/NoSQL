package io.bootify.no_s_q_l.rest;

import io.bootify.no_s_q_l.model.AppDTO;
import io.bootify.no_s_q_l.service.AppService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/apps", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppResource {

    private final AppService appService;

    public AppResource(final AppService appService) {
        this.appService = appService;
    }

    @GetMapping
    public ResponseEntity<List<AppDTO>> getAllApps() {
        return ResponseEntity.ok(appService.findAll());
    }

    @GetMapping("/{app}")
    public ResponseEntity<AppDTO> getApp(@PathVariable(name = "app") final String app) {
        return ResponseEntity.ok(appService.get(app));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<String> createApp(@RequestBody @Valid final AppDTO appDTO,
            final BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (!bindingResult.hasFieldErrors("app") && appDTO.getApp() == null) {
            bindingResult.rejectValue("app", "NotNull");
        }
        if (!bindingResult.hasFieldErrors("app") && appService.appExists(appDTO.getApp())) {
            bindingResult.rejectValue("app", "Exists.app.app");
        }
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(new MethodParameter(
                    this.getClass().getDeclaredMethods()[0], -1), bindingResult);
        }
        final String createdApp = appService.create(appDTO);
        return new ResponseEntity<>(createdApp, HttpStatus.CREATED);
    }

    @PutMapping("/{app}")
    public ResponseEntity<String> updateApp(@PathVariable(name = "app") final String app,
            @RequestBody @Valid final AppDTO appDTO) {
        appService.update(app, appDTO);
        return ResponseEntity.ok(app);
    }

    @DeleteMapping("/{app}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteApp(@PathVariable(name = "app") final String app) {
        appService.delete(app);
        return ResponseEntity.noContent().build();
    }

}
