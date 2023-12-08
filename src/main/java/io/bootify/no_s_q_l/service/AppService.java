package io.bootify.no_s_q_l.service;

import io.bootify.no_s_q_l.domain.App;
import io.bootify.no_s_q_l.model.AppDTO;
import io.bootify.no_s_q_l.repos.AppRepository;
import io.bootify.no_s_q_l.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AppService {

    private final AppRepository appRepository;

    public AppService(final AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public List<AppDTO> findAll() {
        final List<App> apps = appRepository.findAll(Sort.by("app"));
        return apps.stream()
                .map(app -> mapToDTO(app, new AppDTO()))
                .toList();
    }

    public AppDTO get(final String app) {
        return appRepository.findById(app)
                .map(appEntity -> mapToDTO(appEntity, new AppDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final AppDTO appDTO) {
        final App app = new App();
        mapToEntity(appDTO, app);
        app.setApp(appDTO.getApp());
        return appRepository.save(app).getApp();
    }

    public void update(final String app, final AppDTO appDTO) {
        final App appEntity = appRepository.findById(app)
                .orElseThrow(NotFoundException::new);
        mapToEntity(appDTO, appEntity);
        appRepository.save(appEntity);
    }

    public void delete(final String app) {
        appRepository.deleteById(app);
    }

    private AppDTO mapToDTO(final App app, final AppDTO appDTO) {
        appDTO.setApp(app.getApp());
        appDTO.setCategory(app.getCategory());
        appDTO.setRating(app.getRating());
        appDTO.setReviews(app.getReviews());
        appDTO.setSize(app.getSize());
        appDTO.setInstalls(app.getInstalls());
        appDTO.setType(app.getType());
        appDTO.setPrice(app.getPrice());
        appDTO.setContentRating(app.getContentRating());
        appDTO.setGenres(app.getGenres());
        return appDTO;
    }

    private App mapToEntity(final AppDTO appDTO, final App app) {
        app.setCategory(appDTO.getCategory());
        app.setRating(appDTO.getRating());
        app.setReviews(appDTO.getReviews());
        app.setSize(appDTO.getSize());
        app.setInstalls(appDTO.getInstalls());
        app.setType(appDTO.getType());
        app.setPrice(appDTO.getPrice());
        app.setContentRating(appDTO.getContentRating());
        app.setGenres(appDTO.getGenres());
        return app;
    }

    public boolean appExists(final String app) {
        return appRepository.existsByAppIgnoreCase(app);
    }

}
